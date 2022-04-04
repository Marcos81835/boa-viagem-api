package br.com.etechoracio.boa_viagem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.etechoracio.boa_viagem.entity.Viagem;
import br.com.etechoracio.boa_viagem.repository.ViagemRepository;

@RestController
@RequestMapping("/viagem")
public class ViagemController {

	private ViagemRepository repository;
	
	public List<Viagem> listartodos(){
		return repository.findAll();
	}
}
