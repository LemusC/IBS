package com.ibs.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibs.proyecto.model.Compra;
import com.ibs.proyecto.model.Inventariosproducto;
import com.ibs.proyecto.model.Venta;
import com.ibs.proyecto.repository.ICompraRepository;
import com.ibs.proyecto.repository.IInventarioProductoRepository;
import com.ibs.proyecto.repository.IInventarioRepository;

import com.ibs.proyecto.repository.IVentaRepository;

@Controller
@RequestMapping("/inventario")
public class InventarioController {
	
	@Autowired
	IInventarioRepository ir;
	
	@Autowired
	ICompraRepository cr;
	
	@Autowired
	IVentaRepository vr;
	
	@Autowired
	IInventarioProductoRepository ipr;
	
	// LISTAR INVENTARIO
		@GetMapping("/index")
		public String listarInventario(Model m  /*ModelMap mp */) {
			
			m.addAttribute("items", ir.findAll());
			List<Compra> compra = (List<Compra>) cr.findAll();
			m.addAttribute("compra", compra);
			List<Venta> venta = (List<Venta>) vr.findAll();
			m.addAttribute("venta", venta);
			List<Inventariosproducto> inventariosProducto = (List<Inventariosproducto>) ipr.findAll();
			m.addAttribute("inventariosProducto", inventariosProducto);
			
			
			return new String ("vistas/Inventario/ListarInventario");
		}
	
}
