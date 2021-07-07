package com.edutecno.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.edutecno.modelo.Libro;
import com.edutecno.servicio.LibroServicio;

@Controller
public class LibroController {

	@Autowired
	LibroServicio servicio;

	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView inicio = new ModelAndView("/index");
		inicio.addObject("lista", servicio.findAll());
		return inicio;
	}

	@PostMapping("/agregar")
	public RedirectView nuevoLibro(@ModelAttribute Libro libro) {
		servicio.CrearLibro(libro);
		return new RedirectView("/listar");
	}

	@GetMapping("/eliminar/{id}")
	public RedirectView eliminar(@PathVariable(value = "id") Integer id) {

		servicio.eliminar(id);
		return new RedirectView("/listar");
	}

	@GetMapping("/modificar/{id}")
	public RedirectView modificarLibro(@PathVariable(value = "id") Integer id) {
		List<Libro> libros = servicio.findAll();
		for (Libro temp : libros) {
			if (temp.getId().equals(id)) {
				if (temp.isDisponible() == true) {
					temp.setDisponible(false);
				} else {
					temp.setDisponible(true);
				}

				servicio.modificar(temp);
			}

		}

		return new RedirectView("/listar");
	}

}
