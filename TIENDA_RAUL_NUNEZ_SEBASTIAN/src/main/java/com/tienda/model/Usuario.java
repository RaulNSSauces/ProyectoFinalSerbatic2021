package com.tienda.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;

@Entity
@Table(name="usuarios", catalog = "tienda_raul_nunez_sebastian")
public class Usuario {
	
	//Declaración de variables y mapeo a los campos de la BD "usuarios"
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="id_rol")
	private Integer idRol;
	
	@Column(name="email")
	private String email;
	
	@Column(name="clave")
	private String clave;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido1")
	private String apellido1;
	
	@Column(name="apellido2")
	private String apellido2;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="provincia")
	private String provincia;
	
	@Column(name="localidad")
	private String localidad;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="dni")
	private String dni;
	
	//Constructor por defecto sin parametrizar
	public Usuario() {

	}
	
	//Constructor que recibe todos los parámetros menos el ID
	public Usuario(Integer idRol, String email, String clave, String nombre, String apellido1, String apellido2,
			String direccion, String provincia, String localidad, String telefono, String dni) {
		this.idRol = idRol;
		this.email = email;
		this.clave = clave;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.direccion = direccion;
		this.provincia = provincia;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}

	//Constructor que recibe todos los parámetros
	public Usuario(Integer id, Integer idRol, String email, String clave, String nombre, String apellido1,
			String apellido2, String direccion, String provincia, String localidad, String telefono, String dni) {
		this.id = id;
		this.idRol = idRol;
		this.email = email;
		this.clave = clave;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.direccion = direccion;
		this.provincia = provincia;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	//Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	//Método toString()
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", idRol=" + idRol + ", email=" + email + ", clave=" + clave + ", nombre=" + nombre
				+ ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", direccion=" + direccion + ", provincia="
				+ provincia + ", localidad=" + localidad + ", telefono=" + telefono + ", dni=" + dni + "]";
	}
}