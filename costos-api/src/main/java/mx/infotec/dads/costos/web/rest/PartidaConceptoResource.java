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

import mx.infotec.dads.costos.domain.PartidaConcepto;
import mx.infotec.dads.costos.service.PartidaConceptoService;

/**
 * 
 * @author kukulkan
 * @kukulkanGenerated 20181109143229
 */
@RestController
@RequestMapping("/api")
public class PartidaConceptoResource {

    private static final Logger log = LoggerFactory.getLogger(PartidaConceptoResource.class);
    
    private static final String ENTITY_NAME = "partidaConcepto";

    @Autowired
    private PartidaConceptoService service;
    
    /**
     * GET  /partidaConcepto : recupera todos los partidaConcepto.
     *
     * @param pageable información de paginación
     * @return El objeto ResponseEntity con estado de 200 (OK) y la lista de partidaConcepto en el cuerpo del mensaje
     */
    @GetMapping("/partidaConcepto")
    @Timed
    public ResponseEntity<List<PartidaConcepto>> getAllPartidaConcepto(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of PartidaConcepto");
        Page<PartidaConcepto> page = service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/partidaConcepto");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /partidaConcepto/:id : recupera por "id" de PartidaConcepto.
     *
     * @param id el id del PartidaConcepto que se desea recuperar
     * @return El objeto ResponseEntity con el estado de 200 (OK) y dentro del cuerpo del mensaje el PartidaConcepto, o con estado de 404 (Not Found)
     */
    @GetMapping("/partidaConcepto/{id}")
    @Timed
    public ResponseEntity<PartidaConcepto> getPartidaConcepto(@PathVariable String id) {
        log.debug("REST request to get PartidaConcepto : {}", id);
        PartidaConcepto partidaConcepto = service.findById(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(partidaConcepto));
    }

    /**
     * POST  /partidaConcepto : Create a new usuario.
     *
     * @param partidaConcepto el partidaConcepto que se desea crear
     * @return El objeto ResponseEntity con estado 201 (Created) y en el cuerpo un nuevo partidaConcepto, o con estado 400 (Bad Request) si el usuario ya tiene un ID
     * @throws URISyntaxException Si la sintaxis de la URI no es correcta
     */
    @PostMapping("/partidaConcepto")
    @Timed
    public ResponseEntity<PartidaConcepto> createPartidaConcepto(@Valid @RequestBody PartidaConcepto partidaConcepto) throws URISyntaxException {
        log.debug("REST request to save PartidaConcepto : {}", partidaConcepto);
        if (partidaConcepto.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new partidaConcepto cannot already have an ID")).body(null);
        }
        PartidaConcepto result = service.save(partidaConcepto);
        return ResponseEntity.created(new URI("/api/partidaConcepto/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * PUT  /partidaConcepto : Actualiza un PartidaConcepto existente.
     *
     * @param partidaConcepto el partidaConcepto que se desea actualizar
     * @return el objeto ResponseEntity con estado de 200 (OK) y en el cuerpo de la respuesta el PartidaConcepto actualizado,
     * o con estatus de 400 (Bad Request) si el partidaConcepto no es valido,
     * o con estatus de 500 (Internal Server Error) si el partidaConcepto no se puede actualizar
     * @throws URISyntaxException si la sintaxis de la URI no es correcta
     */
    @PutMapping("/partidaConcepto")
    @Timed
    public ResponseEntity<PartidaConcepto> updatePartidaConcepto(@Valid @RequestBody PartidaConcepto partidaConcepto) throws URISyntaxException {
        log.debug("REST request to update PartidaConcepto : {}", partidaConcepto);
        if (partidaConcepto.getId() == null) {
            return createPartidaConcepto(partidaConcepto);
        }
        PartidaConcepto result = service.save(partidaConcepto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, partidaConcepto.getId().toString()))
            .body(result);
    }


    /**
     * DELETE  /partidaConcepto/:id : borrar el PartidaConcepto con "id".
     *
     * @param id el id del PartidaConcepto que se desea borrar
     * @return el objeto ResponseEntity con estatus 200 (OK)
     */
    @DeleteMapping("/partidaConcepto/{id}")
    @Timed
    public ResponseEntity<Void> deletePartidaConcepto(@PathVariable String id) {
        log.debug("REST request to delete PartidaConcepto : {}", id);
        service.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/partidaConcepto?query=:query : buscar por el partidaConcepto correspondiente
     * to the query.
     *
     * @param query el query para el partidaConcepto que se desea buscar
     * @param pageable información de la paginación
     * @return el resultado de la busqueda
     */
    @GetMapping("/_search/partidaConcepto")
    @Timed
    public ResponseEntity<List<PartidaConcepto>> searchPartidaConcepto(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of PartidaConcepto for query {}", query);
        Page<PartidaConcepto> page = service.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/partidaConcepto");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
}