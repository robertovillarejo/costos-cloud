/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2018 kukulkan
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

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;
/**
 * The Proveedor
 * 
 * @author kukulkan
 *
 */
@Document(collection = "proveedores")
public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la llave primaria id
     *
     * @kukulkanGenerated 20181109143229
     */
    @Id
    private String id;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla proveedores
     *
     * @kukulkanGenerated 20181109143229
     */
    @NotNull
    @Field("claveServicio")
    private String claveServicio;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla proveedores
     *
     * @kukulkanGenerated 20181109143229
     */
    @NotNull
    @Size(min = 2)	    
    @Field("nombre")
    private String nombre;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla proveedores
     *
     * @kukulkanGenerated 20181109143229
     */
    @Field("mes")
    private Integer mes;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla proveedores
     *
     * @kukulkanGenerated 20181109143229
     */
    @Field("anio")
    private Integer anio;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla proveedores
     *
     * @kukulkanGenerated 20181109143229
     */
    @Field("porcentaje")
    private Double porcentaje;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla proveedores
     *
     * @kukulkanGenerated 20181109143229
     */
    @NotNull
    @Field("subtipoCosto")
    private String subtipoCosto;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla proveedores
     *
     * @kukulkanGenerated 20181109143229
     */
    @Field("observacion")
    private String observacion;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla proveedores
     *
     * @kukulkanGenerated 20181109143229
     */
    @Field("fechaObservacion")
    private String fechaObservacion;
	
    /**
     * Este constructor fue generado automáticamente por kukulkan
     * 
     */
    public Proveedor() {

    }
    
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la llave primaria proveedores.id
     *
     * @return el valor de id
     *
     * @kukulkanGenerated 20181109143229
     */
    public String getId() {
        return id;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la llave primaria. proveedores.id
     *
     * @return el valor de area_conocimiento.id
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la propiedad proveedores.claveServicio
     *
     * @return el valor de claveServicio
     *
     * @kukulkanGenerated 20181109143229
     */
    public String getClaveServicio() {
        return claveServicio;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. proveedores.claveServicio
     *
     * @return el valor de ClaveServicio
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setClaveServicio(String claveServicio) {
        this.claveServicio = claveServicio;
    }
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la propiedad proveedores.nombre
     *
     * @return el valor de nombre
     *
     * @kukulkanGenerated 20181109143229
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. proveedores.nombre
     *
     * @return el valor de Nombre
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la propiedad proveedores.mes
     *
     * @return el valor de mes
     *
     * @kukulkanGenerated 20181109143229
     */
    public Integer getMes() {
        return mes;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. proveedores.mes
     *
     * @return el valor de Mes
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setMes(Integer mes) {
        this.mes = mes;
    }
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la propiedad proveedores.anio
     *
     * @return el valor de anio
     *
     * @kukulkanGenerated 20181109143229
     */
    public Integer getAnio() {
        return anio;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. proveedores.anio
     *
     * @return el valor de Anio
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setAnio(Integer anio) {
        this.anio = anio;
    }
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la propiedad proveedores.porcentaje
     *
     * @return el valor de porcentaje
     *
     * @kukulkanGenerated 20181109143229
     */
    public Double getPorcentaje() {
        return porcentaje;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. proveedores.porcentaje
     *
     * @return el valor de Porcentaje
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la propiedad proveedores.subtipoCosto
     *
     * @return el valor de subtipoCosto
     *
     * @kukulkanGenerated 20181109143229
     */
    public String getSubtipoCosto() {
        return subtipoCosto;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. proveedores.subtipoCosto
     *
     * @return el valor de SubtipoCosto
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setSubtipoCosto(String subtipoCosto) {
        this.subtipoCosto = subtipoCosto;
    }
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la propiedad proveedores.observacion
     *
     * @return el valor de observacion
     *
     * @kukulkanGenerated 20181109143229
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. proveedores.observacion
     *
     * @return el valor de Observacion
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la propiedad proveedores.fechaObservacion
     *
     * @return el valor de fechaObservacion
     *
     * @kukulkanGenerated 20181109143229
     */
    public String getFechaObservacion() {
        return fechaObservacion;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. proveedores.fechaObservacion
     *
     * @return el valor de FechaObservacion
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setFechaObservacion(String fechaObservacion) {
        this.fechaObservacion = fechaObservacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Proveedor entity = (Proveedor) o;
        if (entity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
    
    /**
     * Este método fue generado automaticamente por kukulkan
     *
     * @return el valor de representado por la entidad Proveedor
     *
     * @kukulkanGenerated 20181109143229
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", claveServicio=").append(claveServicio);
        sb.append(", nombre=").append(nombre);
        sb.append(", mes=").append(mes);
        sb.append(", anio=").append(anio);
        sb.append(", porcentaje=").append(porcentaje);
        sb.append(", subtipoCosto=").append(subtipoCosto);
        sb.append(", observacion=").append(observacion);
        sb.append(", fechaObservacion=").append(fechaObservacion);
        sb.append("]");
        return sb.toString();
    }
}
