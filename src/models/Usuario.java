package models;

import java.sql.Timestamp;

/**
 * Usuario entity. @author MyEclipse Persistence Tools
 */

public class Usuario implements java.io.Serializable {

	// Fields

	private Integer id;
	private Character estadoCivil;
	private DetallesEmpleado detallesEmpleado;
	private Integer detallesEmpleadoId;
	private String nombre;
	private String apellido;
	private String cedula;
	private String email;
	private String direccion;
	private String telefono;
	private Timestamp creacion;
	private Timestamp ultimaModificacion;
	private byte[] foto;
	private Boolean activo;
	private Timestamp nacimiento;
	private String sexo;

	// Constructors

	/** default constructor */
	public Usuario() {
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

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Timestamp getCreacion() {
		return this.creacion;
	}

	public void setCreacion(Timestamp creacion) {
		this.creacion = creacion;
	}

	public Timestamp getUltimaModificacion() {
		return this.ultimaModificacion;
	}

	public void setUltimaModificacion(Timestamp ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Timestamp getNacimiento() {
		return this.nacimiento;
	}

	public void setNacimiento(Timestamp nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Character getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(Character estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public DetallesEmpleado getDetallesEmpleado() {
		return detallesEmpleado;
	}

	public void setDetallesEmpleado(DetallesEmpleado detallesEmpleado) {
		this.detallesEmpleado = detallesEmpleado;
	}

	public Integer getDetallesEmpleadoId() {
		return detallesEmpleadoId;
	}

	public void setDetallesEmpleadoId(Integer detallesEmpleadoId) {
		this.detallesEmpleadoId = detallesEmpleadoId;
	}
}