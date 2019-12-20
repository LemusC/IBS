package com.ibs.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibs.proyecto.model.Categoria;
import com.ibs.proyecto.repository.ICategoriaRepository;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	ICategoriaRepository cr;
	
	@GetMapping("/index")
	public String listarCategoria(Model m) {
		m.addAttribute("items", cr.findAll());
		return new String("vistas/Categoria/ListarCategoria");
	}
	
	@PostMapping("/agregar")
	public String agregarCategoria(@RequestParam (value = "nombreCategoria", required = false) String nombreCategoria,
									@RequestParam (value = "codigoCategoria", required = false) String codigoCategoria) {
		
		Categoria c = new Categoria(nombreCategoria, codigoCategoria);
		cr.save(c);
		
		return new String("redirect:/categoria/index");
	}
	
	@GetMapping(value= "/eliminar/{idCategoria}")
	public String vistaEliminar(@PathVariable Long idCategoria, Model m) {
		
		Categoria c = cr.findById(idCategoria).get();
		m.addAttribute("item", c);
		
		
		return new String ("vistas/Categoria/EliminarCategoria");
	}
	
	@PostMapping(value= "/eliminar")
	public String eliminarCategoria(@RequestParam(value = "idCategoria", required = false) Long idCategoria,
								@RequestParam(value = "nombreCategoria", required = false) String nombreCategoria) {
		
		cr.deleteById(idCategoria);
		
		return new String("redirect:/categoria/index");
	}
	
	@GetMapping(value = "/modificar/{idCategoria}")
	public String vistaModificar(@PathVariable Long idCategoria, Model m) {
		
		Categoria c = cr.findById(idCategoria).get();
		m.addAttribute("item", c);
		
		return new String("vistas/Categoria/ModificarCategoria");
	}
	
	@PostMapping(value = "/modificar")
	public String modificar(@RequestParam(value = "idCategoria", required = false) Long idCategoria,
							 @RequestParam(value = "nombreCategoria", required = false) String nombreCategoria,
							 @RequestParam(value = "codigoCategoria", required = false) String codigoCategoria) {
		
		Categoria c = new Categoria(idCategoria, nombreCategoria, codigoCategoria);
		cr.save(c);
		
		return new String("redirect:/categoria/index");
	}
}
