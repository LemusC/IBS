/*
 * Fecha: 09-24-2019
 * @Jaime_Ramirez
 */
package com.ibs.proyecto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibs.proyecto.model.Usuario;
import com.ibs.proyecto.repository.IUsuarioRepository;

/**
 * The Class JpaUserDetailService.
 */
@Service("jpaUserDetailService")
public class JpaUserDetailService implements UserDetailsService {

	/** The password encoder. */
	/*@Autowired
	private BCryptPasswordEncoder passwordEncoder;*/

	/** The iuser. */
	@Autowired
	private IUsuarioRepository ur;

	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		User userResponse = null;
		Usuario user = null;
		user = ur.findByUser(usuario);
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

		if (user != null) {
			// Agregamos el rol
			roles.add(new SimpleGrantedAuthority("ROLE_" + user.getRoles().getNombreRol()));
			// Consruimos los datos del usuario para comparación
			userResponse = new User(user.getUser(),user.getPassword(), roles);
			 
		}else {
			
			System.out.println("#########################################no encontrado");
		}
		return userResponse;
	}

}
