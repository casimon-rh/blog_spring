package org.upiita.spring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller	
public class ErrorControlador {
	@RequestMapping("/URL_403_ERROR")
	public ModelAndView Error403(Authentication autentication){
		System.out.println("Nombre: " +autentication.getName());
		System.out.println("Password: "+autentication.getCredentials());
		System.out.println("Roles: "+autentication.getAuthorities());
		return new ModelAndView("/Errors/AccessDenied","email",autentication.getName().split("@")[0].toUpperCase());
	}
	@RequestMapping("/URL_404_ERROR")
	public String Error404(){
		return "/Errors/PageNotFound";
	}

}
