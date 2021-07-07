package com.edutecno.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class Libro {

	
	Integer id;
	String titulo;
	String autor;
	int anio;
	boolean disponible;
}
