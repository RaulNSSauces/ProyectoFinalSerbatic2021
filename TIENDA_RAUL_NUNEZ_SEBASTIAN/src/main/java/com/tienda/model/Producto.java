package com.tienda.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name="productos", catalog = "tienda_raul_nunez_sebastian")
public class Producto {
	
	//Declaración de variables y mapeo a los campos de la BD "productos"
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="id_categoria")
	private Integer idCategoria;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="precio")
	private Double precio;
	
	@Column(name="stock")
	private Integer stock;
	
	@Column(name="fecha_alta")
	private Timestamp fechaAlta;
	
	@Column(name="fecha_baja")
	private Timestamp fechaBaja;
	
	@Column(name="impuesto")
	private Float impuesto;
	
	@Column(name="imagen")
	private String imagen;
	
	//Constructor por defecto sin parametrizar
	public Producto() {
		
	}
	
	//Constructor que recibe todos los parámetros menos el ID y la imagen
	public Producto(Integer idCategoria, String nombre, String descripcion, Double precio, Integer stock,
			Timestamp fechaAlta, Timestamp fechaBaja, Float impuesto) {
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
		this.impuesto = impuesto;
	}

	//Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Timestamp getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Timestamp getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Timestamp fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Float getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Float impuesto) {
		this.impuesto = impuesto;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	//Método toString()
	@Override
	public String toString() {
		return "Producto [id=" + id + ", idCategoria=" + idCategoria + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", precio=" + precio + ", stock=" + stock + ", fechaAlta=" + fechaAlta + ", fechaBaja="
				+ fechaBaja + ", impuesto=" + impuesto + ", imagen=" + imagen + "]";
	}
}