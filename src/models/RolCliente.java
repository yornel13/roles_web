package models;

// default package

import java.sql.Timestamp;

import static utilidad.Numeros.round;

/**
 * Pago entity. @author MyEclipse Persistence Tools
 */

public class RolCliente implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer clienteId;
	private Integer usuarioId;
	private String detalles;
	private String inicio;
	private String finalizo;
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
	private Double montoHorasExtras;
	private Double bono;
	private Double transporte;
	private Double totalBonos;
	private Double vacaciones;
	private Double subtotal;
	private Double decimoTercero;
	private Double decimoCuarto;
	private Double jubilacionPatronal;
	private Double aportePatronal;
	private Double seguros;
	private Double uniformes;
	private Double totalIngreso;
	private String empleado;
	private String cedula;
	private String empresa;
	private String clienteNombre;
	private Cliente cliente;
	private Usuario usuario;

	// Constructors

	/** default constructor */
	public RolCliente() {
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

        public String getDetalles() {
            return detalles;
        }

        public void setDetalles(String detalles) {
            this.detalles = detalles;
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
		return round(this.horasNormales);
	}

	public void setHorasNormales(Double horasNormales) {
		this.horasNormales = horasNormales;
	}

	public Double getHorasSuplementarias() {
		return round(this.horasSuplementarias);
	}

	public void setHorasSuplementarias(Double horasSuplementarias) {
		this.horasSuplementarias = horasSuplementarias;
	}

	public Double getHorasSobreTiempo() {
		return round(this.horasSobreTiempo);
	}

	public void setHorasSobreTiempo(Double horasSobreTiempo) {
		this.horasSobreTiempo = horasSobreTiempo;
	}

	public Double getTotalHorasExtras() {
		return round(this.totalHorasExtras);
	}

	public void setTotalHorasExtras(Double totalHorasExtras) {
		this.totalHorasExtras = totalHorasExtras;
	}

	public Double getSalario() {
		return round(this.salario);
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Double getMontoHorasSuplementarias() {
		return round(this.montoHorasSuplementarias);
	}

	public void setMontoHorasSuplementarias(Double montoHorasSuplementarias) {
		this.montoHorasSuplementarias = montoHorasSuplementarias;
	}

	public Double getMontoHorasSobreTiempo() {
		return round(this.montoHorasSobreTiempo);
	}

	public void setMontoHorasSobreTiempo(Double montoHorasSobreTiempo) {
		this.montoHorasSobreTiempo = montoHorasSobreTiempo;
	}

	public Double getBono() {
		return round(this.bono);
	}

	public void setBono(Double bono) {
		this.bono = bono;
	}

	public Double getTransporte() {
		return round(this.transporte);
	}

	public void setTransporte(Double transporte) {
		this.transporte = transporte;
	}

	public Double getTotalBonos() {
		return round(this.totalBonos);
	}

	public void setTotalBonos(Double totalBonos) {
		this.totalBonos = totalBonos;
	}
        
        public Double getVacaciones() {
            return round(vacaciones);
        }

        public void setVacaciones(Double vacaciones) {
            this.vacaciones = vacaciones;
        }

	public Double getSubtotal() {
		return round(this.subtotal);
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getDecimoTercero() {
		return round(this.decimoTercero);
	}

	public void setDecimoTercero(Double decimoTercero) {
		this.decimoTercero = decimoTercero;
	}

	public Double getDecimoCuarto() {
		return round(this.decimoCuarto);
	}

	public void setDecimoCuarto(Double decimoCuarto) {
		this.decimoCuarto = decimoCuarto;
	}

	public Double getJubilacionPatronal() {
		return round(this.jubilacionPatronal);
	}

	public void setJubilacionPatronal(Double jubilacionPatronal) {
		this.jubilacionPatronal = jubilacionPatronal;
	}

	public Double getAportePatronal() {
		return round(this.aportePatronal);
	}

	public void setAportePatronal(Double aportePatronal) {
		this.aportePatronal = aportePatronal;
	}

	public Double getSeguros() {
		return round(this.seguros);
	}

	public void setSeguros(Double seguros) {
		this.seguros = seguros;
	}

	public Double getUniformes() {
		return round(this.uniformes);
	}

	public void setUniformes(Double uniformes) {
		this.uniformes = uniformes;
	}

	public Double getTotalIngreso() {
		return round(this.totalIngreso);
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

	public String getClienteNombre() {
		return this.clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	} 

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getMontoHorasExtras() {
		return montoHorasExtras;
	}

	public void setMontoHorasExtras(Double montoHorasExtras) {
		this.montoHorasExtras = montoHorasExtras;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
}