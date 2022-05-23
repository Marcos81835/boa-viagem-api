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
public class GastoService {

	@Autowired
	private GastoRepository repository;
	
	@Autowired
	private ViagemRepository viagemRepository;
	
	public List<Gasto> listarTodos(){
		return repository.findAll();
	}
	
	public Optional<Gasto> buscarPorId(Long id) {
		return repository.findById(id);
		}
	
	public boolean excluir(Long id) {
		boolean existe = repository.existsById(id);
		if (existe) {
			repository.deleteById(id);
			}
		return existe;
	}
	
	public Gasto inserir(Gasto obj) {
		boolean existe = repository.existsById(obj.getViagem().getId());
		if(!existe)
		{
			throw new RuntimeException("Viagem não encontrada");
		}
		return repository.save(obj);
	}
	
	public Optional<Gasto> atualizar(Long id, 
						   			 Gasto gasto) {
			boolean existe = repository.existsById(id);
			if (!existe) {
				return Optional.empty();
			}
			return Optional.of(repository.save(gasto));
	}
}