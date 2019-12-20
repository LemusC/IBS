package com.ibs.proyecto.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibs.proyecto.model.Compra;
import com.ibs.proyecto.model.Comprasproducto;
import com.ibs.proyecto.model.Producto;
import com.ibs.proyecto.model.Proveedore;
import com.ibs.proyecto.service.CompraService;

@Controller
@RequestMapping("/compra")
public class CompraController {

	@Autowired
	CompraService sCompra;

	// listado en memoria de detalles de la venta a guardar
	public static List<Comprasproducto> detalles = new ArrayList<Comprasproducto>();

	public CompraController() {

	}

	@GetMapping(value = "allCompras")
	@ResponseBody
	public Object allVentas() {

		List<HashMap<String, Object>> objetos = new ArrayList<>();

		List<Compra> c = sCompra.getAllCompra();

		for (Compra compra : c) {
			HashMap<String, Object> hm = new HashMap<>();
			hm.put("id", compra.getId().toString());
			hm.put("fechaCompra", compra.getFechaCompra().toString());
			hm.put("numeroFactura", compra.getNumeroFactura().toString());
			hm.put("tipoCompra", compra.getTipoCompra().toString());
			hm.put("totalCompra", compra.getTotalCompra().toString());
			hm.put("proveedor", compra.getProveedor().getNombreProveedor());
			hm.put("operaciones", "" + "<button class='btn btn-warning ml-2 modificar'>Modificar</button>"
					+ "<button class='btn btn-danger ml-2 eliminar'>Elmininar</button>" + "");

			objetos.add(hm);
		}

		return Collections.singletonMap("data", objetos);
	}

	@GetMapping(value = "index")
	public String index(Model model) {
		return new String("/vistas/Compra/ListarCompra");
	}

	@GetMapping(value = "guardar")
	public String guardarMostrar(Model model, @RequestParam(required = false) Proveedore proveedor) {
		model.addAttribute("productos", sCompra.getAllProductos());
		model.addAttribute("proveedor", sCompra.getAllProveedores());
		//model.addAttribute("proveedor", proveedor);
		detalles = new ArrayList<Comprasproducto>();
		// model.addAttribute("listaProductos", new ArrayList<Producto>());
		return new String("/vistas/Compra/NuevaCompra");
	}

	@PostMapping(value = "cargarProveedor")
	public String cargarProveedor(@RequestParam Long id, Model model) {
		model.addAttribute("proveedor", sCompra.getProveedor(id));
		// model.addAttribute("productos", sVenta.getAllProductos());
		// model.addAttribute("clientes", sVenta.getAllClientes());
		return "redirect:/compra/guardar";
	}

	@GetMapping(value = "/api/proveedores", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Proveedore> indexJSON() {
		return sCompra.getAllProveedores();
	}

	@GetMapping(value = "api/productos")
	@ResponseBody
	public List<Producto> productosJSON() {
		return sCompra.getAllProductos();
	}

	@PostMapping(value = "agregarDetalle")
	@ResponseBody
	public Comprasproducto agregarDetalle(@RequestParam BigInteger cantidad,
			@RequestParam Float precioCompra, @RequestParam Long idProducto) {
		Comprasproducto cp = new Comprasproducto();
		cp.setCantidad(cantidad);
		cp.setPrecioCompra(precioCompra);
		cp.setProducto(sCompra.getProducto(idProducto));
		detalles.add(cp);
		return cp;

	}

	@GetMapping(value = "allDetalles", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Comprasproducto> getDetallesMemoria() {
		return detalles;

	}

	@PostMapping(value = "save")
	@ResponseBody
	public Boolean save(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaCompra,
			@RequestParam BigInteger numeroFactura, @RequestParam String tipoCompra, @RequestParam String totalCompra,
			@RequestParam Long idProveedor) {

		Compra entity = new Compra();
		entity.setFechaCompra(fechaCompra);
		entity.setNumeroFactura(numeroFactura);
		entity.setTipoCompra(tipoCompra);
		entity.setTotalCompra(totalCompra);
		entity.setProveedor(sCompra.getProveedor(idProveedor));

		for (Comprasproducto detalleCompra : detalles) {
			detalleCompra.setCompra(entity);
		}

		entity.setComprasproductos(detalles);

		try {
			sCompra.save(entity);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}
}
