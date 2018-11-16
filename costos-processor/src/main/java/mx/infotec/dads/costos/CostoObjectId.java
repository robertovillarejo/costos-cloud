/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2018 Roberto Villarejo Martínez
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mx.infotec.dads.costos;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CostoObjectId implements Serializable {

    /**
     * 
     */
    protected static final long serialVersionUID = 182075029449305890L;

    @JsonProperty("_id")
    protected ObjectId id;

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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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
