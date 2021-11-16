package com.tienda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Pedido;
import com.tienda.model.Usuario;
import com.tienda.repository.PedidoRepository;

@Service
public class PedidoService {
	
	//Inyecto la dependencia de la interface "PedidoRepository"
	@Autowired
	PedidoRepository pr;

	//Método para crear un nuevo pedido
	public Pedido guardarPedido(Pedido nuevoPedido) {
		return pr.save(nuevoPedido);
	}
	
	//Método que devuelve la lista de pedidos de un usuario en concreto
	public List<Pedido> obtenerPedidos(Usuario usuario){
		List<Pedido> listaPedidos =  pr.findAll();
		List<Pedido> pedidosUsuario = new ArrayList<>();
		for(Pedido pedidos: listaPedidos) {
			if(pedidos.getIdUsuario().equals(usuario.getId())) {
				pedidosUsuario.add(pedidos);
			}
		}
		return pedidosUsuario;
	}
	
	//Método que devuelve un pedido por medio de su ID correspondiente
	public Pedido buscarPedidoPorId(int id) {
		return pr.findById(id);
	}
	
	//Método que devuelve una lista de pedidos
	public List<Pedido> listarPedidos(){
		return pr.listarPedidos();
	}
}