package com.tienda.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Usuario;
import com.tienda.repository.UsuarioRepository;
/**
 * 
 * @author Raul Nunez Sebastian
 * @since 1.0
 * @version 1.0
 *
 */
@Service
public class UsuarioService {
	
	Logger logger = LogManager.getLogger(UsuarioService.class); 

	//Inyecto la dependencia de la interface "UsuarioRepository"
	@Autowired
	UsuarioRepository us;
	/**
	 * buscarUsuarioPorEmail()
	 * 
	 * Metodo que busca un usuario en la BD por medio del campo email
	 * 
	 * @param email
	 * @return usuario
	 */
	//Método para buscar a un usuario a través de su email
	public Usuario buscarUsuarioPorEmail(String email) {
		Usuario usuario = us.findByEmail(email);
		if(usuario != null) {
			logger.info("Usuario con el email especificado existe");
			return usuario;
		}else {
			logger.info("Usuario con el email especificado no existe");
			return null;
		}
	}
	/**
	 * registroUsuario()
	 * 
	 * Metodo que crea un usuario nuevo en la BD
	 * 
	 * @param usuario
	 */
	//Método para registrar a un usuario
	public void registroUsuario(Usuario usuario) {
		us.save(usuario);
		logger.info("Usuario "+usuario+" se ha registrado correctamente");
	}
	
	/**
	 * eliminarUsuario()
	 * 
	 * Metodo que elimina a un usuario de la BD
	 * 
	 * @param usuario
	 */
	//Método para eliminar a un usuario
	public void eliminarUsuario(Usuario usuario) {
		us.delete(usuario);
		logger.info("Usuario eliminado correctamente");
	}
	/**
	 * borrarClientePorId()
	 * 
	 * Metodo que elimina a un cliente por medio del ID correspondiente
	 * 
	 * @param id ID del usuario
	 */
	//Método que elimina un cliente por medio de su ID
	public void borrarClientePorId(int id) {
		us.deleteById(id);
	}
	/**
	 * buscarUsuarioPorId()
	 * 
	 * Metodo que busca un usuario en la BD por medio de su ID correspondiente
	 * 
	 * @param id ID del usuario
	 * @return usuario
	 */
	//Método para buscar un usuario por medio de su ID
	public Usuario buscarUsuarioPorId(int id) {
		Usuario usuario = us.getById(id);
		if(usuario != null) {
			logger.info("Usuario con el id especificado existe");
			return usuario;
		}else {
			logger.info("Usuario con el id especificado no existe");
			return null;
		}
	}
	/**
	 * editarPerfil()
	 * 
	 * Metodo que edita el perfil del usuario que ha iniciado sesión en la aplicación
	 * 
	 * @param usuario
	 */
	//Método para editar el perfil de un usuario
	public void editarPerfil(Usuario usuario) {
		us.save(usuario);
		logger.info("Usuario "+usuario+" editado correctamente");
	}
	 /**
	  * listarUsuarios()
	  * 
	  * Metodo que almacena en una lista todos los usuarios que hay en la base de datos
	  * 
	  * @return List<> devuelve una lista de usuarios
	  */
	//Método que devuelve una lista con todos los usuarios que hay en la BD
	public List<Usuario> listarUsuarios(){
		return us.listarUsuarios();
	}
	
	/**
	 * listarUsuarioPorRol()
	 * 
	 * Metodo que almacena en una lista todos los usuarios que tengan los roles 2 o 3
	 * 
	 * @return List<> devuelve una lista de usuarios con idRol 2 y 3
	 */
	//Método que devuelves los clientes y los empleados por medio de su ID ROL
	public List<Usuario> listarUsuarioPorRol(){
		return us.listarUsuariosPorRol();
	}
	/**
	 * listarClientes()
	 * 
	 * Metodo que almacena en una lista todos los usuarios que tengan los roles 3
	 * 
	 * @return List<> devuelve una lista de usuarios con idRol 3
	 */
	//Método que devuelve los clientes por medio de su ID ROL que es 3
	public List<Usuario> listarClientes(){
		return us.listarClientes();
	}
}