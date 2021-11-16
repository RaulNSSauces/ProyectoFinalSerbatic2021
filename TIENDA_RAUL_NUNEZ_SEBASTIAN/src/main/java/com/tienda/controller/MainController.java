package com.tienda.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tienda.model.DetallesPedido;
import com.tienda.model.Producto;
import com.tienda.service.DetallesPedidoService;
import com.tienda.service.ProductoService;

@Controller
@RequestMapping("")
public class MainController {
	
	private ArrayList<Producto> listaDeProductos = new ArrayList<>();
	
	//Inyecto la dependencia de la clase "ProductoService"
	@Autowired
	ProductoService ps;
	
	//Inyecto la dependencia de la clase "DetallesPedidoService"
	@Autowired
	DetallesPedidoService dps;
	
	//Método que redirige a la vista "index" y lista los productos de la BD
	@GetMapping("")
	public String listadoProductos(Model model, HttpSession sesion) {
		if(sesion.getAttribute("carrito") == null) {
			carrito(sesion);
		}
		ArrayList<Producto> listaProductos = (ArrayList<Producto>) ps.listarProductos();
		rellenarProductos(listaProductos);
		model.addAttribute("lista", listaDeProductos);
		return "index";
	}
	
	//Método para rellenar un array con todos los productos
	public void rellenarProductos(ArrayList<Producto> lista) {
		listaDeProductos.clear();
		for(int i =0; i<lista.size(); i++) {
			Producto p = lista.get(i);
			listaDeProductos.add(p);
		}
	}
	
	//Método que crea la sesión del carrito
	public void carrito(HttpSession sesion) {
		ArrayList<DetallesPedido> listaCarrito = new ArrayList<>();
		sesion.setAttribute("carrito", listaCarrito);
	}
}