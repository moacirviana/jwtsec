package br.jus.tream.jwtsec.security;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.jus.tream.jwtsec.domain.Usuario;

@Service
public class TokenService {
	
	private String secret = "Minh@CHAv3S3cret@";
	
	public String gerarToken(Usuario usuario) {
		return JWT.create()
				.withIssuer("JWTSEC")
				.withSubject(usuario.getId())
				.withClaim("id", usuario.getId())
				.withExpiresAt(LocalDateTime.now()
						.plusSeconds(1200)
						.toInstant(ZoneOffset.of("-04:00"))
				 ).sign(Algorithm.HMAC512(secret));
				 
						
	}

	public String getSubject(String token) {
		return JWT.require(Algorithm.HMAC512(secret))
					.withIssuer("JWTSEC")
					.build().verify(token).getSubject();
     }
}
