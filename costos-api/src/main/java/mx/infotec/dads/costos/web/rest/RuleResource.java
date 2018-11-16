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

import mx.infotec.dads.costos.domain.RulePersistable;
import mx.infotec.dads.costos.service.RuleService;
import mx.infotec.dads.costos.web.rest.util.HeaderUtil;
import mx.infotec.dads.costos.web.rest.util.PaginationUtil;

/**
 * 
 * @author kukulkan
 * @kukulkanGenerated 20181109143229
 */
@RestController
@RequestMapping("/api")
public class RuleResource {

    private static final Logger log = LoggerFactory.getLogger(RuleResource.class);

    private static final String ENTITY_NAME = "rulePersistable";

    @Autowired
    private RuleService service;

    /**
     * GET /rules : recupera todos los rulePersistable.
     *
     * @param pageable
     *            información de paginación
     * @return El objeto ResponseEntity con estado de 200 (OK) y la lista de
     *         rulePersistable en el cuerpo del mensaje
     */
    @GetMapping("/rules")
    @Timed
    public ResponseEntity<List<RulePersistable>> getAllRulePersistable(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of RulePersistable");
        Page<RulePersistable> page = service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rules");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET /rules/:id : recupera por "id" de RulePersistable.
     *
     * @param id
     *            el id del RulePersistable que se desea recuperar
     * @return El objeto ResponseEntity con el estado de 200 (OK) y dentro del
     *         cuerpo del mensaje el RulePersistable, o con estado de 404 (Not
     *         Found)
     */
    @GetMapping("/rules/{id}")
    @Timed
    public ResponseEntity<RulePersistable> getRulePersistable(@PathVariable String id) {
        log.debug("REST request to get RulePersistable : {}", id);
        RulePersistable rulePersistable = service.findById(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(rulePersistable));
    }

    /**
     * POST /rules : Create a new usuario.
     *
     * @param rulePersistable
     *            el rulePersistable que se desea crear
     * @return El objeto ResponseEntity con estado 201 (Created) y en el cuerpo un
     *         nuevo rulePersistable, o con estado 400 (Bad Request) si el usuario
     *         ya tiene un ID
     * @throws URISyntaxException
     *             Si la sintaxis de la URI no es correcta
     */
    @PostMapping("/rules")
    @Timed
    public ResponseEntity<RulePersistable> createRulePersistable(@Valid @RequestBody RulePersistable rulePersistable)
            throws URISyntaxException {
        log.debug("REST request to save RulePersistable : {}", rulePersistable);
        if (rulePersistable.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists",
                    "A new rulePersistable cannot already have an ID")).body(null);
        }
        RulePersistable result = service.save(rulePersistable);
        return ResponseEntity.created(new URI("/api/rulePersistable/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * PUT /rules : Actualiza un RulePersistable existente.
     *
     * @param rulePersistable
     *            el rulePersistable que se desea actualizar
     * @return el objeto ResponseEntity con estado de 200 (OK) y en el cuerpo de la
     *         respuesta el RulePersistable actualizado, o con estatus de 400 (Bad
     *         Request) si el rulePersistable no es valido, o con estatus de 500
     *         (Internal Server Error) si el rulePersistable no se puede actualizar
     * @throws URISyntaxException
     *             si la sintaxis de la URI no es correcta
     */
    @PutMapping("/rules")
    @Timed
    public ResponseEntity<RulePersistable> updateRulePersistable(@Valid @RequestBody RulePersistable rulePersistable)
            throws URISyntaxException {
        log.debug("REST request to update RulePersistable : {}", rulePersistable);
        if (rulePersistable.getId() == null) {
            return createRulePersistable(rulePersistable);
        }
        RulePersistable result = service.save(rulePersistable);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rulePersistable.getId().toString()))
                .body(result);
    }

    /**
     * DELETE /rules/:id : borrar el RulePersistable con "id".
     *
     * @param id
     *            el id del RulePersistable que se desea borrar
     * @return el objeto ResponseEntity con estatus 200 (OK)
     */
    @DeleteMapping("/rules/{id}")
    @Timed
    public ResponseEntity<Void> deleteRulePersistable(@PathVariable String id) {
        log.debug("REST request to delete RulePersistable : {}", id);
        service.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH /_search/rules?query=:query : buscar por el rulePersistable
     * correspondiente to the query.
     *
     * @param query
     *            el query para el rulePersistable que se desea buscar
     * @param pageable
     *            información de la paginación
     * @return el resultado de la busqueda
     */
    @GetMapping("/_search/rules")
    @Timed
    public ResponseEntity<List<RulePersistable>> searchRulePersistable(@RequestParam String query,
            @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of RulePersistable for query {}", query);
        Page<RulePersistable> page = service.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/rules");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}