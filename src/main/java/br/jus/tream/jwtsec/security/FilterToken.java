package br.jus.tream.jwtsec.security;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.jus.tream.jwtsec.repositories.UsuarioRepository;
import br.jus.tream.jwtsec.security.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter{
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain) throws TokenExpiredException, ServletException, IOException {
		String token;
		var authorizationHeader = request.getHeader("Authorization");
		
		if (authorizationHeader != null) {
			token = authorizationHeader.replace("Bearer ", "");
			try {
				var subject = this.tokenService.getSubject(token);
				//System.out.println("subject " + subject);
				var usuario = this.usuarioRepo.findById(subject).get();
				UserDetailsImpl userDetailsImpl = new UserDetailsImpl(usuario);
				var authentication = new UsernamePasswordAuthenticationToken(userDetailsImpl, null, userDetailsImpl.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);	
			} catch (Exception e) {
				System.out.println("Cheguei aqui filterChain !!!--->>>");
				throw new TokenExpiredException("Token JWT inválido ou expirado!", e.getCause());
			}
		}
		filterChain.doFilter(request, response);
	}
}
