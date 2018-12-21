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

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
/**
 * The PartidaConcepto
 * 
 * @author kukulkan
 *
 */
@Document(collection = "partidaConcepto")
public class PartidaConcepto implements Serializable {
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
     * Este campo corresponde a la tabla partidaConcepto
     *
     * @kukulkanGenerated 20181109143229
     */
    @NotNull
    @Field("tipoCosto")
    private String tipoCosto;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla partidaConcepto
     *
     * @kukulkanGenerated 20181109143229
     */
    @NotNull
    @Field("subtipoCosto")
    private String subtipoCosto;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla partidaConcepto
     *
     * @kukulkanGenerated 20181109143229
     */
    @NotNull
    @Field("partida")
    private String partida;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla partidaConcepto
     *
     * @kukulkanGenerated 20181109143229
     */
    @Field("subPartida")
    private String subPartida;
	
    /**
     * Este campo fue generado automaticamente por kukulkan 
     * Este campo corresponde a la tabla partidaConcepto
     *
     * @kukulkanGenerated 20181109143229
     */
    @Field("subSubPartida")
    private String subSubpartida;
	
    /**
     * Este constructor fue generado automáticamente por kukulkan
     * 
     */
    public PartidaConcepto() {

    }
    
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la llave primaria partidaConcepto.id
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
     * Este método SETTER fue generado para la llave primaria. partidaConcepto.id
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
     * Este método GETTER fue generado para la propiedad partidaConcepto.tipoCosto
     *
     * @return el valor de tipoCosto
     *
     * @kukulkanGenerated 20181109143229
     */
    public String getTipoCosto() {
        return tipoCosto;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. partidaConcepto.tipoCosto
     *
     * @return el valor de TipoCosto
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setTipoCosto(String tipoCosto) {
        this.tipoCosto = tipoCosto;
    }
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la propiedad partidaConcepto.subtipoCosto
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
     * Este método SETTER fue generado para la propiedad. partidaConcepto.subtipoCosto
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
     * Este método GETTER fue generado para la propiedad partidaConcepto.partida
     *
     * @return el valor de partida
     *
     * @kukulkanGenerated 20181109143229
     */
    public String getPartida() {
        return partida;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. partidaConcepto.partida
     *
     * @return el valor de Partida
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setPartida(String partida) {
        this.partida = partida;
    }
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la propiedad partidaConcepto.subPartida
     *
     * @return el valor de subPartida
     *
     * @kukulkanGenerated 20181109143229
     */
    public String getSubPartida() {
        return subPartida;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. partidaConcepto.subPartida
     *
     * @return el valor de SubPartida
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setSubPartida(String subPartida) {
        this.subPartida = subPartida;
    }
    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método GETTER fue generado para la propiedad partidaConcepto.subSubPartida
     *
     * @return el valor de subSubPartida
     *
     * @kukulkanGenerated 20181109143229
     */
    public String getSubSubpartida() {
        return subSubpartida;
    }

    /**
     * Este método fue generado automaticamente por kukulkan 
     * Este método SETTER fue generado para la propiedad. partidaConcepto.subSubPartida
     *
     * @return el valor de SubSubPartida
     *
     * @kukulkanGenerated 20181109143229
     */
    public void setSubSubpartida(String subSubPartida) {
        this.subSubpartida = subSubPartida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PartidaConcepto entity = (PartidaConcepto) o;
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
     * @return el valor de representado por la entidad PartidaConcepto
     *
     * @kukulkanGenerated 20181109143229
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tipoCosto=").append(tipoCosto);
        sb.append(", subtipoCosto=").append(subtipoCosto);
        sb.append(", partida=").append(partida);
        sb.append(", subPartida=").append(subPartida);
        sb.append(", subSubPartida=").append(subSubpartida);
        sb.append("]");
        return sb.toString();
    }
}
