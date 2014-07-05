package org.upiita.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
import org.upiita.spring.entity.Post;

//@Service("postDao")
@Component("postDao")
public class HibernatePostDAO implements iPostDAO {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.upiita.spring.dao.iPostDAO#getPost(int)
	 */
	@Override
	public Post getPost(int id) {
		// Transaccion programática
		Session sesion = sessionFactory.openSession();
		sesion.beginTransaction();
		Post po = (Post) sesion.get(Post.class, id);
		Hibernate.initialize(po.getComentarios());
		Hibernate.initialize(po.getCategorias());
		sesion.close();
		return po;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.upiita.spring.dao.iPostDAO#guardar(org.upiita.spring.entity.Post)
	 */
	@Override
	public Integer guardar(Post post) {
		Session sesion = sessionFactory.openSession();
		sesion.beginTransaction();
		sesion.saveOrUpdate(post);
		sesion.getTransaction().commit();
		sesion.close();
		return post.getId();
	}

	@Override
	public List<Post> getSimilarPost(String bus) {
		Session sesion = sessionFactory.openSession();
		sesion.beginTransaction();
		Criteria criterio = sesion.createCriteria(Post.class);
		criterio.add(Restrictions.like("titulo", "%" + bus + "%"));
		List<Post> postList = criterio.list();
		sesion.close();
		return postList;

	}

	@Override
	public List<Post> getDifferentPost(String bus) {
		Session sesion = sessionFactory.openSession();
		sesion.beginTransaction();
		Criteria criterio = sesion.createCriteria(Post.class);
		criterio.add(Restrictions.not(Restrictions.like("titulo", "%" + bus
				+ "%")));
		List<Post> postList = criterio.list();
		sesion.close();
		return postList;

	}

}
