package br.com.fiap.smartinventory.controller.api;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.smartinventory.model.Product;
import br.com.fiap.smartinventory.repository.ProductRepository;


@RestController
@RequestMapping("/api/product")
public class ApiProductController {

	@Autowired
	private ProductRepository repository;
	
	@GetMapping
	@Cacheable("products")
	public Page<Product> index(
			@RequestParam(required = false) String name,
			@PageableDefault(size = 10) Pageable pageable
			
			) {
		
		if (name == null)
			return repository.findAll(pageable);
		
		return repository.findByNameContaining(name, pageable);
	}
	
	
	@PostMapping
	@CacheEvict(value = "products", allEntries = true)
	public ResponseEntity<Product> create(@RequestBody @Valid Product product,
			UriComponentsBuilder uriBuilder) {
		repository.save(product);
		
		URI uri = uriBuilder
				.path("api/product/{id}")
				.buildAndExpand(product.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(product);
	}
	
	
	@DeleteMapping("{id}")
	@CacheEvict(value = "products", allEntries = true)
	public ResponseEntity<Product> remove(@PathVariable Long id){
		Optional<Product> product = repository.findById(id);
		if (product.isEmpty())
			return ResponseEntity.notFound().build();
		
		repository.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
	
	@PutMapping("{id}")
	@CacheEvict(value = "products", allEntries = true)
	public ResponseEntity<Product> update(@PathVariable Long id,
            @RequestBody @Valid Product newProduct){ 
		
		Optional<Product> optional = repository.findById(id);
		
		if (optional.isEmpty())
			return ResponseEntity.notFound().build();
		
		Product product = optional.get();
		product.setName(newProduct.getName());
		product.setQuantity(newProduct.getQuantity());
		product.setDescription(newProduct.getDescription());
		
		repository.save(product);
		
		return ResponseEntity.ok(product);
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<Product> detail(@PathVariable Long id) {
		return ResponseEntity.of(repository.findById(id));
	}
}
