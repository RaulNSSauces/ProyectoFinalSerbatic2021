package com.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Categoria;
import com.tienda.repository.CategoriaRepository;

@Service
public class CategoriaService {

	//Inyecto la dependencia de la interface "CategoriaRepository"
	@Autowired
	CategoriaRepository cr;
	
	//Método que devuelve una lista con todas las categorias
	public List<Categoria> listarCategorias(){
		return cr.listarCategorias();
	}
	
	//Método para crear nuevas categorias
	public void crearCategoria(Categoria nuevaCategoria) {
		cr.save(nuevaCategoria);
	}
}
