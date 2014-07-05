package org.upiita.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.upiita.spring.dao.iPostDAO;
import org.upiita.spring.entity.Post;
import org.upiita.spring.formas.FormasSesion;
import org.upiita.spring.formas.PostForma;

@Controller
public class BlogControlador {

	/*@ModelAttribute(value = "post")
	PostForma Npost() {
		return new PostForma();
	}*/
	@Autowired
	private FormasSesion formasesion;
	

	@Autowired
	@Qualifier("postDao")
	private iPostDAO postDao;

	@RequestMapping(value = "/blog/{postId:[0-9]+}")
	public String mostrarInicio(@PathVariable(value = "postId") Integer postId,@RequestParam(required = false) Integer limit, Model model) {
		System.out.println("/Blog/blog " + postId + " limite " + limit);
		System.out.println("Numero de letras de nombre "+formasesion.getItems());
		model.addAttribute("post", postDao.getPost(postId));
		return "/Blog/post";
	}

	@RequestMapping(value = "/blog/{postId:[0-9]+}/editar")
	public String mostrarEditar(Model model,@PathVariable(value = "postId") Integer postId, Authentication au) {
		try{
			formasesion.setItems(au.getName().split("@")[0].toCharArray().length);
		}catch(NullPointerException npe){
			formasesion.setItems(1);
		}
		System.out.println(model);
		if(model.containsAttribute("post")){
			System.out.println("Error");
		}else{
			model.addAttribute("post", postDao.getPost(postId));
		}
		return "/Blog/editarpost";
	}

	@RequestMapping(value = "/blog/guardar", method = RequestMethod.POST)
	public String guardarPost(@Valid @ModelAttribute("post") PostForma forma,BindingResult validacion, RedirectAttributes atributos) {
		if (validacion.hasErrors()) {
			atributos.addFlashAttribute("post",forma);
			atributos.addFlashAttribute("org.springframework.validation.BindingResult.post",validacion);
			return "redirect:/blog/"+forma.getId()+"/editar";
		} else {
			Post postt = new Post();
			postt.setTitulo(forma.getTitulo());
			postt.setContenido(forma.getContenido());
			postt.setId(forma.getId());
			return "redirect:/Blog/blog/" + postDao.guardar(postt);
		}
		
	}

}
