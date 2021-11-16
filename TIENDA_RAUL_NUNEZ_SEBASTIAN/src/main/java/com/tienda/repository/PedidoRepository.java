package com.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tienda.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	//Método que busca pedidos mediante su ID correspondiente
	Pedido findById(int id);
	
	//Método que busca todos los pedidos almacenados en la BD
	List<Pedido> findAll();
	
	//Método que busca los pedidos por medio de su estado
	List<Pedido> findByEstado(String estado);
	
	//Método para listar los pedidos de la BD a través de una Query
	@Query("select p from Pedido p")
	List<Pedido> listarPedidos();

}