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
		Optional<Viagem> viagem = viagemRepository.findById(obj.getViagem().getId());
		
		if(!viagem.isPresent())
		{
			throw new RuntimeException("Viagem não encontrada");
		}
		if(viagem.get().getSaida() == null)
		{
			throw new RuntimeException("Data de saida preenchida");
		}
		if(!viagem.get().getChegada().isBefore(obj.getData()))
		{
			throw new RuntimeException("Data de chegada é menor");
		}
		
		return repository.save(obj);
	}
	
	public Optional<Gasto> atualizar(Long id, 
						   			 Gasto gasto) {
			boolean existe = repository.existsById(id);
			Optional<Viagem> viagem = viagemRepository.findById(gasto.getViagem().getId());
			
			if (!existe) {
				return Optional.empty();
			}
			
			if(gasto.getData().isBefore(viagem.get().getSaida())) 
			{
				throw new RuntimeException("Data gasto anterior a Saida");
			}
			
			if(!gasto.getViagem().getId().equals(viagem.get().getId()))
			{
				throw new RuntimeException("Viagem de atualização não for a mesma viagem da inserção");
			}
			return Optional.of(repository.save(gasto));
	}
}