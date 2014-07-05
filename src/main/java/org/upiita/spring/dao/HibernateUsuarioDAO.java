package org.upiita.spring.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.upiita.spring.entity.Post;
import org.upiita.spring.entity.Usuario;

//@Service("usrDAO")
@Component("usrDAO")
@Transactional
public class HibernateUsuarioDAO implements iUsuarioDAO {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.upiita.spring.dao.iUsuarioDAO#getUsuario(int)
	 */
	@Override
	public Usuario getUsuario(int id) {
		Session sesion = sessionFactory.getCurrentSession();
		Usuario po = (Usuario) sesion.get(Usuario.class, id);
		Hibernate.initialize(po.getPosts());
		return po;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.upiita.spring.dao.iUsuarioDAO#nuevoUsuario(org.upiita.spring.entity
	 * .Usuario)
	 */
	@Override
	public Integer nuevoUsuario(Usuario usr) {
		Session sesion = sessionFactory.getCurrentSession();
		sesion.saveOrUpdate(usr);
		return usr.getId();
	}

	@Override
	public Usuario login(String email, String password) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Usuario.class);
		crit.add(Restrictions.and(Restrictions.eq("email", email),
				Restrictions.eq("password", password)));
		return (Usuario) crit.uniqueResult();
	}
}
