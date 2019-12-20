package com.ibs.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibs.proyecto.model.Role;
import com.ibs.proyecto.repository.IRolRepository;

@Controller
@RequestMapping("/rol")
public class RolController {

	@Autowired
	IRolRepository rr;

	// LISTAR ROLES
	@GetMapping("/index")
	public String listarRol(Model m /* ModelMap mp */) {
		m.addAttribute("items", (List<Role>) rr.findAll());
		// mp.put("items", rr.findAll());

		return "vistas/Rol/ListarRol";
	}

	/*
	 * @GetMapping("/agregar") public String vistaAgregar() { return
	 * "vistas/Rol/AgregarRol"; }
	 */

	// METODO AGREGAR ROL DESDE MODAL
	@PostMapping("/agregar")
	public String agregarRol(@RequestParam(value = "nombreRol", required = false) String nombreRol) {
		// System.out.println(nombreRol);
		Role r = new Role(nombreRol);
		rr.save(r);
		
		return "redirect:/rol/index";
	}

	// VISTA ELIMINAR ROL
	@GetMapping(value = "eliminar/{idRol}")
	public String eliminarRol(@PathVariable Long idRol, Model m) {

		Role r = rr.findById(idRol).get();
		m.addAttribute("item", r);
		
		return "vistas/Rol/EliminarRol";
	}

	// METODO ELIMINAR ROL
	@PostMapping("eliminar")
	public String eliminarRol(@RequestParam(value = "idRol", required = false) Long idRol,
			@RequestParam(value = "nombreRol", required = false) String nombreRol) {

		rr.deleteById(idRol);

		return "redirect:/rol/index";
	}

	// VISTA ACTUALIZAR ROL
	@GetMapping(value = "modificar/{idRol}")
	public String vistaModificar(@PathVariable Long idRol, Model m) {

		Role r = rr.findById(idRol).get();
		m.addAttribute("item", r);

		return new String("vistas/Rol/ModificarRol");
	}

	// METODO ACTUALIZAR ROL
	@PostMapping(value = "modificar")
	public String modificar(@RequestParam(value = "idRol", required = false) Long idRol,
			@RequestParam(value = "nombreRol", required = false) String nombreRol) {

		Role r = new Role(idRol, nombreRol);

		rr.save(r);

		return "redirect:/rol/index";
	}

}
