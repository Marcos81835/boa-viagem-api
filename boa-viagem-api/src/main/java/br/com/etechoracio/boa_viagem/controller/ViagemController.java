package br.com.etechoracio.boa_viagem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.etechoracio.boa_viagem.entity.Viagem;
import br.com.etechoracio.boa_viagem.service.ViagemService;

@RestController
@RequestMapping("/viagem")
public class ViagemController {
	
	@Autowired
	private ViagemService service;
	
	@GetMapping
	public List<Viagem> listartodos(){
		return service.listartodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Viagem> buscarPorId(@PathVariable Long id) {
		Optional<Viagem> existe = service.buscarPorId(id);
		return existe.isPresent() ? ResponseEntity.ok(existe.get())
								  : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir (@PathVariable Long id) {
		boolean existe = service.excluir(id);
		if (existe) {
			return ResponseEntity.ok().build();
		} 
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Viagem> inserir(@RequestBody Viagem obj) {
		service.inserir(obj);
		return ResponseEntity.ok(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Viagem> atualizar(@PathVariable Long id, 
											@RequestBody Viagem viagem) {
			Optional<Viagem> existe = service.atualizar(id, viagem);
			if (!existe.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(viagem);
	}
}
