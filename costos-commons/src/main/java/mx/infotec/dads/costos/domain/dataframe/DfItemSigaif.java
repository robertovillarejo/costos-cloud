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

package mx.infotec.dads.costos.domain.dataframe;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mx.infotec.dads.costos.domain.DataFrameItem;

@Document("dataFrameItems")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DfItemSigaif extends DataFrameItem {

    /**
     * 
     */
    private static final long serialVersionUID = 5068991723463595030L;
    
    protected String partida;

    protected String subtipoCosto;
    
    protected int area;
    
    protected int anio;
    
    protected double mes1;
    
    protected double mes2;
    
    protected double mes3;
    
    protected double mes4;
    
    protected double mes5;
    
    protected double mes6;
    
    protected double mes7;
    
    protected double mes8;
    
    protected double mes9;
    
    protected double mes10;
    
    protected double mes11;
    
    protected double mes12;

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public String getSubtipoCosto() {
		return subtipoCosto;
	}

	public void setSubtipoCosto(String subtipoCosto) {
		this.subtipoCosto = subtipoCosto;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public double getMes1() {
		return mes1;
	}

	public void setMes1(double mes1) {
		this.mes1 = mes1;
	}

	public double getMes2() {
		return mes2;
	}

	public void setMes2(double mes2) {
		this.mes2 = mes2;
	}

	public double getMes3() {
		return mes3;
	}

	public void setMes3(double mes3) {
		this.mes3 = mes3;
	}

	public double getMes4() {
		return mes4;
	}

	public void setMes4(double mes4) {
		this.mes4 = mes4;
	}

	public double getMes5() {
		return mes5;
	}

	public void setMes5(double mes5) {
		this.mes5 = mes5;
	}

	public double getMes6() {
		return mes6;
	}

	public void setMes6(double mes6) {
		this.mes6 = mes6;
	}

	public double getMes7() {
		return mes7;
	}

	public void setMes7(double mes7) {
		this.mes7 = mes7;
	}

	public double getMes8() {
		return mes8;
	}

	public void setMes8(double mes8) {
		this.mes8 = mes8;
	}

	public double getMes9() {
		return mes9;
	}

	public void setMes9(double mes9) {
		this.mes9 = mes9;
	}

	public double getMes10() {
		return mes10;
	}

	public void setMes10(double mes10) {
		this.mes10 = mes10;
	}

	public double getMes11() {
		return mes11;
	}

	public void setMes11(double mes11) {
		this.mes11 = mes11;
	}

	public double getMes12() {
		return mes12;
	}

	public void setMes12(double mes12) {
		this.mes12 = mes12;
	}

	@Override
	public String toString() {
		return "DfItemSigaif [partida=" + partida + ", subtipoCosto=" + subtipoCosto + ", area=" + area + ", anio="
				+ anio + ", mes1=" + mes1 + ", mes2=" + mes2 + ", mes3=" + mes3 + ", mes4=" + mes4 + ", mes5=" + mes5
				+ ", mes6=" + mes6 + ", mes7=" + mes7 + ", mes8=" + mes8 + ", mes9=" + mes9 + ", mes10=" + mes10
				+ ", mes11=" + mes11 + ", mes12=" + mes12 + "]";
	}    

}
