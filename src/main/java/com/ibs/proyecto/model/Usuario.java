package com.ibs.proyecto.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUsuario;
	
	private String nombreUsuario;

	private String apellidoUsuario;
	
	private String duiUsuario;
	
	private String telefonoUsuario;
	
	private String direccionUsuario;
	
	private String correoUsuario;
	
	private String user;

	private String password;
	
	

	public Usuario() {
		
	}
	
	

	public Usuario(Long idUsuario, String nombreUsuario, String apellidoUsuario, String duiUsuario,
			String telefonoUsuario, String direccionUsuario, String correoUsuario, String user, String password) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.apellidoUsuario = apellidoUsuario;
		this.duiUsuario = duiUsuario;
		this.telefonoUsuario = telefonoUsuario;
		this.direccionUsuario = direccionUsuario;
		this.correoUsuario = correoUsuario;
		this.user = user;
		this.password = password;
	}



	public Usuario(String nombreUsuario, String apellidoUsuario, String duiUsuario, String telefonoUsuario,
			String direccionUsuario, String correoUsuario, String user, String password) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.apellidoUsuario = apellidoUsuario;
		this.duiUsuario = duiUsuario;
		this.telefonoUsuario = telefonoUsuario;
		this.direccionUsuario = direccionUsuario;
		this.correoUsuario = correoUsuario;
		this.user = user;
		this.password = password;
	}

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="idRol")
	private Role roles;

	//bi-directional many-to-one association to Venta
	@OneToMany(mappedBy="usuarios", fetch=FetchType.LAZY)
	private List<Venta> ventas;

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellidoUsuario() {
		return this.apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCorreoUsuario() {
		return this.correoUsuario;
	}

	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}

	public String getDireccionUsuario() {
		return this.direccionUsuario;
	}

	public void setDireccionUsuario(String direccionUsuario) {
		this.direccionUsuario = direccionUsuario;
	}

	public String getDuiUsuario() {
		return this.duiUsuario;
	}

	public void setDuiUsuario(String duiUsuario) {
		this.duiUsuario = duiUsuario;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getTelefonoUsuario() {
		return this.telefonoUsuario;
	}

	public void setTelefonoUsuario(String telefonoUsuario) {
		this.telefonoUsuario = telefonoUsuario;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Role getRoles() {
		return this.roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public List<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setUsuarios(this);

		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setUsuarios(null);

		return venta;
	}

}