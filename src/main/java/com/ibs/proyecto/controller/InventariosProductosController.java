package com.ibs.proyecto.controller;

import java.math.BigInteger;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibs.proyecto.model.Inventario;
import com.ibs.proyecto.model.Inventariosproducto;
import com.ibs.proyecto.model.Producto;
import com.ibs.proyecto.repository.IInventarioProductoRepository;
import com.ibs.proyecto.repository.IInventarioRepository;
import com.ibs.proyecto.repository.IProductoRepository;

@Controller
@RequestMapping("/EntradasSalidasProductos")
public class InventariosProductosController {

	@Autowired
	IInventarioProductoRepository ipr;
	
	@Autowired
	IProductoRepository pr;
	
	@Autowired
	IInventarioRepository ir;
	
	@GetMapping(value="/index")
	public String listarInventariosProductos(Model m) {
		List<Producto> nombreProductos = (List<Producto>) pr.findAll();
		List<Inventario> idInventarios = (List<Inventario>) ir.findAll();
		m.addAttribute("items", ipr.findAll());
		m.addAttribute("nombreProductos", nombreProductos);
		m.addAttribute("idInventarios", idInventarios);
		return new String("vistas/InventariosProductos/ListarInventariosProductos");
	}
	
	@GetMapping(value="/guardarMovimiento")
	public String guardarMovimiento(Model model) {
		List<Producto> nombreProductos=(List<Producto>) pr.findAll();
		model.addAttribute("nombreProductos", nombreProductos);
		
		return new String("vistas/InventariosProductos/AgregarMovimiento");
	}

	@PostMapping(value="/guardarMovimiento")
	public String guardarMovimiento(@RequestParam(value = "evento", required = false) String evento,
			@RequestParam(value = "descripcion", required = false) String descripcion, 
			@RequestParam(value = "idProducto", required = false) Long idProducto, 
			@RequestParam(value = "cantidad", required = false) BigInteger cantidad ) {
		
		@Valid 
		Inventariosproducto ip = new Inventariosproducto();
		ip.setEvento(evento);
		ip.setDescripcion(descripcion);
		ip.setCantidad(cantidad);

		
		Producto p = pr.findById(idProducto).get();

		ip.setProductos(p); 
		ipr.save(ip); 

		return new String ("redirect:/EntradasSalidasProductos/index");
	}
}
