package br.com.etechoracio.boa_viagem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.etechoracio.boa_viagem.entity.Gasto;
import br.com.etechoracio.boa_viagem.service.GastoService;

@RestController
@RequestMapping("/gastos")
public class GastoController {
	
	private GastoService service;
	
	@GetMapping
	public List<Gasto> listarTodos(){
		return service.listarTodos();
	}
	
/*	@GetMapping("/{id}")
	public ResponseEntity<Gasto> buscarPorId(@PathVariable Long id) {
		Optional<Gasto> existe = repository.findById(id);
		if (existe.isPresent()) {
			return ResponseEntity.ok(existe.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}*/
	@GetMapping("/{id}")
	public ResponseEntity<Gasto> buscarPorId(@PathVariable Long id) {
		Optional<Gasto> existe = service.buscarPorId(id);
		return existe.isPresent() ? ResponseEntity.ok(existe.get())
								  : ResponseEntity.notFound().build();
		}
		
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir(@PathVariable long id) {
		boolean existe = service.excluir(id);
		if (existe) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping
	public ResponseEntity<Gasto> inserir(@RequestBody Gasto obj) {
		service.inserir(obj);
		return ResponseEntity.ok(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Gasto> atualizar(@PathVariable Long id, 
										   @RequestBody Gasto gasto) {
		Optional<Gasto> existe = service.atualizar(id, gasto);
		if (!existe.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(gasto);
	}

	}
	
/*
Contemplem o mago
Com seus poderes
Incríveis poderes
Sob o olhar do necromante
A escada prateada vai se erguer
As pessoas maravilhadas
Com seus olhos cheios de poder
Comida fria vai esquentar ao enfeitiçar
Balançando as suas mãos
O cachorro quente explodirá
Na presença do grande mago
O trânsito para de repente
Pode atravessar a rua
Com os carros parados na sua frente
EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
Na tv mudam-se os canais
Sem que saia do sofáaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
Sua varinha pega então
Pra reclinar-se no ar
Pela luz fraca do sol negro do reino dos sonhos.
O mago sobe as cataratas congeladas de Voldrini
Em busca de Celestia, a guardiã do poder infinito
Quando de repente um terrível Garlon aparece
E ataca com gelo, mas o mago é implacável
O Garlon ruge e libera um vento muito sinistro,
Mas o mago é implacável.
O Garlon invoca as pedras de Prophynia,
Mas o mago é implacável.
Invocando os poderes dos ancestrais,
O mago conjura o fogo sagrado
E lança seu feitiço na larva derretida de um Gort insaciável.
(Obrigado, Gort!)
Ahh, Celestia. Acho que você vai adorar isso
O mago fica diante do precipício do poder definitivo,
Os portões se abrem para revelar...
Hum... Quanto tempo eu fiquei dormindo?
Faminto por causa da última missão
O mago quer lanchar
Traça o rumo do prazer
Pro habitual lugar
Ele é o magoooooooooooooooooooooooooooooooooooooooo
O místico magoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
*/
