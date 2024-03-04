package br.jus.tream.jwtsec.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.jus.tream.jwtsec.security.ActiveDirectoryService;
import br.jus.tream.jwtsec.security.UserDetailsServiceImpl;

@Component
public class CustomAuthenticatorProvider implements AuthenticationProvider {
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	ActiveDirectoryService adService;
	
	@Autowired
	Environment env;
	
	@Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
        // ACRESCENTANDO ZEROS A ESQUERDA
        String name = ("000000000000" + authentication.getName()).substring(authentication.getName().length());
        String password = authentication.getCredentials().toString();
        // ACRESCENTANDO MANUALMENTE A ROLE USER
        //List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //authorities.add(new SimpleGrantedAuthority("USER")); 
        
        if (this.loginAD(name, password)) {
        	UserDetails userDetail = userDetailsService.loadUserByUsername(name);        	
        	//userDetail.getAuthorities().stream().map(x -> Perfil.toEnum(x)).collect(Collectors.t());
            //for (userDetail.getAuthorities())
        	return new UsernamePasswordAuthenticationToken(userDetail, password, userDetail.getAuthorities());
        } else {
        	throw new
        	    BadCredentialsException("Usuário e senha inválidos");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    private boolean loginAD(String username, String password) {
		boolean ret = false;
		if (Arrays.asList(env.getActiveProfiles()).contains("prod"))
		    ret = adService.loginActiveDirectory(username, password);
		else
		  ret = true;
    	return ret;
	}
    
	
}
