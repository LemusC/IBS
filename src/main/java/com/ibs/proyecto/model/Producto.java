package com.ibs.proyecto.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Table(name="productos")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProducto;

	private String codigoProducto;
	
	private String nombreProducto;
	
	private String presentacion;
	
	private Double precioCompra;

	private Double precioVenta;
	
	private Integer existencia;

	private String estado;

	private Timestamp fechaIngreso;

	private Timestamp fechaModificacion;

	//bi-directional many-to-one association to Inventariosproducto
	@OneToMany(mappedBy="productos", fetch=FetchType.LAZY)
	private List<Inventariosproducto> inventariosproductos;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="idCategoria")
	private Categoria categorias;

	//bi-directional many-to-one association to Marca
	@ManyToOne
	@JoinColumn(name="idMarca")
	private Marca marcas;

	public Producto() {
	}
	
	

	public Producto(String codigoProducto, String nombreProducto, String presentacion, Double precioCompra,
			Double precioVenta, Integer existencia, String estado, Categoria categorias, Marca marcas) {
		super();
		this.codigoProducto = codigoProducto;
		this.nombreProducto = nombreProducto;
		this.presentacion = presentacion;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.existencia = existencia;
		this.estado = estado;
		this.categorias = categorias;
		this.marcas = marcas;
	}



	public Long getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigoProducto() {
		return this.codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getExistencia() {
		return this.existencia;
	}

	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}

	public Timestamp getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Timestamp getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Double getPrecioCompra() {
		return this.precioCompra;
	}

	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Double getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getPresentacion() {
		return this.presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public List<Inventariosproducto> getInventariosproductos() {
		return this.inventariosproductos;
	}

	public void setInventariosproductos(List<Inventariosproducto> inventariosproductos) {
		this.inventariosproductos = inventariosproductos;
	}

	public Inventariosproducto addInventariosproducto(Inventariosproducto inventariosproducto) {
		getInventariosproductos().add(inventariosproducto);
		inventariosproducto.setProductos(this);

		return inventariosproducto;
	}

	public Inventariosproducto removeInventariosproducto(Inventariosproducto inventariosproducto) {
		getInventariosproductos().remove(inventariosproducto);
		inventariosproducto.setProductos(null);

		return inventariosproducto;
	}

	public Categoria getCategorias() {
		return this.categorias;
	}

	public void setCategorias(Categoria categorias) {
		this.categorias = categorias;
	}

	public Marca getMarcas() {
		return this.marcas;
	}

	public void setMarcas(Marca marcas) {
		this.marcas = marcas;
	}

}