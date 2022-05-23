package br.com.etechoracio.boa_viagem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.etechoracio.boa_viagem.entity.Gasto;
import br.com.etechoracio.boa_viagem.entity.Viagem;
import br.com.etechoracio.boa_viagem.repository.GastoRepository;
import br.com.etechoracio.boa_viagem.repository.ViagemRepository;

@Service
public class ViagemService {
	
	@Autowired
	private ViagemRepository repository;
	
	@Autowired
	private GastoRepository gastoRepository;
	
	public boolean excluir(Long id) {
		boolean existe = repository.existsById(id);
		if (!existe) {
			return existe;
		}
		
		List<Gasto> gastos = gastoRepository.findByViagemId(id);
		if (!gastos.isEmpty()){
			gastoRepository.deleteAll(gastos);
		}
		
		repository.deleteById(id);
		return existe;
		
	}
	
	public List<Viagem> listartodos(){
		return repository.findAll();
	}
	
	public Optional<Viagem> buscarPorId( Long id) {
		return repository.findById(id);
	}
	
	public Viagem inserir(Viagem obj) {
		return repository.save(obj);
	}
	
	public Optional<Viagem> atualizar(Long id, 
									  Viagem viagem) {
			boolean existe = repository.existsById(id);
			if (!existe) {
				return Optional.empty();
			}
			return Optional.of(repository.save(viagem));
	}
}