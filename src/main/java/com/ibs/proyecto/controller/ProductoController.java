package com.ibs.proyecto.controller;

import java.util.List;
import java.text.DecimalFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibs.proyecto.model.Categoria;
import com.ibs.proyecto.model.Marca;
import com.ibs.proyecto.model.Producto;
import com.ibs.proyecto.repository.ICategoriaRepository;
import com.ibs.proyecto.repository.IMarcaRepository;
import com.ibs.proyecto.repository.IProductoRepository;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	IProductoRepository pr;

	@Autowired
	ICategoriaRepository cr;

	@Autowired
	IMarcaRepository mr;

	@GetMapping("/index")
	public String listarProducto(Model m) {
		List<Categoria> nombreCategorias = (List<Categoria>) cr.findAll();
		m.addAttribute("nombreCategorias", nombreCategorias);
		List<Marca> nombreMarcas = (List<Marca>) mr.findAll();
		m.addAttribute("nombreMarcas", nombreMarcas);
		m.addAttribute("items", pr.findAll());
		m.addAttribute("decimal", new DecimalFormat("#.##"));
		return new String("vistas/Producto/ListarProducto");
	}

	@PostMapping("/agregar")
	public String guardarProducto(@RequestParam(value = "codigoProducto", required = false) String codigoProducto,
			@RequestParam(value = "nombreProducto", required = false) String nombreProducto,
			@RequestParam(value = "presentacion", required = false) String presentacion,
			@RequestParam(value = "precioCompra", required = false) Double precioCompra,
			@RequestParam(value = "precioVenta", required = false) Double precioVenta,
			@RequestParam(value = "existencia", required = false) Integer existencia,
			@RequestParam(value = "estado", required = false) String estado,
			@RequestParam(value = "idCategoria", required = false) Long idCategoria,
			@RequestParam(value = "idMarca", required = false) Long idMarca) {

		@Valid
		Producto p = new Producto();
		p.setCodigoProducto(codigoProducto);
		p.setNombreProducto(nombreProducto);
		p.setPresentacion(presentacion);
		p.setPrecioCompra(precioCompra);
		p.setPrecioVenta(precioVenta);
		p.setExistencia(existencia);
		p.setEstado(estado);

		Categoria c = cr.findById(idCategoria).get();
		p.setCategorias(c);

		Marca m = mr.findById(idMarca).get();
		p.setMarcas(m);

		pr.save(p);

		return new String("redirect:/producto/index");
	}

	@GetMapping(value = "eliminar/{idProducto}")
	public String eliminarProducto(@PathVariable Long idProducto, Model m) {
		Producto p = pr.findById(idProducto).get();
		m.addAttribute("item", p);
		
		return new String("vistas/Producto/EliminarProducto");
	}

	@PostMapping("/eliminar")
	public String eliminarProducto(@RequestParam(value = "idProducto", required = false) Long idProducto) {

		pr.deleteById(idProducto);

		return new String("redirect:/producto/index");
	}
	
	@GetMapping(value = "modificar/{idProducto}")
	public String vistaModificar(@PathVariable Long idProducto, Model m) {
		Producto p = pr.findById(idProducto).get();
		m.addAttribute("item", p);
		List<Categoria> nombreCategorias = (List<Categoria>) cr.findAll();
		m.addAttribute("nombreCategorias", nombreCategorias);
		List<Marca> nombreMarcas = (List<Marca>) mr.findAll();
		m.addAttribute("nombreMarcas", nombreMarcas);
		
		return new String("vistas/Producto/ModificarProducto");
	}

	@PostMapping(value = "modificar")
	public String modificarProducto(@RequestParam(value = "idProducto", required = false) Long idProducto,
			@RequestParam(value = "codigoProducto", required = false) String codigoProducto,
			@RequestParam(value = "nombreProducto", required = false) String nombreProducto,
			@RequestParam(value = "presentacion", required = false) String presentacion,
			@RequestParam(value = "precioCompra", required = false) Double precioCompra,
			@RequestParam(value = "precioVenta", required = false) Double precioVenta,
			@RequestParam(value = "existencia", required = false) Integer existencia,
			@RequestParam(value = "estado", required = false) String estado,
			@RequestParam(value = "idCategoria", required = false) Long idCategoria,
			@RequestParam(value = "idMarca", required = false) Long idMarca) {

		Producto p = new Producto();
		p.setIdProducto(idProducto);
		p.setCodigoProducto(codigoProducto);
		p.setNombreProducto(nombreProducto);
		p.setPresentacion(presentacion);
		p.setPrecioCompra(precioCompra);
		p.setPrecioVenta(precioVenta);
		p.setExistencia(existencia);
		p.setEstado(estado);

		Categoria c = cr.findById(idCategoria).get();
		p.setCategorias(c);

		Marca m = mr.findById(idMarca).get();
		p.setMarcas(m);

		pr.save(p);

		return new String("redirect:/producto/index");

	}
}
