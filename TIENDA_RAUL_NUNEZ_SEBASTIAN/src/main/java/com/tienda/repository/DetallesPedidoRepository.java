package com.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.model.DetallesPedido;

public interface DetallesPedidoRepository extends JpaRepository<DetallesPedido, Integer>{

	//Método que busca el detalle del pedido por medio del ID del pedido
	List<DetallesPedido> findByIdPedido(int id);
}