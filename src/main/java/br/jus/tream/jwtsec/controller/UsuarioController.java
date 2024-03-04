package br.jus.tream.jwtsec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.tream.jwtsec.domain.Usuario;
import br.jus.tream.jwtsec.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioController {
	@Autowired
	 private UsuarioService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> find(@PathVariable String id) {
		Usuario obj = service.findById(id); 
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping()
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> lst = service.findAll();
		return ResponseEntity.ok(lst);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
	   return ResponseEntity.noContent().build();	
	}
}
