package com.generation.lojagames.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojagames.model.Produto;
import com.generation.lojagames.repository.CategoriaRepository;
import com.generation.lojagames.repository.ProdutoRepository;

@RestController
@RequestMapping ("/produto")
@CrossOrigin ("*")
public class ProdutoController {

		@Autowired // passo a reponsabilidade para o spring
		private ProdutoRepository produtoRepository;
		
		@Autowired
		private CategoriaRepository categoriaRepository;
		
		@GetMapping
		public ResponseEntity<List<Produto>> getAll() {
			return ResponseEntity.ok(produtoRepository.findAll());
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Produto> getById(@PathVariable Long id) {
			return produtoRepository.findById(id)
					.map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}
		
		@GetMapping("/título/{título}")
		public ResponseEntity<List<Produto>>getByTítulo(@PathVariable String título){
			return ResponseEntity.ok(produtoRepository.findAllByTítuloContainingIgnoreCase(título));
			
		}
		
		@GetMapping("/preco/{preco}")
		public ResponseEntity<List<Produto>>getByPreco(@PathVariable BigDecimal preco){
			return ResponseEntity.ok(produtoRepository.findAllByPreco(preco));
			
		}
		
		@PostMapping                                       // exigir que o corpo da requisição seja preenchido
		public ResponseEntity<Produto> postProduto (@Valid @RequestBody Produto produto){
			if (categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		@PutMapping
		public ResponseEntity<Produto> putProduto (@Valid @RequestBody Produto produto){
			if(produtoRepository.existsById(produto.getId()))	{
				if(categoriaRepository.existsById(produto.getCategoria().getId()))
					return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				
		}
		
		@ResponseStatus(HttpStatus.NO_CONTENT)
		@DeleteMapping("{id}")
		public void delete (@PathVariable Long id) { 
			Optional <Produto> produto = produtoRepository.findById(id); // verificar no banco de dados
			// ESTÁ OU NÃO ESTÁ NO BANCO DE DADOS PELO ID
			
			if(produto.isEmpty())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND); // N ENCONTRADO
			produtoRepository.deleteById(id); // SE EXISTIR VAI CAIR AQUI
		}
}
