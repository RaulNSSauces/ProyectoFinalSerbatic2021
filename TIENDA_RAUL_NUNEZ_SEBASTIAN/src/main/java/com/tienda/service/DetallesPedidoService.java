package com.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.DetallesPedido;
import com.tienda.repository.DetallesPedidoRepository;

@Service
public class DetallesPedidoService {
	
	//Inyecto la dependencia de la interface "DetallesPedidoRepository"
	@Autowired
	DetallesPedidoRepository dpr;
	
	//Método que devuelve todos los detalles de un pedido mediente su ID correspondiente
	public List<DetallesPedido> obtenerDetallePedidoPorId(int id){
		return dpr.findByIdPedido(id);
	}
	
	//Método que devuelve todos los detalles de un pedido
	public List<DetallesPedido> obtenerDetallesPedido(){
		return dpr.findAll();
	}
	
	//Método para crear un nuevo detalle de pedido	
	public void guardarDetallePedido(DetallesPedido nuevoDetallePedido) {
		dpr.save(nuevoDetallePedido);
	}
}