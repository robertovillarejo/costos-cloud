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
 * The EquipoComputo
 * 
 * @author kukulkan
 *
 */
@Document(collection = "equipoComputo")
public class EquipoComputo implements Serializable {
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
     * Este campo corresponde a la tabla equipoComputo
     *
     * @kukulkanGenerated 20181109143229
     */
    @NotNull
    @Field("tipoComputadora")
    private String tipoComputadora;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla equipoComputo
     *
     * @kukulkanGenerated 20181109143229
     */
    @NotNull
    @Field("proveedor")
    private String proveedor;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla equipoComputo
     *
     * @kukulkanGenerated 20181109143229
     */
    @Field("monto")
    private Double monto;
	
    /**
     * Este constructor fue generado automáticamente por kukulkan
     * 
     */
    public EquipoComputo() {

    }
    
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la llave primaria equipoComputo.id
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
     * Este método SETTER fue generado para la llave primaria. equipoComputo.id
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
     * Este método GETTER fue generado para la propiedad equipoComputo.tipoComputadora
     *
     * @return el valor de tipoComputadora
     *
     * @kukulkanGenerated 20181109143229
     */
    public String getTipoComputadora() {
        return tipoComputadora;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. equipoComputo.tipoComputadora
     *
     * @return el valor de TipoComputadora
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setTipoComputadora(String tipoComputadora) {
        this.tipoComputadora = tipoComputadora;
    }
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la propiedad equipoComputo.proveedor
     *
     * @return el valor de proveedor
     *
     * @kukulkanGenerated 20181109143229
     */
    public String getProveedor() {
        return proveedor;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. equipoComputo.proveedor
     *
     * @return el valor de Proveedor
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la propiedad equipoComputo.monto
     *
     * @return el valor de monto
     *
     * @kukulkanGenerated 20181109143229
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. equipoComputo.monto
     *
     * @return el valor de Monto
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EquipoComputo entity = (EquipoComputo) o;
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
     * @return el valor de representado por la entidad EquipoComputo
     *
     * @kukulkanGenerated 20181109143229
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tipoComputadora=").append(tipoComputadora);
        sb.append(", proveedor=").append(proveedor);
        sb.append(", monto=").append(monto);
        sb.append("]");
        return sb.toString();
    }
}
