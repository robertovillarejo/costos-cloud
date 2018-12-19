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
package mx.infotec.dads.costos.web.rest;

import java.util.List;
import java.util.Optional;

import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import mx.infotec.dads.costos.web.rest.util.HeaderUtil;
import mx.infotec.dads.costos.web.rest.util.PaginationUtil;

import mx.infotec.dads.costos.domain.Proveedor;
import mx.infotec.dads.costos.service.ProveedorService;

/**
 * 
 * @author kukulkan
 * @kukulkanGenerated 20181109143229
 */
@RestController
@RequestMapping("/api")
public class ProveedorResource {

    private static final Logger log = LoggerFactory.getLogger(ProveedorResource.class);
    
    private static final String ENTITY_NAME = "proveedor";

    @Autowired
    private ProveedorService service;
    
    /**
     * GET  /proveedores : recupera todos los proveedores.
     *
     * @param pageable información de paginación
     * @return El objeto ResponseEntity con estado de 200 (OK) y la lista de proveedores en el cuerpo del mensaje
     */
    @GetMapping("/proveedores")
    @Timed
    public ResponseEntity<List<Proveedor>> getAllProveedor(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Proveedor");
        Page<Proveedor> page = service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/proveedores");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /proveedores/:id : recupera por "id" de Proveedor.
     *
     * @param id el id del Proveedor que se desea recuperar
     * @return El objeto ResponseEntity con el estado de 200 (OK) y dentro del cuerpo del mensaje el Proveedor, o con estado de 404 (Not Found)
     */
    @GetMapping("/proveedores/{id}")
    @Timed
    public ResponseEntity<Proveedor> getProveedor(@PathVariable String id) {
        log.debug("REST request to get Proveedor : {}", id);
        Proveedor proveedor = service.findById(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(proveedor));
    }

    /**
     * POST  /proveedores : Create a new usuario.
     *
     * @param proveedor el proveedor que se desea crear
     * @return El objeto ResponseEntity con estado 201 (Created) y en el cuerpo un nuevo proveedor, o con estado 400 (Bad Request) si el usuario ya tiene un ID
     * @throws URISyntaxException Si la sintaxis de la URI no es correcta
     */
    @PostMapping("/proveedores")
    @Timed
    public ResponseEntity<Proveedor> createProveedor(@Valid @RequestBody Proveedor proveedor) throws URISyntaxException {
        log.debug("REST request to save Proveedor : {}", proveedor);
        if (proveedor.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new proveedor cannot already have an ID")).body(null);
        }
        Proveedor result = service.save(proveedor);
        return ResponseEntity.created(new URI("/api/proveedores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * PUT  /proveedores : Actualiza un Proveedor existente.
     *
     * @param proveedor el proveedor que se desea actualizar
     * @return el objeto ResponseEntity con estado de 200 (OK) y en el cuerpo de la respuesta el Proveedor actualizado,
     * o con estatus de 400 (Bad Request) si el proveedor no es valido,
     * o con estatus de 500 (Internal Server Error) si el proveedor no se puede actualizar
     * @throws URISyntaxException si la sintaxis de la URI no es correcta
     */
    @PutMapping("/proveedores")
    @Timed
    public ResponseEntity<Proveedor> updateProveedor(@Valid @RequestBody Proveedor proveedor) throws URISyntaxException {
        log.debug("REST request to update Proveedor : {}", proveedor);
        if (proveedor.getId() == null) {
            return createProveedor(proveedor);
        }
        Proveedor result = service.save(proveedor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, proveedor.getId().toString()))
            .body(result);
    }


    /**
     * DELETE  /proveedores/:id : borrar el Proveedor con "id".
     *
     * @param id el id del Proveedor que se desea borrar
     * @return el objeto ResponseEntity con estatus 200 (OK)
     */
    @DeleteMapping("/proveedores/{id}")
    @Timed
    public ResponseEntity<Void> deleteProveedor(@PathVariable String id) {
        log.debug("REST request to delete Proveedor : {}", id);
        service.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/proveedores?query=:query : buscar por el proveedor correspondiente
     * to the query.
     *
     * @param query el query para el proveedor que se desea buscar
     * @param pageable información de la paginación
     * @return el resultado de la busqueda
     */
    @GetMapping("/_search/proveedores")
    @Timed
    public ResponseEntity<List<Proveedor>> searchProveedor(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Proveedor for query {}", query);
        Page<Proveedor> page = service.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/proveedores");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
}