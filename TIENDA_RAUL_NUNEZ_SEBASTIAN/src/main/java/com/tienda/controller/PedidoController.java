package com.tienda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.model.DetallesPedido;
import com.tienda.model.Pedido;
import com.tienda.model.Usuario;
import com.tienda.service.DetallesPedidoService;
import com.tienda.service.PedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	DetallesPedidoService detallePedidoService;
	
	@GetMapping("/listaPedidos")
	public String vListaPedidos(Model model, HttpSession sesion) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		List<Pedido> listadoDePedidos = pedidoService.obtenerPedidos(usuario);
		model.addAttribute("listaPedidos", listadoDePedidos);
		return "/usuario/listarPedidos";
	}
	
	@GetMapping("/detallesPedido/{id}")
	public String vDetallesPedido(@PathVariable("id") int id, Model model) {
		List<DetallesPedido> listadoDetallesP = detallePedidoService.obtenerDetallePedidoPorId(id);
		model.addAttribute("detallePedido", listadoDetallesP);
		return "/usuario/listarDetallesPedido";
	}
	
	@GetMapping("/listarAllPedidos")
	public String listarAllPedidos(Model model) {
		List<Pedido> listaPedidos = (ArrayList<Pedido>)pedidoService.listarPedidos();
		List<Pedido> pedidosActualizados = new ArrayList<Pedido>();
		if(listaPedidos != null) {
			for(Pedido pedidos: listaPedidos) {
				if(pedidos.getEstado().equals("pendiente")  || pedidos.getEstado().equals("solicitud de cancelacion")) {
					pedidosActualizados.add(pedidos);
				}
			}
		}
		model.addAttribute("listadoPedidosActualizados", pedidosActualizados);
		
		return "/admin/listarPedidosClientes";
	}
	
	@GetMapping("/cancelarPedido/{id}")
	public String cancelarPedido(@PathVariable("id") int id) {
		Pedido pedido = pedidoService.buscarPedidoPorId(id);
		pedido.setEstado("solicitud de cancelacion");
		pedidoService.guardarPedido(pedido);
		return "redirect:/pedido/listaPedidos";
	}
	
	@GetMapping("/enviado/{id}")
	public String enviado(@PathVariable("id") int id) {
		Pedido pedido = pedidoService.buscarPedidoPorId(id);
		pedido.setEstado("enviado");
		pedidoService.guardarPedido(pedido);
		return "redirect:/pedido/listarAllPedidos";
	}
	
	@GetMapping("/solicitudCalcelado/{id}")
	public String solicitudCalcelado(@PathVariable("id") int id) {
		Pedido pedido = pedidoService.buscarPedidoPorId(id);
		pedido.setEstado("cancelado");
		pedidoService.guardarPedido(pedido);
		return "redirect:/pedido/listarAllPedidos";
	}
}
