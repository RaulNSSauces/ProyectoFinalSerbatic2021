package com.tienda.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tienda.model.Categoria;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaService cs;
	
	@Autowired
	ProductoService ps;
	
	@GetMapping("/formAltaCategoria")
	public String formAltaCategoria() {
		return "/admin/añadirCategorias";
	}
	
	@GetMapping("/listarCategorias")
	public String listarCategorias(Model model, HttpSession sesion) {
		model.addAttribute("categorias", cs.listarCategorias());
		return "/admin/listarCategorias";
	}
	
	@PostMapping("/altaCategoria")
	public String altaCategoria(@ModelAttribute Categoria nuevaCategoria, HttpSession sesion, Model model, RedirectAttributes redirect) {
		cs.crearCategoria(nuevaCategoria);
		return "redirect:/categorias/listarCategorias";
	}
	
	@GetMapping("/categoriaNaruto")
	public String categoriasNaruto(Model model, HttpSession sesion) {
		model.addAttribute("categoriaNaruto", ps.listarCategoriasNaruto());
		return "/anonimo/listarProductosNaruto";
	}
	
	@GetMapping("/categoriaDragonBall")
	public String categoriaDragonBall(Model model, HttpSession sesion) {
		model.addAttribute("categoriaDragonBall", ps.listarCategoriasDragonBall());
		return "/anonimo/listarProductosDragonBall";
	}

}
