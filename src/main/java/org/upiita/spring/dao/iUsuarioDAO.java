package org.upiita.spring.dao;

import org.upiita.spring.entity.Usuario;

public interface iUsuarioDAO {

	public abstract Usuario getUsuario(int id);

	public abstract Integer nuevoUsuario(Usuario usr);

	Usuario login(String email, String pass);

}