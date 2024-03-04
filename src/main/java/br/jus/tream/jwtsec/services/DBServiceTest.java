package br.jus.tream.jwtsec.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.tream.jwtsec.domain.Usuario;
import br.jus.tream.jwtsec.domain.enums.Perfil;
import br.jus.tream.jwtsec.repositories.UsuarioRepository;

@Service
public class DBServiceTest {
	/*
	@Autowired
	private BCryptPasswordEncoder pe; */
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	public void instantiateTestDatabase() throws ParseException {		
		Usuario s1 = new Usuario();
		s1.setMatricula("2301634");
		s1.setId("015697172275");
		s1.setNome("MOACIR VIANA");
		s1.setIdUnidade(1);
		s1.setSiglaUnd("SEDES");
		s1.addPerfil(Perfil.ADMIN);
	
		Usuario s2 = new Usuario();
		s2.setMatricula("2301635");
		s2.setId("015697172276");
		s2.setNome("RICAOM ANAIV");
		s2.setIdUnidade(1);
		//s2.addPerfil(Perfil.ADMIN);
		s2.setSiglaUnd("SEDES");
		
		usuarioRepo.saveAll(Arrays.asList(s1,s2));

	}
}
