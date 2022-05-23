package br.com.etechoracio.boa_viagem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.etechoracio.boa_viagem.entity.Viagem;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {
	
	List<Viagem> findByGastoId(Long id);

}
