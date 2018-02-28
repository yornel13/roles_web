package models;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Usuario entity. @author MyEclipse Persistence Tools
 */

public class Usuario implements java.io.Serializable {

	// Fields

	private Integer id;
	private Character estadoCivil;
	//private DetallesEmpleado detallesEmpleado;
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
        private Set pagos = new HashSet(0);
	private Set identidads = new HashSet(0);
        private Set controlDiarios = new HashSet(0);
        private Set controlExtrases = new HashSet(0);
	private Set bonos = new HashSet(0);
        private Set diasVacaciones = new HashSet(0);
	private Set registroAccioneses = new HashSet(0);
	private Set roleses = new HashSet(0);
        private Set deudas = new HashSet(0);
        private Set pagoQuincenas = new HashSet(0);
        private Set pagoMeses = new HashSet(0);
        private Set rolIndividuals = new HashSet(0);
        private Set fotos = new HashSet(0);
        private Set planillaIesses = new HashSet(0);
        private Set pagoVacacioneses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Usuario() {
	}

	/** minimal constructor */
	public Usuario(String nombre, String apellido, String cedula, String email,
			String direccion, Timestamp creacion, Timestamp ultimaModificacion,
			Boolean activo, Timestamp nacimiento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.cedula = cedula;
		this.email = email;
		this.direccion = direccion;
		this.creacion = creacion;
		this.ultimaModificacion = ultimaModificacion;
		this.activo = activo;
		this.nacimiento = nacimiento;
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

	public Set getIdentidads() {
		return this.identidads;
	}

	public void setIdentidads(Set identidads) {
		this.identidads = identidads;
	}

	public Set getBonos() {
		return this.bonos;
	}

	public void setBonos(Set bonos) {
		this.bonos = bonos;
	}

	public Set getRegistroAccioneses() {
		return this.registroAccioneses;
	}

	public void setRegistroAccioneses(Set registroAccioneses) {
		this.registroAccioneses = registroAccioneses;
	}

	public Set getRoleses() {
		return this.roleses;
	}

	public void setRoleses(Set roleses) {
		this.roleses = roleses;
	}

        public Set getPagos() {
            return pagos;
        }

        public void setPagos(Set pagos) {
            this.pagos = pagos;
        }

        public Set getDeudas() {
            return deudas;
        }

        public void setDeudas(Set deudas) {
            this.deudas = deudas;
        }

        public Set getPagoQuincenas() {
            return pagoQuincenas;
        }

        public void setPagoQuincenas(Set pagoQuincenas) {
            this.pagoQuincenas = pagoQuincenas;
        }

        public Set getPagoMeses() {
            return pagoMeses;
        }

        public void setPagoMeses(Set pagoMeses) {
            this.pagoMeses = pagoMeses;
        }

        public Set getRolIndividuals() {
            return rolIndividuals;
        }

        public void setRolIndividuals(Set rolIndividuals) {
            this.rolIndividuals = rolIndividuals;
        }

    public Set getFotos() {
        return fotos;
    }

    public void setFotos(Set fotos) {
        this.fotos = fotos;
    }

    public Set getPlanillaIesses() {
        return planillaIesses;
    }

    public void setPlanillaIesses(Set planillaIesses) {
        this.planillaIesses = planillaIesses;
    }

    public Set getDiasVacaciones() {
        return diasVacaciones;
    }

    public void setDiasVacaciones(Set diasVacaciones) {
        this.diasVacaciones = diasVacaciones;
    }

    public Set getControlDiarios() {
        return controlDiarios;
    }

    public void setControlDiarios(Set controlDiarios) {
        this.controlDiarios = controlDiarios;
    }

    public Set getControlExtrases() {
        return controlExtrases;
    }

    public void setControlExtrases(Set controlExtrases) {
        this.controlExtrases = controlExtrases;
    }

    public Set getPagoVacacioneses() {
        return pagoVacacioneses;
    }

    public void setPagoVacacioneses(Set pagoVacacioneses) {
        this.pagoVacacioneses = pagoVacacioneses;
    }
      
    
        
}