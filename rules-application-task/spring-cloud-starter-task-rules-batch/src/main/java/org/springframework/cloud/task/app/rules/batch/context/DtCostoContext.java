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

import org.springframework.data.domain.Example;

import mx.infotec.dads.costos.domain.PartidaConcepto;
import mx.infotec.dads.costos.domain.Proveedor;
import mx.infotec.dads.costos.domain.dataframe.DfItemDt;
import mx.infotec.dads.costos.service.PartidaConceptoService;
import mx.infotec.dads.costos.service.ProveedorService;

public class DtCostoContext extends CostoContext {

    private DfItemDt dtItem;

    private PartidaConceptoService partidaConceptoService;

    public DtCostoContext(DfItemDt dfItemDt, ProveedorService proveedorService,
            PartidaConceptoService partidaConceptoService) {
        this.dtItem = dfItemDt;
        this.proveedorService = proveedorService;
        this.partidaConceptoService = partidaConceptoService;
    }

    public DfItemDt getItem() {
        return dtItem;
    }

    public Proveedor getProveedor() {
        Proveedor proveedorExample = new Proveedor();
        proveedorExample.setSubtipoCosto(costo.getSubtipoCosto());
        Optional<Proveedor> maybeProveedor = proveedorService.findByExample(Example.of(proveedorExample));
        if (maybeProveedor.isPresent()) {
            return maybeProveedor.get();
        }
        return null;
    }

    public PartidaConcepto getPartidaConcepto() {
        PartidaConcepto partidaConceptoExample = new PartidaConcepto();
        partidaConceptoExample.setPartida(dtItem.getPartida());
        Optional<PartidaConcepto> maybePc = partidaConceptoService.findByExample(Example.of(partidaConceptoExample));
        if (maybePc.isPresent()) {
            return maybePc.get();
        }
        return null;
    }

}
