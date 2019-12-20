package com.ibs.proyecto.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;



/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idRol;
	
	//@Column(name = "nombreRol")
	private String nombreRol;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="roles", fetch=FetchType.LAZY)
	private List<Usuario> usuarios;

	public Role() {
	}

	public Role(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public Role(Long idRol, String nombreRol) {
		this.idRol = idRol;
		this.nombreRol = nombreRol;
	}

	public Long getIdRol() {
		return this.idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public String getNombreRol() {
		return this.nombreRol;
	}

	@Column(name = "nombreRol")
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setRoles(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setRoles(null);

		return usuario;
	}

	public Role get() {
		return null;
	}

	/*@Override
	public String toString() {
		return "Role [idRol=" + idRol + ", nombreRol=" + nombreRol + "]";
	}*/

}