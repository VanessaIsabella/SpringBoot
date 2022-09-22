package com.generation.lojagames.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.lojagames.model.Categoria;
import com.generation.lojagames.model.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long>{


		public List<Produto> findAllByTítuloContainingIgnoreCase(@Param("título") String título); 
		public List<Produto> findAllByPreco(BigDecimal preco);
	
}
