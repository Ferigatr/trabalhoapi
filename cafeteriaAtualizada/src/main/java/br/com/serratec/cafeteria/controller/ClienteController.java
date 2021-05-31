package br.com.serratec.cafeteria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.serratec.cafeteria.model.Cliente;
import br.com.serratec.cafeteria.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> obter() {
		return this.clienteRepository.findAll();
	}

	@GetMapping("/{idCliente}")
	public ResponseEntity<Cliente> obterPorId(@PathVariable(value = "idCliente") Long idCliente) {
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		if (cliente.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
	}

	// @GetMapping("/{nome}")
	// public ResponseEntity<Cliente> obterPorNome(@PathVariable(value = "nome")
	// String nome) {
	// List<Cliente> a = a.stream().filter(b ->
	// b.getNome().equals(nome)).collect(Collectors.toList());

	// Optional<Cliente> nome = clienteRepository.findByName(nome);
	// if (nome.isEmpty()) {
	// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// }
	// return new ResponseEntity<>(nome.get(), HttpStatus.OK);
	// }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente) {

		return this.clienteRepository.save(cliente);
	}

	@PutMapping("/{idCliente}")
	public ResponseEntity<Cliente> atualizar(@PathVariable(value = "idCliente") Long idCliente,
			@RequestBody Cliente cliente) {
		Optional<Cliente> idInternoCliente = clienteRepository.findById(idCliente);

		if (idInternoCliente.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		cliente.setIdCliente(idCliente);
		this.clienteRepository.save(cliente);
		return new ResponseEntity<>(idInternoCliente.get(), HttpStatus.OK);
	}

	@DeleteMapping("/{idCliente}")
	public ResponseEntity<Cliente> deletar(@PathVariable(value = "idCliente") Long idCliente) {

		Optional<Cliente> idInternoCliente = clienteRepository.findById(idCliente);

		if (idInternoCliente.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		this.clienteRepository.deleteById(idCliente);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}