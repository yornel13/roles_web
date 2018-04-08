package models;

// default package

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * RolIndividual entity. @author MyEclipse Persistence Tools
 */

public class RolIndividual implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer usuarioId;
	private Usuario usuario;
	private String inicio;
	private String finalizo;
	private String detalles;
	private Timestamp fecha;
	private Double sueldo;
	private Double dias;
	private Double horasNormales;
	private Double horasSuplementarias;
	private Double horasSobreTiempo;
	private Double totalHorasExtras;
	private Double salario;
	private Double montoHorasSuplementarias;
	private Double montoHorasSobreTiempo;
	private Double bono;
	private Double transporte;
	private Double totalBonos;
	private Double vacaciones;
	private Double subtotal;
	private Double decimoTercero;
	private Double decimoCuarto;
    private Double reserva;
	private Double jubilacionPatronal;
	private Double aportePatronal;
	private Double seguros;
	private Double uniformes;
	private Double totalIngreso;
	private String empleado;
	private String cedula;
	private String empresa;
	private String rolesCliente;
    private Boolean decimosPagado;

	// Constructors

	/** default constructor */
	public RolIndividual() {
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getInicio() {
		return this.inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFinalizo() {
		return this.finalizo;
	}

	public void setFinalizo(String finalizo) {
		this.finalizo = finalizo;
	}

	public String getDetalles() {
		return this.detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Double getSueldo() {
		return this.sueldo;
	}

	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}

	public Double getDias() {
		return this.dias;
	}

	public void setDias(Double dias) {
		this.dias = dias;
	}

	public Double getHorasNormales() {
		return this.horasNormales;
	}

	public void setHorasNormales(Double horasNormales) {
		this.horasNormales = horasNormales;
	}

	public Double getHorasSuplementarias() {
		return this.horasSuplementarias;
	}

	public void setHorasSuplementarias(Double horasSuplementarias) {
		this.horasSuplementarias = horasSuplementarias;
	}

	public Double getHorasSobreTiempo() {
		return this.horasSobreTiempo;
	}

	public void setHorasSobreTiempo(Double horasSobreTiempo) {
		this.horasSobreTiempo = horasSobreTiempo;
	}

	public Double getTotalHorasExtras() {
		return this.totalHorasExtras;
	}

	public void setTotalHorasExtras(Double totalHorasExtras) {
		this.totalHorasExtras = totalHorasExtras;
	}

	public Double getSalario() {
		return this.salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Double getMontoHorasSuplementarias() {
		return this.montoHorasSuplementarias;
	}

	public void setMontoHorasSuplementarias(Double montoHorasSuplementarias) {
		this.montoHorasSuplementarias = montoHorasSuplementarias;
	}

	public Double getMontoHorasSobreTiempo() {
		return this.montoHorasSobreTiempo;
	}

	public void setMontoHorasSobreTiempo(Double montoHorasSobreTiempo) {
		this.montoHorasSobreTiempo = montoHorasSobreTiempo;
	}

	public Double getBono() {
		return this.bono;
	}

	public void setBono(Double bono) {
		this.bono = bono;
	}

	public Double getTransporte() {
		return this.transporte;
	}

	public void setTransporte(Double transporte) {
		this.transporte = transporte;
	}

	public Double getTotalBonos() {
		return this.totalBonos;
	}

	public void setTotalBonos(Double totalBonos) {
		this.totalBonos = totalBonos;
	}

	public Double getVacaciones() {
		return this.vacaciones;
	}

	public void setVacaciones(Double vacaciones) {
		this.vacaciones = vacaciones;
	}

	public Double getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getDecimoTercero() {
		return this.decimoTercero;
	}

	public void setDecimoTercero(Double decimoTercero) {
		this.decimoTercero = decimoTercero;
	}

	public Double getDecimoCuarto() {
		return this.decimoCuarto;
	}

	public void setDecimoCuarto(Double decimoCuarto) {
		this.decimoCuarto = decimoCuarto;
	}

	public Double getJubilacionPatronal() {
		return this.jubilacionPatronal;
	}

	public void setJubilacionPatronal(Double jubilacionPatronal) {
		this.jubilacionPatronal = jubilacionPatronal;
	}

	public Double getAportePatronal() {
		return this.aportePatronal;
	}

	public void setAportePatronal(Double aportePatronal) {
		this.aportePatronal = aportePatronal;
	}

	public Double getSeguros() {
		return this.seguros;
	}

	public void setSeguros(Double seguros) {
		this.seguros = seguros;
	}

	public Double getUniformes() {
		return this.uniformes;
	}

	public void setUniformes(Double uniformes) {
		this.uniformes = uniformes;
	}

	public Double getTotalIngreso() {
		return this.totalIngreso;
	}

	public void setTotalIngreso(Double totalIngreso) {
		this.totalIngreso = totalIngreso;
	}

	public String getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getRolesCliente() {
		return this.rolesCliente;
	}

	public void setRolesCliente(String rolesCliente) {
		this.rolesCliente = rolesCliente;
	}

	public Boolean getDecimosPagado() {
		return decimosPagado;
	}

	public void setDecimosPagado(Boolean decimosPagado) {
		this.decimosPagado = decimosPagado;
	}

    public Double getReserva() {
        return reserva;
    }

    public void setReserva(Double reserva) {
        this.reserva = reserva;
    }

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
}