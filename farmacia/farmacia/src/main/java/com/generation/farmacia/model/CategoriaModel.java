package com.generation.farmacia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "tb_categoria")
public class CategoriaModel {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotBlank(message = "O atributo drogaria é obrigatório")
		@Size(min = 5, max =100, message = "O atributo título deve conter no mínimo 05 e no máximo 100 caracteres")
		private String medicamentos;
		
		@NotBlank(message = "O atributo higiene é obrigatório")
		@Size(min = 10, max = 100, message = "O atributo texto deve conter no mínimo 10 e no máximo 100 caracteres")
		private String higiene;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDrogaria() {
			return medicamentos;
		}

		public void setDrogaria(String drogaria) {
			this.medicamentos = medicamentos;
		}

		public String getHigiene() {
			return higiene;
		}

		public void setHigiene(String higiene) {
			this.higiene = higiene;
		}
	}

