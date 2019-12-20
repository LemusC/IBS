package com.ibs.proyecto.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.math.BigInteger;

/**
 * The persistent class for the compras database table.
 * 
 */
@Entity
@Table(name="compras")
@NamedQuery(name="Compra.findAll", query="SELECT c FROM Compra c")
public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
 
	@DateTimeFormat(pattern = "yyy-mm-dd")
	private Date fechaCompra;

	private Timestamp fechaIngreso;

	private BigInteger numeroFactura;

	private String tipoCompra;

	private String totalCompra;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Proveedore proveedor;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "compra")
	private List<Comprasproducto> comprasproductos;

	public Compra() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaCompra() {
		return this.fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Timestamp getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public BigInteger getNumeroFactura() {
		return this.numeroFactura;
	}

	public void setNumeroFactura(BigInteger numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getTipoCompra() {
		return this.tipoCompra;
	}

	public void setTipoCompra(String tipoCompra) {
		this.tipoCompra = tipoCompra;
	}

	public String getTotalCompra() {
		return this.totalCompra;
	}

	public void setTotalCompra(String totalCompra) {
		this.totalCompra = totalCompra;
	}

	public Proveedore getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedore proveedor) {
		this.proveedor = proveedor;
	}

	public List<Comprasproducto> getComprasproductos() {
		return this.comprasproductos;
	}

	public void setComprasproductos(List<Comprasproducto> comprasproductos) {
		this.comprasproductos = comprasproductos;
	}
}