package com.tienda.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Producto;
import com.tienda.repository.ProductoRepository;

@Service
public class ProductoService {
	
	Logger logger = LogManager.getLogger(UsuarioService.class); 

	//Inyecto la dependencia de la interface "ProductoRepository"
	@Autowired
	ProductoRepository pr;
	
	//Método para obtener un producto por medio de su ID
	public Producto buscarProductoPorId(int id) {
		Producto p = pr.findById(id);
		if(p != null) {
			logger.info("Producto con el id especificado existe");
			return p;
		}else {
			logger.info("Producto con el id especificado no existe");
			return null;
		}
	}
	
	//Método para listar los productos
	public List<Producto> listarProductos(){
		List<Producto> lista = pr.listarProductos();
		return lista;
	}
	
	//Método para crear nuevos productos
	public void altaProducto(Producto producto) {
		pr.save(producto);
		logger.info("Producto creado correctamente");
	}
	
	//Método para eliminar productos por medio de su ID
	public void borrarProducto(int id) {
		pr.deleteById(id);
		logger.info("Producto eliminado correctamente");
	}
	
	//Método para actualizar productos
	public void actualizarProducto(Producto producto) {
		pr.save(producto);
		logger.info("Producto actualizado correctamente");
	}
	
	//Método que devuelve una lista con las categoría de Naruto
	public List<Producto> listarCategoriasNaruto(){
		return pr.categoriaNaruto();
	}
		
	//Método que devuelve una lista con las categorías de Dragon Ball
	public List<Producto> listarCategoriasDragonBall(){
		return pr.categoriaDragonBall();
	}
}