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

package mx.infotec.dads.costos.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mx.infotec.dads.kukulkan.tables.annotations.Sheet;
import mx.infotec.dads.kukulkan.tables.annotations.SheetColumn;

/**
 * Un Costo
 * 
 * @author Roberto Villarejo Martínez
 *
 */
@Sheet
@Document(collection = "costos")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Costo implements Serializable {

    /**
     * 
     */
    protected static final long serialVersionUID = 182075029449305890L;

    @SheetColumn(title = "id")
    @Id
    protected String id;

    @SheetColumn(title = "monto")
    protected double monto;

    @SheetColumn(title = "porcentaje")
    protected double porcentaje;

    @SheetColumn(title = "año")
    protected int anio;

    @SheetColumn(title = "mes")
    protected int mes;

    @SheetColumn(title = "fechaRegistro")
    protected Date fechaRegistro;

    @SheetColumn(title = "area")
    protected int area;

    @SheetColumn(title = "proyectoOperativo")
    protected String proyectoOperativo;

    @SheetColumn(title = "proyectoPresupuestal")
    protected String proyectoPresupuestal;

    @SheetColumn(title = "servicio")
    protected String servicio;

    @SheetColumn(title = "tipoCosto")
    protected String tipoCosto;

    @SheetColumn(title = "partida")
    protected int partida;

    @SheetColumn(title = "proveedor")
    protected String proveedor;

    @SheetColumn(title = "user")
    protected String user;

    @SheetColumn(title = "subpartida")
    protected String subpartida;

    @SheetColumn(title = "subSubPartida")
    protected String subSubPartida;

    @SheetColumn(title = "numeroFactura")
    protected String numeroFactura;

    @DBRef
    protected DataFrameItem dataFrameItem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public DataFrameItem getDataFrameItem() {
        return dataFrameItem;
    }

    public void setDataFrameItem(DataFrameItem dfItem) {
        this.dataFrameItem = dfItem;
    }

    @Override
    public String toString() {
        return "Costo [id=" + id + ", monto=" + monto + ", porcentaje=" + porcentaje + ", anio=" + anio + ", mes=" + mes
                + ", fechaRegistro=" + fechaRegistro + ", area=" + area + ", proyectoOperativo=" + proyectoOperativo
                + ", proyectoPresupuestal=" + proyectoPresupuestal + ", servicio=" + servicio + ", tipoCosto="
                + tipoCosto + ", partida=" + partida + ", proveedor=" + proveedor + ", user=" + user + ", subpartida="
                + subpartida + ", subSubPartida=" + subSubPartida + ", numeroFactura=" + numeroFactura + "]";
    }

}
