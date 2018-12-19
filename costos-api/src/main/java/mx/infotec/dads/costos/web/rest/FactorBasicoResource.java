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

import mx.infotec.dads.costos.domain.FactorBasico;
import mx.infotec.dads.costos.service.FactorBasicoService;

/**
 * 
 * @author kukulkan
 * @kukulkanGenerated 20181109143229
 */
@RestController
@RequestMapping("/api")
public class FactorBasicoResource {

    private static final Logger log = LoggerFactory.getLogger(FactorBasicoResource.class);
    
    private static final String ENTITY_NAME = "factorBasico";

    @Autowired
    private FactorBasicoService service;
    
    /**
     * GET  /factorBasico : recupera todos los factorBasico.
     *
     * @param pageable información de paginación
     * @return El objeto ResponseEntity con estado de 200 (OK) y la lista de factorBasico en el cuerpo del mensaje
     */
    @GetMapping("/factorBasico")
    @Timed
    public ResponseEntity<List<FactorBasico>> getAllFactorBasico(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of FactorBasico");
        Page<FactorBasico> page = service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/factorBasico");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /factorBasico/:id : recupera por "id" de FactorBasico.
     *
     * @param id el id del FactorBasico que se desea recuperar
     * @return El objeto ResponseEntity con el estado de 200 (OK) y dentro del cuerpo del mensaje el FactorBasico, o con estado de 404 (Not Found)
     */
    @GetMapping("/factorBasico/{id}")
    @Timed
    public ResponseEntity<FactorBasico> getFactorBasico(@PathVariable String id) {
        log.debug("REST request to get FactorBasico : {}", id);
        Optional<FactorBasico> factorBasico = service.findById(id);
        return ResponseUtil.wrapOrNotFound(factorBasico);
    }

    /**
     * POST  /factorBasico : Create a new usuario.
     *
     * @param factorBasico el factorBasico que se desea crear
     * @return El objeto ResponseEntity con estado 201 (Created) y en el cuerpo un nuevo factorBasico, o con estado 400 (Bad Request) si el usuario ya tiene un ID
     * @throws URISyntaxException Si la sintaxis de la URI no es correcta
     */
    @PostMapping("/factorBasico")
    @Timed
    public ResponseEntity<FactorBasico> createFactorBasico(@Valid @RequestBody FactorBasico factorBasico) throws URISyntaxException {
        log.debug("REST request to save FactorBasico : {}", factorBasico);
        if (factorBasico.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new factorBasico cannot already have an ID")).body(null);
        }
        FactorBasico result = service.save(factorBasico);
        return ResponseEntity.created(new URI("/api/factorBasico/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * PUT  /factorBasico : Actualiza un FactorBasico existente.
     *
     * @param factorBasico el factorBasico que se desea actualizar
     * @return el objeto ResponseEntity con estado de 200 (OK) y en el cuerpo de la respuesta el FactorBasico actualizado,
     * o con estatus de 400 (Bad Request) si el factorBasico no es valido,
     * o con estatus de 500 (Internal Server Error) si el factorBasico no se puede actualizar
     * @throws URISyntaxException si la sintaxis de la URI no es correcta
     */
    @PutMapping("/factorBasico")
    @Timed
    public ResponseEntity<FactorBasico> updateFactorBasico(@Valid @RequestBody FactorBasico factorBasico) throws URISyntaxException {
        log.debug("REST request to update FactorBasico : {}", factorBasico);
        if (factorBasico.getId() == null) {
            return createFactorBasico(factorBasico);
        }
        FactorBasico result = service.save(factorBasico);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, factorBasico.getId().toString()))
            .body(result);
    }


    /**
     * DELETE  /factorBasico/:id : borrar el FactorBasico con "id".
     *
     * @param id el id del FactorBasico que se desea borrar
     * @return el objeto ResponseEntity con estatus 200 (OK)
     */
    @DeleteMapping("/factorBasico/{id}")
    @Timed
    public ResponseEntity<Void> deleteFactorBasico(@PathVariable String id) {
        log.debug("REST request to delete FactorBasico : {}", id);
        service.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/factorBasico?query=:query : buscar por el factorBasico correspondiente
     * to the query.
     *
     * @param query el query para el factorBasico que se desea buscar
     * @param pageable información de la paginación
     * @return el resultado de la busqueda
     */
    @GetMapping("/_search/factorBasico")
    @Timed
    public ResponseEntity<List<FactorBasico>> searchFactorBasico(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of FactorBasico for query {}", query);
        Page<FactorBasico> page = service.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/factorBasico");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
}