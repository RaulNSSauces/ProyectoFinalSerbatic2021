package com.tienda.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tienda.model.Producto;
import com.tienda.service.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	//Inyecto la dependencia de la clase "ProductoService"
	@Autowired
	ProductoService ps;
	
	//Método que redirige a la vista "index" pasando por la ruta "/listaProductos" y lista los productos de la BD
	@GetMapping("/listaProductos")
	public String listadoProductos(Model model, HttpSession sesion) {
		model.addAttribute("lista", ps.listarProductos());
		return "index";
	}
	
	//Método que muestra el detalle de cada pedido haciendo clic en el botón de detalle en la vista principal
	@GetMapping("/detallePedido/{id}")
	public String detallePedido(Model model, @PathVariable("id") int id) {
		model.addAttribute("producto", ps.buscarProductoPorId(id));
		return "anonimo/detallePedido";
	}
	
	//Método que lista productos en la vista de "listarProductos" del empleado
	@GetMapping("/listadoProductos")
	public String listarProductosE(Model model, HttpSession sesion) {
		model.addAttribute("lista", ps.listarProductos());
		return "empleado/listarProductos";
	}
	
	//ALTA PRODUCTOS
	@GetMapping("/formAltaProducto")
	public String formAltaProducto() {
		return "/empleado/añadirProductos";
	}
	
	@PostMapping("/altaProducto")
	public String altaProducto(@ModelAttribute Producto nuevoProducto, HttpSession sesion, Model model, RedirectAttributes redirect) {
		ps.altaProducto(nuevoProducto);
		return "redirect:/producto/listadoProductos";
	}
	
	//ELIMINAR PRODUCTOS
	@GetMapping("/eliminarProducto/{id}")
	public String eliminarProducto(@PathVariable("id") int id, Model model) {
		ps.borrarProducto(id);
		return "redirect:/producto/listadoProductos";
	}
	
	//ACTUALIZAR PRODUCTOS
	@GetMapping("/formEditarProducto/{id}")
	public String editarProducto(@PathVariable("id") int id, Model model, HttpSession sesion) {
		Producto producto = ps.buscarProductoPorId(id);
		model.addAttribute("producto", producto);
		return "empleado/editarProductos";
	}
	
	@PostMapping("/editarProducto")
	public String formEditarPerfil(Model modelo, @ModelAttribute Producto producto) {
		ps.actualizarProducto(producto);
		return "redirect:/producto/listadoProductos";
	}
}