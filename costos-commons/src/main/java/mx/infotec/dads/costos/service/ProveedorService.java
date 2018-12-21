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
package mx.infotec.dads.costos.service;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.infotec.dads.costos.domain.Proveedor;

/**
 * ProveedorService
 * 
 * @author kukulkan
 * @kukulkanGenerated 20181109143229
 */
public interface ProveedorService {

    /**
     * regresa una lista con todos los elementos Proveedor
     * 
     * @return Page<Proveedor>
     */
    Page<Proveedor> findAll(Pageable pageable);

    /**
     * Consulta un Proveedor por su llave primaria
     * 
     * @param id
     * @return Proveedor
     */
    Optional<Proveedor> findById(String id);

    /**
     * 
     * @param nombre
     * @return
     */
    Optional<Proveedor> findByExample(Example<Proveedor> proveedorExample);

    /**
     * Guarda o actualiza un Proveedor
     * 
     * @param proveedor
     * @return boolean
     */
    Proveedor save(Proveedor proveedor);

    /**
     * Regresa true o false si existe un Proveedor almacenado
     * 
     * @param id
     * @return boolean
     */
    boolean exists(String id);

    /**
     * Borrar un Proveedor por su llave primaria
     * 
     * @param id
     */
    void delete(String id);

    /**
     * Borrar todos los elementos Proveedor almacenados
     * 
     * @param id
     */
    void deleteAll();

    /**
     * Buscar Proveedor con el correspondiente al query.
     *
     * @param query
     *            El query de la busqueda
     * 
     * @param pageable
     *            la información de paginación
     * @return Page de todas las entidades
     */
    Page<Proveedor> search(String query, Pageable pageable);

}
