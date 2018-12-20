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

import mx.infotec.dads.costos.domain.Proveedor;
import mx.infotec.dads.costos.domain.dataframe.DfItemRh;
import mx.infotec.dads.costos.service.ProveedorService;

public class RhCostoContext extends CostoContext {

    private DfItemRh dfItem;

    public RhCostoContext(DfItemRh dfItem, ProveedorService proveedorService) {
        this.dfItem = dfItem;
        this.proveedorService = proveedorService;
    }

    public DfItemRh getItem() {
        return this.dfItem;
    }

    public Proveedor getProveedor() {
        Proveedor exampleProveedor = new Proveedor();
        exampleProveedor.setNombre(dfItem.getProveedor());
        exampleProveedor.setMes(dfItem.getMes());
        exampleProveedor.setAnio(dfItem.getAnio());
        Optional<Proveedor> proveedor = proveedorService.findByExample(Example.of(exampleProveedor));
        if (proveedor.isPresent())
            return proveedor.get();
        return null;
    }

}
