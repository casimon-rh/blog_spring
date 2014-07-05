package org.upiita.spring.testing;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.dao.iUsuarioDAO;
import org.upiita.spring.entity.DatosUsuario;
import org.upiita.spring.entity.Post;
import org.upiita.spring.entity.Usuario;

public class UsuarioDAOTest {

	private static ApplicationContext context;

	private static iUsuarioDAO usuarioDAO;

	@BeforeClass
	public static void start() {
		context = new ClassPathXmlApplicationContext("dao-context-testing.xml");
		usuarioDAO = (iUsuarioDAO) context.getBean("usrDAO");
	}

	@Test
	public void getUsuarioDAO() {
		Usuario user = usuarioDAO.getUsuario(1);
		Assert.assertNotNull("El método para buscar usuario es null", user);
		for(Post p:user.getPosts()){
			System.out.println("Posts de usuario: "+p.getTitulo());
		}
	}

	@Test
	public void saveUsuario() {

		Usuario user = new Usuario();
		// user.setId(3);
		user.setEmail("usuario@test");
		user.setNombre("usuario test");

		Integer userIdBD = usuarioDAO.nuevoUsuario(user);

		Usuario userBD = usuarioDAO.getUsuario(userIdBD);
		Assert.assertNotNull("El Usuario Es Nulo", userBD);

		Assert.assertEquals("El Id es Distinto", user.getId(), userBD.getId());
		Assert.assertNotNull("El Id Es Nulo", userBD.getId());

		Assert.assertEquals("El Email es Distinto", user.getEmail(),
				userBD.getEmail());
		Assert.assertNotNull("El Email Es Nulo", userBD.getEmail());

		Assert.assertEquals("El Nombre es Distinto", user.getNombre(),
				userBD.getNombre());
		Assert.assertNotNull("El Nombre Es Nulo", userBD.getNombre());

	}

	@Test
	public void loginTest() {
		Usuario usr = new Usuario();
		usr = usuarioDAO.login("pepe@email.com", "1234");
		Assert.assertNotNull("El login es incorrecto",usr);
	}
	
	@Test
	public void MapeoTest(){
		Usuario usr = usuarioDAO.getUsuario(1);
		System.out.println(usr.getDatosUsuario());
		
		DatosUsuario du = usr.getDatosUsuario();
		System.out.println("Reverse " +du.getUsuario().getNombre() );
	}

}
