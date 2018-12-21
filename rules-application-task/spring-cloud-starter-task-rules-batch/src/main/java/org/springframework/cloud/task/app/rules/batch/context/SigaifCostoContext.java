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

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;

import mx.infotec.dads.costos.domain.PartidaConcepto;
import mx.infotec.dads.costos.domain.Proveedor;
import mx.infotec.dads.costos.domain.dataframe.DfItemSigaif;
import mx.infotec.dads.costos.service.PartidaConceptoService;
import mx.infotec.dads.costos.service.ProveedorService;

/**
 * @author Roberto Villarejo Martínez
 *
 */
public class SigaifCostoContext extends CostoContext {

    private DfItemSigaif sigaif;

    public SigaifCostoContext(DfItemSigaif dfItem, ProveedorService proveedorService,
            PartidaConceptoService partidaConceptoService) {
        this.sigaif = dfItem;
        this.proveedorService = proveedorService;
        this.partidaConceptoService = partidaConceptoService;
    }

    public DfItemSigaif getItem() {
        return sigaif;
    }

    /**
     * Recupera el proveedor a partir de la relación: sigaif.subtipoCosto ==
     * proveedor.subtipoCosto
     * 
     * @return el proveedor
     */
    @Cacheable
    public Proveedor getProveedor() {
        Proveedor example = new Proveedor();
        example.setSubtipoCosto(sigaif.getSubtipoCosto());
        Optional<Proveedor> maybeProveedor = proveedorService.findByExample(Example.of(example));
        if (maybeProveedor.isPresent()) {
            return maybeProveedor.get();
        }
        return null;
    }

    /**
     * Recupera la partidaConcepto a partir de la relación: sigaif.partida ==
     * partidaConcepto.partida
     * 
     * @return la partidaConcepto
     */
    @Cacheable
    public PartidaConcepto getPartidaConcepto() {
        PartidaConcepto example = new PartidaConcepto();
        example.setSubtipoCosto(sigaif.getSubtipoCosto());
        Optional<PartidaConcepto> maybePartidaConcepto = partidaConceptoService.findByExample(Example.of(example));
        if (maybePartidaConcepto.isPresent()) {
            return maybePartidaConcepto.get();
        }
        return null;
    }

}
