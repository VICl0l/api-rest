package com.global_hitss.api_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global_hitss.api_rest.models.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {
	
}
