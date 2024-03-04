package br.jus.tream.jwtsec.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.jus.tream.jwtsec.domain.Usuario;
import br.jus.tream.jwtsec.repositories.UsuarioRepository;
import br.jus.tream.jwtsec.security.exceptions.TokenExpiredException;
import br.jus.tream.jwtsec.services.exceptions.DataIntegrityException;
import br.jus.tream.jwtsec.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepo;
	
	public List<Usuario> findAllByPerfil(){
		return usuarioRepo.findAllByPerfil();
	}
	public List<Usuario> findAllParaDistribuir(){
		return usuarioRepo.findAllParaDistribuir();
	}
	public List<Usuario> findAllThrow(){
		throw new TokenExpiredException("Gerando Error - jwt expirado test");
	}
	public List<Usuario> findAll(){
		return usuarioRepo.findAll();
	}
	public Usuario findById(String id){
		Optional<Usuario> obj = usuarioRepo.findById(id); 
    	return obj.orElseThrow(() -> new ObjectNotFoundException( 
    		      "Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName())); 
	}
	public Usuario findByMatricula(String mat){
		Optional<Usuario> obj = Optional.of(usuarioRepo.findByMatricula(mat));
    	return obj.orElseThrow(() -> new ObjectNotFoundException( 
    		      "Objeto não encontrado! Matrícula: " + mat + ", Tipo: " + Usuario.class.getName())); 
	}
	
	@Transactional
	public void save(Usuario obj) {
		usuarioRepo.save(obj);
	}
	
	@Transactional
	public void removePerfil(String numTitulo) {
		usuarioRepo.removePerfil(numTitulo);
	}
	
	@Transactional
	public void delete(String id) {
		Usuario user = usuarioRepo.findById(id).get();
    	try {
    		usuarioRepo.delete(user);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma Cliente que possui pedidos cadastrados!");
		}    	
	}
}
