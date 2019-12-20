package com.ibs.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibs.proyecto.model.Cliente;
import com.ibs.proyecto.repository.IClienteRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	IClienteRepository cr;
	
	@GetMapping("/index")
	public String listarCliente(Model m) {
		m.addAttribute("items", cr.findAll());

		return new String("vistas/Cliente/ListarCliente");
	}

	@PostMapping("/agregar")
	public String agregarCliente(@RequestParam(value = "nombreCliente", required = false) String nombreCliente,
			@RequestParam(value = "apellidoCliente", required = false) String apellidoCliente,
			@RequestParam(value = "direccionCliente", required = false) String direccionCliente,
			@RequestParam(value = "departamentoCliente", required = false) String departamentoCliente,
			@RequestParam(value = "duiCliente", required = false) String duiCliente,
			@RequestParam(value = "nrc", required = false) String nrc,
			@RequestParam(value = "nit", required = false) String nit,
			@RequestParam(value = "tipoCliente", required = false) String tipoCliente,
			@RequestParam(value = "giro", required = false) String giro,
			@RequestParam(value = "telefonoCliente", required = false) String telefonoCliente) {

		Cliente c = new Cliente(nombreCliente, apellidoCliente, direccionCliente, departamentoCliente, duiCliente, 
				nrc, nit, tipoCliente, giro, telefonoCliente);

		cr.save(c);
		return new String("redirect:/cliente/index");
	}
	
	@GetMapping(value = "eliminar/{idCliente}")
	public String vistaEliminar(@PathVariable Long idCliente, Model m) {

		Cliente c = cr.findById(idCliente).get();
		m.addAttribute("item", c);

		return new String ("vistas/Cliente/EliminarCliente");
	}

	@PostMapping("/eliminar")
	public String eliminarCliente(@RequestParam(value = "idCliente", required = false) Long idCliente) {

		cr.deleteById(idCliente);

		return new String("redirect:/cliente/index");
	}

	@GetMapping(value = "modificar/{idCliente}")
	public String vistaModificar(@PathVariable Long idCliente, Model m) {

		Cliente c = cr.findById(idCliente).get();
		m.addAttribute("item", c);

		return new String("vistas/Cliente/ModificarCliente");
	}

	@PostMapping(value = "modificar")
	public String modificar(@RequestParam(value = "idCliente", required = false) Long idCliente,
					@RequestParam(value = "nombreCliente", required = false) String nombreCliente,
					@RequestParam(value = "apellidoCliente", required = false) String apellidoCliente,
					@RequestParam(value = "direccionCliente", required = false) String direccionCliente,
					@RequestParam(value = "departamentoCliente", required = false) String departamentoCliente,
					@RequestParam(value = "duiCliente", required = false) String duiCliente,
					@RequestParam(value = "nrc", required = false) String nrc,
					@RequestParam(value = "nit", required = false) String nit,
					@RequestParam(value = "tipoCliente", required = false) String tipoCliente,
					@RequestParam(value = "giro", required = false) String giro,
					@RequestParam(value = "telefonoCliente", required = false) String telefonoCliente) {

		Cliente c = new Cliente();
		c.setIdCliente(idCliente);
		c.setNombreCliente(nombreCliente);
		c.setApellidoCliente(apellidoCliente);
		c.setDireccionCliente(direccionCliente);
		c.setDepartamentoCliente(departamentoCliente);
		c.setDuiCliente(duiCliente);
		c.setNrc(nrc);
		c.setNit(nit);
		c.setTipoCliente(tipoCliente);
		c.setGiro(giro);
		c.setTelefonoCliente(telefonoCliente);
		
		cr.save(c);

		return new String("redirect:/cliente/index");
	}
}
