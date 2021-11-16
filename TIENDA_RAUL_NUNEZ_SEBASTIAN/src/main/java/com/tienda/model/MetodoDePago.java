package com.tienda.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;

@Entity
@Table(name="metodos_pago", catalog = "tienda_raul_nunez_sebastian")
public class MetodoDePago {

	//Declaración de variables y mapeo a los campos de la BD "metodos_pago"
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="metodo_pago")
	private String metodoPago;

	//Constructor por defecto sin perametrizar
	public MetodoDePago() {

	}

	//Constructor que recibe todos los parámetros menos el ID
	public MetodoDePago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	//Método toString()
	@Override
	public String toString() {
		return "MetodoDePago [id=" + id + ", metodoPago=" + metodoPago + "]";
	}
}