package models;

import java.sql.Time;
import java.sql.Date;

public class ControlExtras {

    private Integer id;
    private Integer usuario_id;
    private Integer cliente_id;
    private Date fecha;
    private String caso;
    private Double recargo;
    private Double sobreTiempo;
    private Time entrada;
    private Time salida;

    public ControlExtras(Integer id, Integer usuario_id, Integer cliente_id, Date fecha, String caso, Double recargo, Double sobreTiempo, Time entrada, Time salida) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.cliente_id = cliente_id;
        this.fecha = fecha;
        this.caso = caso;
        this.recargo = recargo;
        this.sobreTiempo = sobreTiempo;
        this.entrada = entrada;
        this.salida = salida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCaso() {
        return caso;
    }

    public void setCaso(String caso) {
        this.caso = caso;
    }

    public Double getRecargo() {
        return recargo;
    }

    public void setRecargo(Double recargo) {
        this.recargo = recargo;
    }

    public Double getSobreTiempo() {
        return sobreTiempo;
    }

    public void setSobreTiempo(Double sobreTiempo) {
        this.sobreTiempo = sobreTiempo;
    }

    public Time getEntrada() {
        return entrada;
    }

    public void setEntrada(Time entrada) {
        this.entrada = entrada;
    }

    public Time getSalida() {
        return salida;
    }

    public void setSalida(Time salida) {
        this.salida = salida;
    }
}
