package com.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tienda.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	//Método para buscar un usuario por medio de su ID
	Usuario findById(int id);
	
	//Método para buscar un usuario por medio de su Email
	Usuario findByEmail(String email);
	
	//Método para eliminar un usuario por medio de su ID
	Usuario deleteById(int id);
	
	//Método para listar los usuarios de la BD a través de una Query
	@Query("select u from Usuario u")
	List<Usuario> listarUsuarios();
	
	//Método para listar todos los clientes y los empleados cuyo ID ROL sea 2 o 3
	@Query("select u from Usuario u where u.idRol=2 or u.idRol=3")
	List<Usuario> listarUsuariosPorRol();
	
	//Método para listar empleados cuyo ID ROL es 2
	@Query("select u from Usuario u where u.idRol=3")
	List<Usuario> listarClientes();
	
}
