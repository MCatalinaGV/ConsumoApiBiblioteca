package com.edutecno.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.edutecno.modelo.Libro;

@Service
public class LibroServicio {


	@Autowired
	private RestTemplate restTemplate;
	
	
	
	public void CrearLibro(Libro libro) {
		
		HttpEntity<Libro> request = new HttpEntity<Libro>(libro);
	
		 restTemplate.postForEntity("http://localhost:8080/api/biblioteca/v1/agregar", request, String.class);
	}

	
	
	public List<Libro> findAll(){
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Libro> request = new HttpEntity<Libro>(headers);
		ResponseEntity<List<Libro>> response = restTemplate.exchange("http://localhost:8080/api/biblioteca/v1/listar", 
				HttpMethod.GET,
				request,
				new ParameterizedTypeReference<List<Libro>>() {});
		return response.getBody();
		
	}
	
	
	public void modificar(Libro libro) {
		HttpEntity<Libro> request = new HttpEntity<Libro>(libro);
	
		restTemplate.put("http://localhost:8080/api/biblioteca/v1/modificar", request, String.class);
	}
	
	
	public void eliminar(Integer id) {
		
		restTemplate.delete("http://localhost:8080/api/biblioteca/v1/eliminar/" + id);
	}
	
	
}
