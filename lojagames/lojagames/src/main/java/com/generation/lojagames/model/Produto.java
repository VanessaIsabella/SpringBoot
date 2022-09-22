package com.generation.lojagames.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_produto")
public class Produto {

	
	    @Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	    
	    @NotBlank
	    @Size (min=5, max=100)
	    private String produto;
		
		@NotBlank(message = "O nome do Produto é obrigatório.")
		@Size(min = 5, max = 100, message = "O nome do produto deve conter entre 5 e 100 caractéres")
		private String título;
		
		@NotNull(message = "Preço é obrigatório!")
		@Positive(message = "O preço deve ser maior do que zero!")
		private BigDecimal preco;
		
		@ManyToOne
		@JsonIgnoreProperties("produto")
		private Categoria categoria;	

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTítulo() {
			return título;
		}

		public void setTítulo(String título) {
			this.título = título;
		}

		public BigDecimal getPreco() {
			return preco;
		}

		public void setPreco(BigDecimal preco) {
			this.preco = preco;
		}

		public Categoria getCategoria() {
			return categoria;
		}

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}

		public String getProduto() {
			return produto;
		}

		public void setProduto(String produto) {
			this.produto = produto;
		}
			
}
		

		