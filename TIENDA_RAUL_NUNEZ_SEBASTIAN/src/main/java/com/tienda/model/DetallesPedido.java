package com.tienda.model;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.*;

@Entity
@Table(name="detalles_pedido", catalog = "tienda_raul_nunez_sebastian")
public class DetallesPedido {

	//Declaración de variables y mapeo a los campos de la BD "detalles_pedido"
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="id_pedido")
	private int idPedido;
	
	@Column(name="id_producto")
	private int idProducto;
	
	@Column(name="nombre_producto")
	private String nombreProducto;
	
	@Column(name="precio_unidad")
	private double precioUnidad;
	
	@Column(name="unidades")
	private int unidades;
	
	@Column(name="impuesto")
	private Float impuesto;
	
	@Column(name="total")
	private double total;
	
	//Constructor por defecto sin perametrizar
	public DetallesPedido() {

	}
	
	//Constructor que recibe todos los parámetros menos el ID
	public DetallesPedido(int idPedido, int idProducto, String nombreProducto, double precioUnidad, int unidades,
			Float impuesto, double total) {
		this.idPedido = idPedido;
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.precioUnidad = precioUnidad;
		this.unidades = unidades;
		this.impuesto = impuesto;
		this.total = total;
	}

	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public Float getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Float impuesto) {
		this.impuesto = impuesto;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	//Método toString()
	@Override
	public String toString() {
		return "DetallesPedido [id=" + id + ", idPedido=" + idPedido + ", idProducto=" + idProducto
				+ ", nombreProducto=" + nombreProducto + ", precioUnidad=" + precioUnidad + ", unidades=" + unidades
				+ ", impuesto=" + impuesto + ", total=" + total + "]";
	}
}