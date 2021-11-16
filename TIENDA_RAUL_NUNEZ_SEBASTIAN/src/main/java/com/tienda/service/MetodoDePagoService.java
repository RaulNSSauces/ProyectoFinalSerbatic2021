package com.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.MetodoDePago;
import com.tienda.repository.MetodoDePagoRepository;

@Service
public class MetodoDePagoService {
	
	//Inyecto la dependencia de la interface "MetodoDePagoRepository"
	@Autowired
	MetodoDePagoRepository mdpr;

	//Método que devuelve un array con todos los métodos de pago que hay en la BD
	public List<MetodoDePago> obtenerMetodoDePago(){
		return mdpr.findAll();
	}
}