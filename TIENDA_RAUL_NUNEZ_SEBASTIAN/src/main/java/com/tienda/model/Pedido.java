package com.tienda.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="pedidos", catalog = "tienda_raul_nunez_sebastian")
public class Pedido {

	//Declaración de variables y mapeo a los campos de la BD "pedidos"
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="id_usuario")
	private Integer idUsuario;
	
	@Column(name="fecha")
	private Date fecha;
	
	@Column(name="metodo_pago")
	private String metodoPago;
	
	@Column(name="estado")
	private String estado;
	
	@Column(name="num_factura")
	private String numFactura;
	
	@Column(name="total")
	private Double total;

	//Constructor por defecto sin perametrizar
	public Pedido() {

	}

	//Constructor que recibe todos los parámetros menos el ID
	public Pedido(Integer idUsuario, String metodoPago, String estado, String numFactura, Double total) {
		super();
		this.idUsuario = idUsuario;
		this.metodoPago = metodoPago;
		this.estado = estado;
		this.numFactura = numFactura;
		this.total = total;
	}

	//Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	//Método toString()
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", idUsuario=" + idUsuario + ", fecha=" + fecha + ", metodoPago=" + metodoPago
				+ ", estado=" + estado + ", numFactura=" + numFactura + ", total=" + total + "]";
	}
}