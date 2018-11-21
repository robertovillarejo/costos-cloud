package mx.infotec.dads.costos.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Un Costo
 * 
 * @author Roberto Villarejo Martínez
 *
 */
@Document(collection = "costos")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Costo implements Serializable {

    /**
     * 
     */
    protected static final long serialVersionUID = 182075029449305890L;

    @Id
    protected String id;

    protected Origin origin;

    protected boolean processed;

    protected double monto;

    protected double porcentaje;

    protected int anio;

    protected int mes;

    protected Date fechaRegistro;

    protected int area;

    protected String proyectoOperativo;

    protected String proyectoPresupuestal;

    protected String servicio;

    protected String tipoCosto;

    protected int partida;

    protected String proveedor;

    protected String user;

    protected String subpartida;

    protected String subSubPartida;

    protected String numeroFactura;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    @JsonGetter
    public boolean isProcessed() {
        return processed;
    }

    @JsonSetter
    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getProyectoOperativo() {
        return proyectoOperativo;
    }

    public void setProyectoOperativo(String proyectoOperativo) {
        this.proyectoOperativo = proyectoOperativo;
    }

    public String getProyectoPresupuestal() {
        return proyectoPresupuestal;
    }

    public void setProyectoPresupuestal(String proyectoPresupuestal) {
        this.proyectoPresupuestal = proyectoPresupuestal;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getTipoCosto() {
        return tipoCosto;
    }

    public void setTipoCosto(String tipoCosto) {
        this.tipoCosto = tipoCosto;
    }

    public int getPartida() {
        return partida;
    }

    public void setPartida(int partida) {
        this.partida = partida;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSubpartida() {
        return subpartida;
    }

    public void setSubpartida(String subpartida) {
        this.subpartida = subpartida;
    }

    public String getSubSubPartida() {
        return subSubPartida;
    }

    public void setSubSubPartida(String subSubPartida) {
        this.subSubPartida = subSubPartida;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    @Override
    public String toString() {
        return "Costo [id=" + id + ", processed=" + processed + ", monto=" + monto + ", porcentaje=" + porcentaje
                + ", anio=" + anio + ", mes=" + mes + ", fechaRegistro=" + fechaRegistro + ", area=" + area
                + ", proyectoOperativo=" + proyectoOperativo + ", proyectoPresupuestal=" + proyectoPresupuestal
                + ", servicio=" + servicio + ", tipoCosto=" + tipoCosto + ", partida=" + partida + ", proveedor="
                + proveedor + ", user=" + user + ", subpartida=" + subpartida + ", subSubPartida=" + subSubPartida
                + ", numeroFactura=" + numeroFactura + "]";
    }

}
