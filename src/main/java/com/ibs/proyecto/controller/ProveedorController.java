package com.ibs.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibs.proyecto.model.Proveedore;
import com.ibs.proyecto.repository.IProveedorRepository;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

	@Autowired
	IProveedorRepository pr;

	@GetMapping("/index")
	public String listarProveedor(Model m) {
		m.addAttribute("items", pr.findAll());

		return "vistas/Proveedor/ListarProveedor";
	}

	@PostMapping("/agregar")
	public String agregarProveedor(@RequestParam(value = "nombreProveedor", required = false) String nombreProveedor,
			@RequestParam(value = "apellidoProveedor", required = false) String apellidoProveedor,
			@RequestParam(value = "duiProveedor", required = false) String duiProveedor,
			@RequestParam(value = "direccionProveedor", required = false) String direccionProveedor,
			@RequestParam(value = "telefonoProveedor", required = false) String telefonoProveedor,
			@RequestParam(value = "correoProveedor", required = false) String correoProveedor) {

		Proveedore p = new Proveedore(nombreProveedor, apellidoProveedor, duiProveedor, direccionProveedor,
				telefonoProveedor, correoProveedor);

		pr.save(p);
		return "redirect:/proveedor/index";
	}
	
	@GetMapping(value = "eliminar/{idProveedor}")
		public String eliminarProveedor(@PathVariable Long idProveedor, Model m) {

			Proveedore p = pr.findById(idProveedor).get();
			m.addAttribute("item", p);
			
			return "vistas/Proveedor/EliminarProveedor";
		}

		@PostMapping("/eliminar")
		public String eliminarProveedor(@RequestParam(value = "idProveedor", required = false) Long idProveedor) {

			pr.deleteById(idProveedor);

			return "redirect:/proveedor/index";
		}

		@GetMapping(value = "modificar/{idProveedor}")
		public String vistaModificar(@PathVariable Long idProveedor, Model m) {

			Proveedore p = pr.findById(idProveedor).get();
			m.addAttribute("item", p);

			return new String("vistas/Proveedor/ModificarProveedor");
		}

		@PostMapping(value = "modificar")
		public String modificar(@RequestParam(value = "idProveedor", required = false) Long idProveedor,
						@RequestParam(value = "nombreProveedor", required = false) String nombreProveedor,
						@RequestParam(value = "apellidoProveedor", required = false) String apellidoProveedor,
						@RequestParam(value = "duiProveedor", required = false) String duiProveedor,
						@RequestParam(value = "direccionProveedor", required = false) String direccionProveedor,
						@RequestParam(value = "telefonoProveedor", required = false) String telefonoProveedor,
						@RequestParam(value = "correoProveedor", required = false) String correoProveedor) {

			Proveedore p = new Proveedore();
			p.setIdProveedor(idProveedor);
			p.setNombreProveedor(nombreProveedor);
			p.setApellidoProveedor(apellidoProveedor);
			p.setDuiProveedor(duiProveedor);
			p.setDireccionProveedor(direccionProveedor);
			p.setTelefonoProveedor(telefonoProveedor);
			p.setCorreoProveedor(correoProveedor);

			pr.save(p);

			return "redirect:/proveedor/index";
		}
		
}
