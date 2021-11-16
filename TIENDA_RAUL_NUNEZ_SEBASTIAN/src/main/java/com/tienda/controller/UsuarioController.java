package com.tienda.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tienda.model.Usuario;
import com.tienda.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	Base64 base64 = new Base64();
	//Inyecto la dependencia de la clase "UsuarioService"
	@Autowired
	UsuarioService us;
	
	//Método que redirige al formulario de "vLogin" para que el usuario inicie sesión en la app
	@GetMapping("/formAlta")
	public String formAlta(HttpSession sesion) {
		return "/usuario/vLogin";
	}
	
	//Método que redirige a la vista "inicio" cuando el usuario ha iniciado sesión correctamente en la app
	@GetMapping("/inicioUsuario")
	public String inicio(HttpSession sesion) {
		return "redirect:/";
	}
	
	//Método que redirige al formulario de "vRegistro" en caso de que el usuario quiera registrarse en la app
	@GetMapping("/formRegistro")
	public String registro(HttpSession sesion) {
		return "/usuario/vRegistro";
	}
	
	//Método que redirige al formulario de "vEditar" en caso de que el usuario quiera editar su perfil
	@GetMapping("/formEditar")
	public String formEditarPerfil(Model modelo, HttpSession sesion) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		modelo.addAttribute("usuario", usuario);
		return "/usuario/vEditar";
	}
	
	//Método que redirige al formulario de "vPerfil" en caso de que el usuario quiera ver su perfil
	@GetMapping("/formEditarPerfil")
	public String verPerfil(Model modelo, HttpSession sesion) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		modelo.addAttribute("usuario", usuario);
		return "/usuario/vPerfil";
	}
	
	@GetMapping("/atras")
	public String volver(HttpSession sesion) {
		if(sesion.getAttribute("usuario") == null) {
			return "redirect:/";
		}else {
			return "redirect:/usuario/inicioUsuario";
		}
	}
	
	//Método que comprueba si el email y el password del usuario son correctos para poder iniciar sesión en la app
	@PostMapping("/iniciarSesion")
	public String altaUsuario(HttpSession sesion, Model model, @ModelAttribute Usuario usuario) {
		Usuario u = us.buscarUsuarioPorEmail(usuario.getEmail());
		if(u != null) {
			String claveEncriptada = new String(base64.encode(usuario.getClave().getBytes()));
			if(u.getClave().equals(claveEncriptada)) {
				sesion.setAttribute("usuario", u);
				return "redirect:/usuario/inicioUsuario";
			}else {
				model.addAttribute("mensaje", "El usuario y/o la contraseña son incorrectos");
				return "/usuario/vLogin";
			}
		}else {
			model.addAttribute("mensaje", "El usuario y/o la contraseña son incorrectos");
			return "/usuario/vLogin";
		}
	}
	
	//Método para que un usuario se registre en la app
	@PostMapping("/registroUsuario")
	public String registroUsuario(@ModelAttribute Usuario usuarioNuevo, HttpSession sesion, Model model, RedirectAttributes redirect) {
		String claveEncriptada = new String(base64.encode(usuarioNuevo.getClave().getBytes()));
		usuarioNuevo.setClave(claveEncriptada);
		usuarioNuevo.setIdRol(3);
		us.registroUsuario(usuarioNuevo);
		return "redirect:/usuario/formAlta";
	}
	
	//Método para que un usuario elimine su usuario
	@PostMapping("/eliminarUsuario")
	public String eliminarUsuario(@ModelAttribute Usuario usuario, HttpSession sesion) {
		us.eliminarUsuario(usuario);
		sesion.setAttribute("usuario", null);
		return "/usuario/vLogin";
	}
	
	//Método para editar el perfil del usuario logueado
	@PostMapping("/editarPerfil")
	public String editarPerfil(@Valid @ModelAttribute Usuario usuario, HttpSession sesion, BindingResult bindingResult) {
		us.editarPerfil(usuario);
		System.out.println(usuario.getApellido1());
		System.out.println(usuario.getApellido2());
		sesion.setAttribute("usuario", usuario);
		return "redirect:/usuario/inicioUsuario";
	}
	
	//LISTAR CLIENTES
	@GetMapping("/listadoClientes")
	public String listarClientesE(Model model, HttpSession sesion) {
		model.addAttribute("listaClientes", us.listarClientes());
		return "empleado/listarClientes";
	}
	
	//ALTA CLIENTES
	@GetMapping("/formAltaCliente")
	public String formAltaCliente(HttpSession sesion) {
		return "/empleado/añadirClientes";
	}
	
	@PostMapping("/altaCliente")
	public String altaCliente(@ModelAttribute Usuario nuevoCliente, HttpSession sesion, Model model, RedirectAttributes redirect) {
		String claveEncriptada = new String(base64.encode(nuevoCliente.getClave().getBytes()));
		nuevoCliente.setClave(claveEncriptada);
		nuevoCliente.setIdRol(3);
		us.registroUsuario(nuevoCliente);
		return "redirect:/usuario/listadoClientes";
	}
	
	//ELIMINAR CLIENTES
	@GetMapping("/eliminarCliente/{id}")
	public String eliminarCliente(@PathVariable("id") int id, Model model, HttpSession sesion) {
		us.borrarClientePorId(id);
		return "redirect:/usuario/listadoClientes";
	}
	
	//ACUALIZAR CLIENTES
	@GetMapping("/formEditarCliente/{id}")
	public String editarCliente(@PathVariable("id") int id, Model model, HttpSession sesion) {
		Usuario usuario = us.buscarUsuarioPorId(id);
		model.addAttribute("usuario", usuario);
		return "empleado/editarClientes";
	}
	
	@PostMapping("/editarCliente")
	public String formEditarCliente(Model modelo, @ModelAttribute Usuario cliente, HttpSession sesion) {
		us.editarPerfil(cliente);
		return "redirect:/usuario/listadoClientes";
	}
	
	//LISTAR EMPLEADOS
	@GetMapping("/listadoEmpleados")
	public String listarEmpleados(Model model, HttpSession sesion) {
		model.addAttribute("listaEmpleados", us.listarUsuarioPorRol());
		return "admin/listarEmpleados";
	}
	
	//ALTA EMPLEADOS
	@GetMapping("/formAltaEmpleado")
	public String formAltaEmplado(HttpSession sesion) {
		return "/admin/añadirEmpleados";
	}
		
	@PostMapping("/altaEmpleado")
	public String altaEmpleado(@ModelAttribute Usuario nuevoEmpleado, HttpSession sesion, Model model, RedirectAttributes redirect) {
		String claveEncriptada = new String(base64.encode(nuevoEmpleado.getClave().getBytes()));
		nuevoEmpleado.setClave(claveEncriptada);
		nuevoEmpleado.setIdRol(2);
		us.registroUsuario(nuevoEmpleado);
		return "redirect:/usuario/listadoEmpleados";
	}
	
	//ELIMINAR EMPLEADOS
	@GetMapping("/eliminarEmpleado/{id}")
	public String eliminarEmpleado(@PathVariable("id") int id, Model model, HttpSession sesion) {
		us.borrarClientePorId(id);
		return "redirect:/usuario/listadoEmpleados";
	}
	
	//ACTUALIZAR EMPLEADOS
	@GetMapping("/formEditarEmpleado/{id}")
	public String editarEmpleado(@PathVariable("id") int id, Model model, HttpSession sesion) {
		Usuario empleado = us.buscarUsuarioPorId(id);
		model.addAttribute("usuario", empleado);
		return "admin/editarEmpleados";
	}
	
	@PostMapping("/editarEmpleado")
	public String formEditarEmpleado(Model modelo, @ModelAttribute Usuario empleado, HttpSession sesion) {
		us.editarPerfil(empleado);
		empleado.setIdRol(2);
		return "redirect:/usuario/listadoEmpleados";
	}
	
	//CAMBIAR CONTRASEÑA
	@GetMapping("/formCambiarPass")
	public String formCambiarPass(Model model, HttpSession sesion) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		model.addAttribute("usuario", usuario);
		return "usuario/cambiarPassword";
	}
	
	@PostMapping("/cambiarPass")
	public String cambiarPass(@ModelAttribute Usuario usuario, HttpSession sesion, RedirectAttributes redirect) {
		String claveEncriptada = new String(base64.encode(usuario.getClave().getBytes()));
		usuario.setClave(claveEncriptada);
		us.registroUsuario(usuario);
		return "redirect:/";
	}
	
	//Método para que el usuario pueda cerrar sesión en la app
	@GetMapping("/cerrarSesion")
	public String cerrarSesion(HttpSession sesion) {
		sesion.setAttribute("usuario", null);
		return "redirect:/";
	}
}