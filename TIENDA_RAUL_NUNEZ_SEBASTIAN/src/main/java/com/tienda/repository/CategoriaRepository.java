package com.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tienda.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	//Método para listar las categorias de la BD a través de una Query
	@Query("select c from Categoria c")
	List<Categoria> listarCategorias();
}
