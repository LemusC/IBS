package com.ibs.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibs.proyecto.model.Marca;
import com.ibs.proyecto.repository.IMarcaRepository;

@Controller
@RequestMapping("/marca")
public class MarcaController {

	@Autowired
	IMarcaRepository mr;
	
	@GetMapping("/index")
	public String listarMarca(Model m) {
		m.addAttribute("items", mr.findAll());
		return new String("vistas/Marca/ListarMarca");
	}
	
	@PostMapping("/agregar")
	public String agregarMarca(@RequestParam(value = "nombreMarca", required = false) String nombreMarca) {
		
		Marca m = new Marca(nombreMarca);
		mr.save(m);
		
		return new String("redirect:/marca/index");
	}
	
	@GetMapping(value = "/eliminar/{idMarca}")
	public String eliminarMarca(@PathVariable Long idMarca, Model m) {
		
		Marca ma = mr.findById(idMarca).get();
		m.addAttribute("item", ma);
		
		
		return new String ("vistas/Marca/EliminarMarca");
	}
	
	@PostMapping("/eliminar")
	public String eliminarMarca(@RequestParam(value = "idMarca", required = false) Long idMarca,
								@RequestParam(value = "nombreMarca", required = false) String nombreMarca) {
		
		mr.deleteById(idMarca);
		
		return new String("redirect:/marca/index");
	}
	
	@GetMapping(value = "/modificar/{idMarca}")
	public String vistaModificar(@PathVariable Long idMarca, Model m) {
		
		Marca ma = mr.findById(idMarca).get();
		m.addAttribute("item", ma);
		
		return new String("vistas/Marca/ModificarMarca");
	}
	
	@PostMapping(value = "modificar")
	public String modificar(@RequestParam(value = "idMarca", required = false) Long idMarca,
							 @RequestParam(value = "nombreMarca", required = false) String nombreMarca) {
		
		Marca m = new Marca(idMarca, nombreMarca);
		mr.save(m);
		
		return new String("redirect:/marca/index");
	}
	
}
