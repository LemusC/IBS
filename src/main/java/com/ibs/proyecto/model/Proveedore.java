package com.ibs.proyecto.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the proveedores database table.
 * 
 */
@Entity
@Table(name="proveedores")
@NamedQuery(name="Proveedore.findAll", query="SELECT p FROM Proveedore p")
public class Proveedore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProveedor;
	
	private String nombreProveedor;

	private String apellidoProveedor;
	
	private String duiProveedor;

	private String direccionProveedor;

	private String telefonoProveedor;
	
	private String correoProveedor;

	public Proveedore() {
	}

	public Proveedore(String nombreProveedor, String apellidoProveedor, String duiProveedor,
			String direccionProveedor, String telefonoProveedor, String correoProveedor) {
		super();
		this.nombreProveedor = nombreProveedor;
		this.apellidoProveedor = apellidoProveedor;
		this.duiProveedor = duiProveedor;
		this.direccionProveedor = direccionProveedor;
		this.telefonoProveedor = telefonoProveedor;
		this.correoProveedor = correoProveedor;
		
	}
	
	

	public Proveedore(Long idProveedor, String nombreProveedor, String apellidoProveedor, String duiProveedor,
			String direccionProveedor, String telefonoProveedor, String correoProveedor) {
		this.nombreProveedor = nombreProveedor;
		this.apellidoProveedor = apellidoProveedor;
		this.duiProveedor = duiProveedor;
		this.direccionProveedor = direccionProveedor;
		this.telefonoProveedor = telefonoProveedor;
		this.correoProveedor = correoProveedor; 
	}

	public Long getIdProveedor() {
		return this.idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getApellidoProveedor() {
		return this.apellidoProveedor;
	}

	public void setApellidoProveedor(String apellidoProveedor) {
		this.apellidoProveedor = apellidoProveedor;
	}

	public String getCorreoProveedor() {
		return this.correoProveedor;
	}

	public void setCorreoProveedor(String correoProveedor) {
		this.correoProveedor = correoProveedor;
	}

	public String getDireccionProveedor() {
		return this.direccionProveedor;
	}

	public void setDireccionProveedor(String direccionProveedor) {
		this.direccionProveedor = direccionProveedor;
	}

	public String getDuiProveedor() {
		return this.duiProveedor;
	}

	public void setDuiProveedor(String duiProveedor) {
		this.duiProveedor = duiProveedor;
	}

	public String getNombreProveedor() {
		return this.nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getTelefonoProveedor() {
		return this.telefonoProveedor;
	}

	public void setTelefonoProveedor(String telefonoProveedor) {
		this.telefonoProveedor = telefonoProveedor;
	}

}