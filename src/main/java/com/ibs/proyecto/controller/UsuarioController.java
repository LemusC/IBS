package com.ibs.proyecto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibs.proyecto.model.Role;
import com.ibs.proyecto.model.Usuario;
import com.ibs.proyecto.repository.IRolRepository;
import com.ibs.proyecto.repository.IUsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	IUsuarioRepository ur;

	@Autowired
	IRolRepository rr;

	@GetMapping("/index")
	public String listarUsuario(Model m) {
		List<Role> nombreRoles = (List<Role>) rr.findAll();
		m.addAttribute("items", ur.findAll());
		m.addAttribute("nombreRoles", nombreRoles);

		return new String("vistas/Usuario/ListarUsuario");
	}

	@PostMapping("/agregar")
	public String guardarUsuario(@RequestParam(value = "nombreUsuario", required = false) String nombreUsuario,
			@RequestParam(value = "apellidoUsuario", required = false) String apellidoUsuario,
			@RequestParam(value = "duiUsuario", required = false) String duiUsuario,
			@RequestParam(value = "telefonoUsuario", required = false) String telefonoUsuario,
			@RequestParam(value = "direccionUsuario", required = false) String direccionUsuario,
			@RequestParam(value = "correoUsuario", required = false) String correoUsuario,
			@RequestParam(value = "user", required = false) String user,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "idRol", required = false) Long idRol) {

		@Valid
		Usuario u = new Usuario();
		u.setNombreUsuario(nombreUsuario);
		u.setApellidoUsuario(apellidoUsuario);
		u.setDuiUsuario(duiUsuario);
		u.setTelefonoUsuario(telefonoUsuario);
		u.setDireccionUsuario(direccionUsuario);
		u.setCorreoUsuario(correoUsuario);
		u.setUser(user);
		u.setPassword(passwordEncoder.encode(password));

		Role r = rr.findById(idRol).get();
		u.setRoles(r);

		ur.save(u);

		return new String("redirect:/usuario/index");
	}
	
	@GetMapping(value = "eliminar/{idUsuario}")
	public String eliminarUsuario(@PathVariable Long idUsuario, Model m) {
		Usuario u = ur.findById(idUsuario).get();
		m.addAttribute("item", u);
		
		return new String("vistas/Usuario/EliminarUsuario");
	}

	@PostMapping("/eliminar")
	public String eliminarUsuario(@RequestParam(value = "idUsuario", required = false) Long idUsuario) {

		ur.deleteById(idUsuario);

		return new String("redirect:/usuario/index");
	}

	@GetMapping(value = "modificar/{idUsuario}")
	public String vistaModificar(@PathVariable Long idUsuario, Model m) {
		Usuario u = ur.findById(idUsuario).get();
		m.addAttribute("item", u);
		List<Role> nombreRoles = (List<Role>) rr.findAll();
		m.addAttribute("nombreRoles", nombreRoles);

		return new String("vistas/Usuario/ModificarUsuario");
	}

	@PostMapping(value = "modificar")
	public String modificar(@RequestParam(value = "idUsuario", required = false) Long idUsuario,
			@RequestParam(value = "nombreUsuario", required = false) String nombreUsuario,
			@RequestParam(value = "apellidoUsuario", required = false) String apellidoUsuario,
			@RequestParam(value = "duiUsuario", required = false) String duiUsuario,
			@RequestParam(value = "telefonoUsuario", required = false) String telefonoUsuario,
			@RequestParam(value = "direccionUsuario", required = false) String direccionUsuario,
			@RequestParam(value = "correoUsuario", required = false) String correoUsuario,
			@RequestParam(value = "user", required = false) String user,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "idRol", required = false) Long idRol) {

		Usuario u = new Usuario();
		u.setIdUsuario(idUsuario);
		u.setNombreUsuario(nombreUsuario);
		u.setApellidoUsuario(apellidoUsuario);
		u.setDuiUsuario(duiUsuario);
		u.setTelefonoUsuario(telefonoUsuario);
		u.setDireccionUsuario(direccionUsuario);
		u.setCorreoUsuario(correoUsuario);
		u.setUser(user);
		u.setPassword(passwordEncoder.encode(password));

		Role r = rr.findById(idRol).get();
		u.setRoles(r);

		ur.save(u);

		return new String("redirect:/usuario/index");

	}
}
