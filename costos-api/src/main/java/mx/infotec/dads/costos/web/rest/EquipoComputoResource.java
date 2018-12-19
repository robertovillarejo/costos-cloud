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

import mx.infotec.dads.costos.domain.EquipoComputo;
import mx.infotec.dads.costos.service.EquipoComputoService;

/**
 * 
 * @author kukulkan
 * @kukulkanGenerated 20181109143229
 */
@RestController
@RequestMapping("/api")
public class EquipoComputoResource {

    private static final Logger log = LoggerFactory.getLogger(EquipoComputoResource.class);
    
    private static final String ENTITY_NAME = "equipoComputo";

    @Autowired
    private EquipoComputoService service;
    
    /**
     * GET  /equipoComputo : recupera todos los equipoComputo.
     *
     * @param pageable información de paginación
     * @return El objeto ResponseEntity con estado de 200 (OK) y la lista de equipoComputo en el cuerpo del mensaje
     */
    @GetMapping("/equipoComputo")
    @Timed
    public ResponseEntity<List<EquipoComputo>> getAllEquipoComputo(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of EquipoComputo");
        Page<EquipoComputo> page = service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/equipoComputo");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /equipoComputo/:id : recupera por "id" de EquipoComputo.
     *
     * @param id el id del EquipoComputo que se desea recuperar
     * @return El objeto ResponseEntity con el estado de 200 (OK) y dentro del cuerpo del mensaje el EquipoComputo, o con estado de 404 (Not Found)
     */
    @GetMapping("/equipoComputo/{id}")
    @Timed
    public ResponseEntity<EquipoComputo> getEquipoComputo(@PathVariable String id) {
        log.debug("REST request to get EquipoComputo : {}", id);
        EquipoComputo equipoComputo = service.findById(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(equipoComputo));
    }

    /**
     * POST  /equipoComputo : Create a new usuario.
     *
     * @param equipoComputo el equipoComputo que se desea crear
     * @return El objeto ResponseEntity con estado 201 (Created) y en el cuerpo un nuevo equipoComputo, o con estado 400 (Bad Request) si el usuario ya tiene un ID
     * @throws URISyntaxException Si la sintaxis de la URI no es correcta
     */
    @PostMapping("/equipoComputo")
    @Timed
    public ResponseEntity<EquipoComputo> createEquipoComputo(@Valid @RequestBody EquipoComputo equipoComputo) throws URISyntaxException {
        log.debug("REST request to save EquipoComputo : {}", equipoComputo);
        if (equipoComputo.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new equipoComputo cannot already have an ID")).body(null);
        }
        EquipoComputo result = service.save(equipoComputo);
        return ResponseEntity.created(new URI("/api/equipoComputo/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * PUT  /equipoComputo : Actualiza un EquipoComputo existente.
     *
     * @param equipoComputo el equipoComputo que se desea actualizar
     * @return el objeto ResponseEntity con estado de 200 (OK) y en el cuerpo de la respuesta el EquipoComputo actualizado,
     * o con estatus de 400 (Bad Request) si el equipoComputo no es valido,
     * o con estatus de 500 (Internal Server Error) si el equipoComputo no se puede actualizar
     * @throws URISyntaxException si la sintaxis de la URI no es correcta
     */
    @PutMapping("/equipoComputo")
    @Timed
    public ResponseEntity<EquipoComputo> updateEquipoComputo(@Valid @RequestBody EquipoComputo equipoComputo) throws URISyntaxException {
        log.debug("REST request to update EquipoComputo : {}", equipoComputo);
        if (equipoComputo.getId() == null) {
            return createEquipoComputo(equipoComputo);
        }
        EquipoComputo result = service.save(equipoComputo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, equipoComputo.getId().toString()))
            .body(result);
    }


    /**
     * DELETE  /equipoComputo/:id : borrar el EquipoComputo con "id".
     *
     * @param id el id del EquipoComputo que se desea borrar
     * @return el objeto ResponseEntity con estatus 200 (OK)
     */
    @DeleteMapping("/equipoComputo/{id}")
    @Timed
    public ResponseEntity<Void> deleteEquipoComputo(@PathVariable String id) {
        log.debug("REST request to delete EquipoComputo : {}", id);
        service.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/equipoComputo?query=:query : buscar por el equipoComputo correspondiente
     * to the query.
     *
     * @param query el query para el equipoComputo que se desea buscar
     * @param pageable información de la paginación
     * @return el resultado de la busqueda
     */
    @GetMapping("/_search/equipoComputo")
    @Timed
    public ResponseEntity<List<EquipoComputo>> searchEquipoComputo(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of EquipoComputo for query {}", query);
        Page<EquipoComputo> page = service.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/equipoComputo");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
}