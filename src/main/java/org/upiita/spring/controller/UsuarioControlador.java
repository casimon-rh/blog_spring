package org.upiita.spring.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.upiita.spring.dao.iUsuarioDAO;
import org.upiita.spring.entity.Usuario;

@Controller
public class UsuarioControlador {
	
	@ModelAttribute(value="Usuario")
	Usuario newUser(){
		return new Usuario();
	}
	
	//@Resource(name="usrDAO")
	@Autowired
	@Qualifier("usrDAO")
	private iUsuarioDAO usrDAO;
	
	@RequestMapping("/usuario/{id:[0-9]+}")
	public ModelAndView inicioUsuario(@PathVariable(value="id") Integer id){
		System.out.println("Id usuario "+id);
		Usuario usr = usrDAO.getUsuario(id);
		return new ModelAndView("Usuario/usuario","usr",usr);
	}
	@RequestMapping("/usuario/{id:[0-9]+}/editar")
	public ModelAndView editarUsuario(@PathVariable(value="id") Integer id){		
		return new ModelAndView( "Usuario/eUsuario","usr", usrDAO.getUsuario(id));
	}
	
	@RequestMapping(value="/usuario/editar/do", method=RequestMethod.POST)
	public String hazEditaUsuario(@ModelAttribute Usuario usr){
		return "redirect:Usuario//usuario/"+usrDAO.nuevoUsuario(usr);
	}
}
