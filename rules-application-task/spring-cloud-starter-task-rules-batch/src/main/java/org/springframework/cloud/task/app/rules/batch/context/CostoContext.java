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

package org.springframework.cloud.task.app.rules.batch.context;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import mx.infotec.dads.costos.domain.Costo;
import mx.infotec.dads.costos.domain.DataFrameItem;
import mx.infotec.dads.costos.domain.PartidaConcepto;
import mx.infotec.dads.costos.domain.Proveedor;
import mx.infotec.dads.costos.domain.dataframe.DfItemDt;
import mx.infotec.dads.costos.service.FactorBasicoService;
import mx.infotec.dads.costos.service.PartidaConceptoService;
import mx.infotec.dads.costos.service.ProveedorService;

public class CostoContext {

	protected Costo costo = new Costo();

	private DataFrameItem dfItem;
	
	@Autowired
	private ProveedorService proveedor;
	
	@Autowired
	private PartidaConceptoService partidaConcepto;
	
	@Autowired
	private FactorBasicoService factores;

	public CostoContext(DataFrameItem dfItem) {
		this.dfItem = dfItem;
	}

	/*public ProveedorService getProveedor() {
		DfItemDt dt = (DfItemDt) dfItem;
		Optional<Proveedor> pc = proveedor.findOneBySubPartida(dt.getSubPartida());
		if (pc.isPresent()) {
			return pc.get();
		} else {
			return null;
		}
		return proveedor;
	}*/

	public PartidaConcepto getPartidaConcepto() {
		DfItemDt dt = (DfItemDt) dfItem;
		Optional<PartidaConcepto> pc = partidaConcepto.findOneBySubPartida(dt.getSubPartida());
		if (pc.isPresent()) {
			return pc.get();
		} else {
			return null;
		}
	}

	public FactorBasicoService getFactores() {
		return factores;
	}

	public Costo getCosto() {
		return costo;
	}

	public DataFrameItem getItem() {
		return dfItem;
	}

}
