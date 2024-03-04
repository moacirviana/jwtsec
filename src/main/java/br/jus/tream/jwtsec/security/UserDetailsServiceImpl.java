package br.jus.tream.jwtsec.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.jus.tream.jwtsec.domain.Usuario;
import br.jus.tream.jwtsec.repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository userRepo;
    
    @Value("${spring.profiles.active}")
	String profile;
     
    @Override
    public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException {
    	Optional<Usuario> user = userRepo.findById(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado ou desabilitado!");
        }
        if(profile.contains("prod")) {
        	user.get().setProducao(true);
    	}
        return new UserDetailsImpl(user.get());
     }
    
}