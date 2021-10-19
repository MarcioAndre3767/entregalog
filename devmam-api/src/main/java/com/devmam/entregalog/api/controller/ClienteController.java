package com.devmam.entregalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devmam.entregalog.domain.model.Cliente;
import com.devmam.entregalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	

	//@Autowired -> All...
	private ClienteRepository clienteRepository;
	
	
	
	
	@GetMapping
	public List<Cliente> listar() {
		
		return clienteRepository.findAll();		
		//return clienteRepository.findByNomeContaining("io");
		//return clienteRepository.findByNome("Marcio");			
	}
	
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		
		return clienteRepository.findById(clienteId)
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.notFound().build());
		
		
		
//		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
//		
//		if (cliente.isPresent()) {
//			return  ResponseEntity.ok(cliente.get());	
//		}
//		
//		return ResponseEntity.notFound().build(); 
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente){
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = clienteRepository.save(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepository.deleteById(clienteId);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	

}








//@GetMapping("/clientes")
//public List<Cliente> listar() {
//	Cliente cliente1 = new Cliente();
//	cliente1.setId(1L);
//	cliente1.setNome("João");
//	cliente1.setTelefone("34 99999-1111");
//	cliente1.setEmail("joaodascouves@algaworks.com");
//	
//	Cliente cliente2 = new Cliente();
//	cliente2.setId(2L);
//	cliente2.setNome("Maria");
//	cliente2.setTelefone("11 99999-2222");
//	cliente2.setEmail("mariadasilva@algaworks.com");
//	
//	return Arrays.asList(cliente1, cliente2);
//}




















