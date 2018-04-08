package models;

// default package

import java.util.HashSet;
import java.util.Set;

/**
 * Cargo entity. @author MyEclipse Persistence Tools
 */

public class Cargo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String nombre;
	private Boolean activo;

	// Constructors

	/** default constructor */
	public Cargo() {
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}