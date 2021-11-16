package com.tienda.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.model.DetallesPedido;
import com.tienda.model.MetodoDePago;
import com.tienda.model.Pedido;
import com.tienda.model.Producto;
import com.tienda.model.Usuario;
import com.tienda.service.DetallesPedidoService;
import com.tienda.service.MetodoDePagoService;
import com.tienda.service.PedidoService;
import com.tienda.service.ProductoService;

@Controller
@RequestMapping("/carrito")
public class CarritoController {
	
	//Inyecto la dependencia de la clase "ProductoService"
	@Autowired
	ProductoService ps;
	
	//Inyecto la dependencia de la clase "DetallesPedidoService"
	@Autowired
	DetallesPedidoService dps;
	
	//Inyecto la dependencia de la clase "MetodoDePagoService"
	@Autowired
	MetodoDePagoService mdps;
	
	//Inyecto la dependencia de la clase "PedidoService"
	@Autowired
	PedidoService pedidoService;

	//Método que devuelve la vista del carrito
	@RequestMapping("/vCarrito")
	public String carrito(HttpSession sesion, Model model) {
		double precioTotal = 0;
		@SuppressWarnings("unchecked")
		ArrayList<DetallesPedido> listaCarrito = (ArrayList<DetallesPedido>) sesion.getAttribute("carrito");
		if(listaCarrito.size() >= 1) {
			for(DetallesPedido productos: listaCarrito) {
				precioTotal = precioTotal + productos.getTotal();
			}
		}
		model.addAttribute("precioTotal", precioTotal);
		return "/carrito/vCarrito";
	}
	
	//Método para insertar productos al carrito mediente su ID correspondiente
	@GetMapping("/agregarCarrito/{id}")
	public String agregarProducto(@PathVariable("id") int id, Model model, HttpSession sesion) {
		boolean existe = false;
		Producto p = ps.buscarProductoPorId(id);
		if(p != null) {
			@SuppressWarnings("unchecked")
			ArrayList<DetallesPedido> listaCarrito = (ArrayList<DetallesPedido>) sesion.getAttribute("carrito");
			if(listaCarrito != null) {
				for(DetallesPedido dp: listaCarrito) {
					if(dp.getIdProducto()==id) {
						if(p.getStock() > dp.getUnidades()) {
							Integer nProducto = dp.getUnidades()+1;
							double total = dp.getPrecioUnidad()*nProducto;
							dp.setUnidades(nProducto);
							dp.setTotal(total);
						}
						
						existe = true;
					}
				}
			}
			if(existe == false) {
				DetallesPedido nuevoDetallePedido = new DetallesPedido(1, p.getId(), p.getNombre(), p.getPrecio(), 1, p.getImpuesto(), p.getPrecio());
				listaCarrito.add(nuevoDetallePedido);
			}
		}
		//model.addAttribute("precioTotal", precioTotal);
		return "redirect:/";
	}
	
	//Método para eliminar productos del carrito mediante su ID correspondiente
	@GetMapping("/eliminarCarrito/{id}")
	public String eliminarProducto(@PathVariable("id") int id, Model model, HttpSession sesion) {
		@SuppressWarnings("unchecked")
		ArrayList<DetallesPedido> listaCarrito = (ArrayList<DetallesPedido>) sesion.getAttribute("carrito");
		if(listaCarrito != null) {
			for(DetallesPedido productosCarrito: listaCarrito){
				if(productosCarrito != null) {
					if(productosCarrito.getIdProducto() == id) {
						int contador = productosCarrito.getUnidades()-1;
						if(contador<=0) {
							listaCarrito.remove(productosCarrito);
							return "redirect:/carrito/vCarrito";
						}else {
							productosCarrito.setUnidades(contador);
							double total = productosCarrito.getPrecioUnidad()*contador;
							productosCarrito.setTotal(total);
						}
					}
				}
			}
		}
		return "redirect:/carrito/vCarrito";
	}
	
	//Método que devuelve la vista de confirmar la compra del pedido
	@GetMapping("/confirmarCompra")
	public String confirmarPedido(HttpSession sesion, Model model) {
		Usuario sesionDelusuario = (Usuario) sesion.getAttribute("usuario");
		if(sesionDelusuario == null) {
			return "redirect:/usuario/formAlta";
		}else {
			@SuppressWarnings("unchecked")
			ArrayList<DetallesPedido> listaDetallePedido = (ArrayList<DetallesPedido>) sesion.getAttribute("carrito");
			if(listaDetallePedido.size() <=0) {
				return "redirect:/";
			}
			double total = 0;
			if(listaDetallePedido.size()>=1) {
				for(DetallesPedido p: listaDetallePedido) {
					total = total+p.getTotal();
				}
			}
			Pedido nuevoPedido = new Pedido (sesionDelusuario.getId(), null, "pendiente", null, total);
			sesion.setAttribute("pedidos", nuevoPedido);
			List<MetodoDePago> metodoPago = mdps.obtenerMetodoDePago();
			model.addAttribute("metodoPago", metodoPago);
			return "/carrito/confirmarCompra";
		}
	}
	
	//Método para finalizar la compra
	@PostMapping("/finalizarCompra")
	public String finalizarCompra(@ModelAttribute Pedido pedido, HttpSession sesion, Model model) {
		Pedido sesionPedido = (Pedido) sesion.getAttribute("pedidos");
		sesionPedido.setMetodoPago(pedido.getMetodoPago());
		@SuppressWarnings("unchecked")
		ArrayList<DetallesPedido> listaDetallePedido = (ArrayList<DetallesPedido>) sesion.getAttribute("carrito");
		Pedido nuevoPedido = pedidoService.guardarPedido(sesionPedido);
		for(DetallesPedido p: listaDetallePedido) {
			p.setIdPedido(nuevoPedido.getId());
			Producto producto = ps.buscarProductoPorId(p.getIdProducto());
			producto.setStock(producto.getStock()-p.getUnidades());
			ps.actualizarProducto(producto);
			dps.guardarDetallePedido(p);
		}
		sesion.removeAttribute("pedidos");
		sesion.setAttribute("carrito", new ArrayList<Producto>());
		
		return "redirect:/carrito/finCompra";
	}
	
	//Método que devuelve la vista de la compra finalizada
	@GetMapping("/finCompra")
	public String compraFinalizada(HttpSession sesion) {
		return "/carrito/compraFinalizada";
	}
	
	//Método que devuelve al index de la tienda
	@GetMapping("/volverTienda")
	public String volverTienda(HttpSession sesion) {
		return "redirect:/";
	}
}