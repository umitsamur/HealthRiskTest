package com.project.tez.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	@Value("${tez.app.secret}")
	private String APP_SECRET;
	@Value("${tez.expires.in}")
	private Long EXPIRES_IN;

	public String generateJwtToken(Authentication auth) {
		JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
		Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);

		// setSubject() userId kullanılıcak, keyimizde
		// setIssuedAt() Ne zaman bu key oluşturuldu ?
		// setExpiration()
		// signWith(algoritma, anahtar)
		// compact() oluşturma
		return Jwts.builder().setSubject(Long.toString(userDetails.getId())).setIssuedAt(new Date())
				.setExpiration(expireDate).signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();

	}

	// şifrelenmiş userIdyi Jwt'den geri çözmek için
	public Long getUserIdFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
			return !isTokenExpired(token);
		} catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException
				| IllegalArgumentException e) {
			return false;
		}
	}

	// expiration değişkeni şu anki zamandan sonra bir tarihi gösteriyorsa
	// 'before()' methodu 'true' değerini döndürür
	private boolean isTokenExpired(String token) {
		Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
		return expiration.before(new Date());
	}

}
