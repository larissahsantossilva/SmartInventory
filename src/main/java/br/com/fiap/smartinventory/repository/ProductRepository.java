package br.com.fiap.smartinventory.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.smartinventory.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{

	Page<Product> findByNameContaining(String name, Pageable pageable);

}
