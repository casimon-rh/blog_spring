package org.upiita.spring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
//usuarios_id_seq
import javax.persistence.SequenceGenerator;

@Entity(name = "usuarios")
public class Usuario {
	@Id
	@SequenceGenerator(name = "idUsuarioSecuencia", sequenceName = "usuarios_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "idUsuarioSecuencia", strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private int id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	
	@OneToOne
	@JoinColumn(name="datos_autor_id")
	private DatosUsuario datosUsuario;
	
	@OneToMany(mappedBy="usuario")
	private	List<Post> posts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DatosUsuario getDatosUsuario() {
		return datosUsuario;
	}

	public void setDatosUsuario(DatosUsuario datosUsuario) {
		this.datosUsuario = datosUsuario;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
