package com.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tienda.model.MetodoDePago;

public interface MetodoDePagoRepository extends JpaRepository<MetodoDePago, Integer>{
	
	//Método que busca todos los métodos de pago almacenados en la BD
	List<MetodoDePago> findAll();
}
