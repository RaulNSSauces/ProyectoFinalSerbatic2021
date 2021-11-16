package com.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tienda.model.Producto;

public interface ProductoRepository  extends JpaRepository<Producto, Integer>{
	
	//Método para listar los productos de la BD a través de una Query
	@Query("select p from Producto p")
	List<Producto> listarProductos();
	
	//Método para buscar un producto por medio de su ID
	Producto findById(int id);
	
	@Query("select p from Producto p where p.idCategoria=1")
	List<Producto> categoriaNaruto();
	
	@Query("select p from Producto p where p.idCategoria=2")
	List<Producto> categoriaDragonBall();

}
