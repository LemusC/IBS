package com.ibs.proyecto.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@GetMapping(value = "/index")
	public String login() { 
		return new String("vistas/Log/index");

	}
	
	@GetMapping(value = {"/login"} )
	public String login(Model model, Principal principal, RedirectAttributes flash,@RequestParam(value="logout", required = false) String logout) { 
		if(principal != null) {
			flash.addFlashAttribute("Info","La sesión ya esta iniciada");
			return new String("redirect:/index");
		}
		return new String("vistas/Log/login");

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new String("redirect:/login?logout=true");
	}

}
