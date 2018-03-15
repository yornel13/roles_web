package models;

import utilidad.UserType;

public class User {

    public static final String ADMINISTRADOR = "A";
    public static final String EMPLEADO = "E";
    public static final String CLIENTE = "C";
    public static final String EMPRESA = "M";

    private Integer id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String username;
    private String password;
    private String tipo;
    private Boolean activo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public UserType getType() {
        switch (tipo) {
            case ADMINISTRADOR:
                return UserType.ADMINISTRADOR;
            case EMPLEADO:
                return UserType.EMPLEADO;
            case CLIENTE:
                return UserType.CLIENTE;
            case EMPRESA:
                return UserType.EMPRESA;
            default:
                return UserType.NONE;
        }
    }

    public String getTipoText() {
        switch (tipo) {
            case ADMINISTRADOR:
                return "Administrador";
            case EMPLEADO:
                return "Empleado";
            case CLIENTE:
                return "Cliente";
            case EMPRESA:
                return "Empresa";
            default:
                return "NONE";
        }
    }

    public String getProfile() {
        return getTipoText()+": "+getNombre()+" "+getApellido();
    }
}
