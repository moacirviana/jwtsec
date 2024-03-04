package br.jus.tream.jwtsec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.jus.tream.jwtsec.domain.DTO.JwtDTO;
import br.jus.tream.jwtsec.domain.DTO.Login;
import br.jus.tream.jwtsec.security.TokenService;
import br.jus.tream.jwtsec.security.UserDetailsImpl;

@RestController
public class AuthController {
		
		  @Autowired
		  private AuthenticationManager authenticationManager;
		  
		  @Autowired
		  TokenService tokenService;
		  

		  @PostMapping("/login")
		  public ResponseEntity<JwtDTO> login(@RequestBody Login login) {
			  UsernamePasswordAuthenticationToken usernamePassword = 
					  new UsernamePasswordAuthenticationToken(login.getLogin(), login.getSenha());
			  Authentication authentication = authenticationManager.authenticate(usernamePassword);
			  var usuario = (UserDetailsImpl)authentication.getPrincipal();
			  String token = tokenService.gerarToken(usuario.getUsuario());
			  JwtDTO dto = new JwtDTO(usuario.getUsername(),usuario.getUsuario().getNome(), token);
			  return ResponseEntity.ok(dto);
		  }
		  /*
		  @PostMapping("/logout")
		  public ResponseEntity<String> logout(HttpServletRequest request) {
		      request.getSession().invalidate();
		      return ResponseEntity.ok("Logout realizado com sucesso");
		  }
		  */

		  /*
		  @PostMapping("/refresh_token")
		  public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
			  Usuario usuario = UserService.authenticated();
			  System.out.println(usuario.toString());
			  String newToken = tokenService.gerarToken(usuario);
			  response.addHeader("Authorization", "Bearer " + newToken);
			  return ResponseEntity.noContent().build();
			  }
		  */
		  
	
}
