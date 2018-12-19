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
 * The FactorBasico
 * 
 * @author kukulkan
 *
 */
@Document(collection = "factorBasico")
public class FactorBasico implements Serializable {
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
     * Este campo corresponde a la tabla factorBasico
     *
     * @kukulkanGenerated 20181109143229
     */
    @NotNull
    @Field("nombre")
    private String nombre;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla factorBasico
     *
     * @kukulkanGenerated 20181109143229
     */
    @Field("sede")
    private String sede;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla factorBasico
     *
     * @kukulkanGenerated 20181109143229
     */
    @Field("mes")
    private String mes;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla factorBasico
     *
     * @kukulkanGenerated 20181109143229
     */
    @Field("anio")
    private String anio;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla factorBasico
     *
     * @kukulkanGenerated 20181109143229
     */
    @NotNull
    @Field("valor")
    private Double valor;
	
    /**
     * Este constructor fue generado automáticamente por kukulkan
     * 
     */
    public FactorBasico() {

    }
    
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la llave primaria factorBasico.id
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
     * Este método SETTER fue generado para la llave primaria. factorBasico.id
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
     * Este método GETTER fue generado para la propiedad factorBasico.nombre
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
     * Este método SETTER fue generado para la propiedad. factorBasico.nombre
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
     * Este método GETTER fue generado para la propiedad factorBasico.sede
     *
     * @return el valor de sede
     *
     * @kukulkanGenerated 20181109143229
     */
    public String getSede() {
        return sede;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. factorBasico.sede
     *
     * @return el valor de Sede
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setSede(String sede) {
        this.sede = sede;
    }
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la propiedad factorBasico.mes
     *
     * @return el valor de mes
     *
     * @kukulkanGenerated 20181109143229
     */
    public String getMes() {
        return mes;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. factorBasico.mes
     *
     * @return el valor de Mes
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setMes(String mes) {
        this.mes = mes;
    }
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la propiedad factorBasico.anio
     *
     * @return el valor de anio
     *
     * @kukulkanGenerated 20181109143229
     */
    public String getAnio() {
        return anio;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. factorBasico.anio
     *
     * @return el valor de Anio
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setAnio(String anio) {
        this.anio = anio;
    }
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la propiedad factorBasico.valor
     *
     * @return el valor de valor
     *
     * @kukulkanGenerated 20181109143229
     */
    public Double getValor() {
        return valor;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. factorBasico.valor
     *
     * @return el valor de Valor
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FactorBasico entity = (FactorBasico) o;
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
     * @return el valor de representado por la entidad FactorBasico
     *
     * @kukulkanGenerated 20181109143229
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", nombre=").append(nombre);
        sb.append(", sede=").append(sede);
        sb.append(", mes=").append(mes);
        sb.append(", anio=").append(anio);
        sb.append(", valor=").append(valor);
        sb.append("]");
        return sb.toString();
    }
}
