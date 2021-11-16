package com.tienda.model;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.*;

@Entity
@Table(name="roles", catalog = "tienda_raul_nunez_sebastian")
public class Rol {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="rol")
	private String rol;

	//Constructor por defecto sin parametrizar
	public Rol() {

	}
	
	//Constructor que recibe todos los parámetros menos el ID
	public Rol(String rol) {
		this.rol = rol;
	}

	//Constructor que recibe todos los parámetros
	public Rol(Integer id, String rol) {
		this.id = id;
		this.rol = rol;
	}

	//Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	//Método toString()
	@Override
	public String toString() {
		return "Rol [id=" + id + ", rol=" + rol + "]";
	}
}
