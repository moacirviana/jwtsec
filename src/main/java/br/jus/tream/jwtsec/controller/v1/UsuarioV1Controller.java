package br.jus.tream.jwtsec.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.tream.jwtsec.domain.Usuario;
import br.jus.tream.jwtsec.services.UsuarioService;

@RestController
@RequestMapping(value="/v1/usuarios")
public class UsuarioV1Controller {
	@Autowired
	 private UsuarioService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> find(@PathVariable String id) {
		Usuario obj = service.findById(id); 
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping("/error")
	public ResponseEntity<List<Usuario>> findAllThrow() {
		List<Usuario> lst = service.findAllThrow();
		return ResponseEntity.ok(lst);
	}
	
	@GetMapping()
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> lst = service.findAll();
		return ResponseEntity.ok(lst);
	}
}
