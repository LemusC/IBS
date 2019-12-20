package com.ibs.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibs.proyecto.model.Cliente;
import com.ibs.proyecto.model.Producto;
import com.ibs.proyecto.repository.IClienteRepository;
import com.ibs.proyecto.repository.IProductoRepository;
import com.ibs.proyecto.repository.IVentaRepository;

@Controller
@RequestMapping("/venta")
public class VentaController {
	
	@Autowired
	IVentaRepository vr;
	
	@Autowired
	IProductoRepository ipr;
	
	@Autowired
	IClienteRepository icr;
	
	@GetMapping(value="/index")
	public String listarVenta(Model m) {
		
		m.addAttribute("items", vr.findAll());
		
		return new String("vistas/Venta/ListarVenta");
	}
	
	@GetMapping(value="/guardarVenta")
	public String guardarCompra(Model m) {
		List<Producto> nombreProductos = (List<Producto>) ipr.findAll();
		List<Cliente> Clientes = (List<Cliente>) icr.findAll();
		
		m.addAttribute("nombreProductos", nombreProductos);
		m.addAttribute("Clientes", Clientes);
		
		return new String("vistas/Venta/AgregarVenta");
	}

}
