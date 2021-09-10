package br.com.fiap.smartinventory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Product {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@NotBlank(message="Este campo é obrigatorio")
	private String name;
	
	@Min(value = 1)
	private int quantity;
	
	@Size(min = 10)
	private String description;
}
