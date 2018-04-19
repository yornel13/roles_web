package models;

// default package

import java.sql.Timestamp;

/**
 * DetallesEmpleado entity. @author MyEclipse Persistence Tools
 */

public class DetallesEmpleado implements java.io.Serializable {

	// Fields

	private Integer id;
	private Departamento departamento;
	private Integer empresaId;
	private Empresa empresa;
	private Integer cargoId;
	private Cargo cargo;
	private Double sueldo;
	private Double quincena;
	private Boolean acumulaDecimos;
	private String nroCuenta;
	private String extra;
	private Timestamp fechaInicio;
	private Timestamp fechaContrato;

	// Constructors

	/** default constructor */
	public DetallesEmpleado() {
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Double getSueldo() {
		return this.sueldo;
	}

	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}
        
	public Double getQuincena() {
		return quincena;
	}

	public void setQuincena(Double quincena) {
		this.quincena = quincena;
	}

	public Boolean getAcumulaDecimos() {
		return acumulaDecimos;
	}

	public void setAcumulaDecimos(Boolean acumulaDecimos) {
		this.acumulaDecimos = acumulaDecimos;
	}

	public String getNroCuenta() {
		return this.nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getExtra() {
		return this.extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Timestamp getFechaContrato() {
		return fechaContrato;
	}

	public void setFechaContrato(Timestamp fechaContrato) {
		this.fechaContrato = fechaContrato;
	}

	public Integer getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}

	public Integer getCargoId() {
		return cargoId;
	}

	public void setCargoId(Integer cargoId) {
		this.cargoId = cargoId;
	}
}