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

import mx.infotec.dads.costos.domain.DataFrameType;
import mx.infotec.dads.costos.service.DataFrameTypeService;

/**
 * 
 * @author kukulkan
 * @kukulkanGenerated 20181109143229
 */
@RestController
@RequestMapping("/api")
public class DataFrameTypeResource {

    private static final Logger log = LoggerFactory.getLogger(DataFrameTypeResource.class);
    
    private static final String ENTITY_NAME = "dataFrameType";

    @Autowired
    private DataFrameTypeService service;
    
    /**
     * GET  /dataFrameType : recupera todos los dataFrameType.
     *
     * @param pageable información de paginación
     * @return El objeto ResponseEntity con estado de 200 (OK) y la lista de dataFrameType en el cuerpo del mensaje
     */
    @GetMapping("/dataFrameType")
    @Timed
    public ResponseEntity<List<DataFrameType>> getAllDataFrameType(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of DataFrameType");
        Page<DataFrameType> page = service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dataFrameType");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /dataFrameType/:id : recupera por "id" de DataFrameType.
     *
     * @param id el id del DataFrameType que se desea recuperar
     * @return El objeto ResponseEntity con el estado de 200 (OK) y dentro del cuerpo del mensaje el DataFrameType, o con estado de 404 (Not Found)
     */
    @GetMapping("/dataFrameType/{id}")
    @Timed
    public ResponseEntity<DataFrameType> getDataFrameType(@PathVariable String id) {
        log.debug("REST request to get DataFrameType : {}", id);
        DataFrameType dataFrameType = service.findById(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dataFrameType));
    }

    /**
     * POST  /dataFrameType : Create a new usuario.
     *
     * @param dataFrameType el dataFrameType que se desea crear
     * @return El objeto ResponseEntity con estado 201 (Created) y en el cuerpo un nuevo dataFrameType, o con estado 400 (Bad Request) si el usuario ya tiene un ID
     * @throws URISyntaxException Si la sintaxis de la URI no es correcta
     */
    @PostMapping("/dataFrameType")
    @Timed
    public ResponseEntity<DataFrameType> createDataFrameType(@Valid @RequestBody DataFrameType dataFrameType) throws URISyntaxException {
        log.debug("REST request to save DataFrameType : {}", dataFrameType);
        if (dataFrameType.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new dataFrameType cannot already have an ID")).body(null);
        }
        DataFrameType result = service.save(dataFrameType);
        return ResponseEntity.created(new URI("/api/dataFrameType/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * PUT  /dataFrameType : Actualiza un DataFrameType existente.
     *
     * @param dataFrameType el dataFrameType que se desea actualizar
     * @return el objeto ResponseEntity con estado de 200 (OK) y en el cuerpo de la respuesta el DataFrameType actualizado,
     * o con estatus de 400 (Bad Request) si el dataFrameType no es valido,
     * o con estatus de 500 (Internal Server Error) si el dataFrameType no se puede actualizar
     * @throws URISyntaxException si la sintaxis de la URI no es correcta
     */
    @PutMapping("/dataFrameType")
    @Timed
    public ResponseEntity<DataFrameType> updateDataFrameType(@Valid @RequestBody DataFrameType dataFrameType) throws URISyntaxException {
        log.debug("REST request to update DataFrameType : {}", dataFrameType);
        if (dataFrameType.getId() == null) {
            return createDataFrameType(dataFrameType);
        }
        DataFrameType result = service.save(dataFrameType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dataFrameType.getId().toString()))
            .body(result);
    }


    /**
     * DELETE  /dataFrameType/:id : borrar el DataFrameType con "id".
     *
     * @param id el id del DataFrameType que se desea borrar
     * @return el objeto ResponseEntity con estatus 200 (OK)
     */
    @DeleteMapping("/dataFrameType/{id}")
    @Timed
    public ResponseEntity<Void> deleteDataFrameType(@PathVariable String id) {
        log.debug("REST request to delete DataFrameType : {}", id);
        service.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/dataFrameType?query=:query : buscar por el dataFrameType correspondiente
     * to the query.
     *
     * @param query el query para el dataFrameType que se desea buscar
     * @param pageable información de la paginación
     * @return el resultado de la busqueda
     */
    @GetMapping("/_search/dataFrameType")
    @Timed
    public ResponseEntity<List<DataFrameType>> searchDataFrameType(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of DataFrameType for query {}", query);
        Page<DataFrameType> page = service.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/dataFrameType");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
}