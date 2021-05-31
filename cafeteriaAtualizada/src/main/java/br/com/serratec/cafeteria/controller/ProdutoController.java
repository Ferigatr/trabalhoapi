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

import br.com.serratec.cafeteria.model.Produto;
import br.com.serratec.cafeteria.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Produto> obter() {
		return this.produtoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> obterPorId(@PathVariable(value = "id") Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(produto.get(), HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto adicionar(@RequestBody Produto produto) {
		return this.produtoRepository.save(produto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable(value = "id") Long id, @RequestBody Produto produto) {
		Optional<Produto> idInternoProduto = produtoRepository.findById(id);

		if (idInternoProduto.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		produto.setId(id);
		this.produtoRepository.save(produto);
		return new ResponseEntity<>(idInternoProduto.get(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> deletar(@PathVariable(value = "id") Long id) {

		Optional<Produto> idInternoProduto = produtoRepository.findById(id);

		if (idInternoProduto.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		this.produtoRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}