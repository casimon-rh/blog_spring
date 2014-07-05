package org.upiita.spring.testing;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.dao.iPostDAO;
import org.upiita.spring.entity.Categoria;
import org.upiita.spring.entity.Comentario;
import org.upiita.spring.entity.Post;

import static org.springframework.util.Assert.notEmpty;

public class PostDAOTest {
	
	private static ApplicationContext context;
	
	private static iPostDAO postDAO;
	
	@BeforeClass
	public static void start(){
		context = new ClassPathXmlApplicationContext("dao-context-testing.xml");
		postDAO = (iPostDAO) context.getBean("postDao");			
	}
	
	@Test
	public void getPostTest(){
		Post post= postDAO.getPost(1);
		Assert.assertNotNull("El metodo para buscar post regresa null",post);
		System.out.println("Titulo: "+post.getTitulo());
		for(Comentario cm:post.getComentarios()){
			System.out.println("Comentario: "+cm.getComentario());
		}
		for(Categoria ca:post.getCategorias()){
			System.out.println("Categorias: "+ca.getNombre());
		}
	}

	@Test
	public void savePostTest(){
		
		Post pp = new Post();
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		
		//pp.setId(3);
		pp.setTitulo("Titulo Test");
		pp.setContenido("Contenido TEST");
		pp.setFechaCreacion(fecha);
		
		Integer postIdBd=postDAO.guardar(pp);
		
		Post postBD=postDAO.getPost(postIdBd);		
		Assert.assertNotNull("El Post es Nulo",postBD);
		
		Assert.assertEquals("El Id es Distinto",pp.getId(), postBD.getId());
		Assert.assertNotNull("El Id es Nulo",postBD.getId());
		
		Assert.assertEquals("El Contenido es Distinto",pp.getContenido(), postBD.getContenido());
		Assert.assertNotNull("El Contenido es Nulo",postBD.getContenido());
		
		Assert.assertEquals("El Título es Distinto",pp.getTitulo(), postBD.getTitulo());
		Assert.assertNotNull("El Título es Nulo", postBD.getTitulo());
		
		Assert.assertEquals("La Fecha es Distinta",formato.format(pp.getFechaCreacion()), formato.format(postBD.getFechaCreacion()));
		Assert.assertNotNull("La Fecha es Nula", postBD.getFechaCreacion());		
		
	}
	@Test
	public void getByTitTest(){
		List<Post> postList= postDAO.getSimilarPost("Titulo");
		notEmpty(postList,"La colección de post es NullOrEmpty");
	}
	@Test
	public void getByNotTitTest(){
		List<Post> postList= postDAO.getDifferentPost("asdaoifhiashklklaf");
		notEmpty(postList,"La colección de post es NullOrEmpty");
	}
}
