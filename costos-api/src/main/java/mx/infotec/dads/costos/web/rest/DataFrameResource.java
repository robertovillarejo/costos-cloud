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

import static mx.infotec.dads.costos.web.rest.util.TikaUtil.detectDocType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import mx.infotec.dads.costos.config.ApplicationProperties;
import mx.infotec.dads.costos.domain.DataFrame;
import mx.infotec.dads.costos.service.DataFrameService;
import mx.infotec.dads.costos.web.rest.util.HeaderUtil;
import mx.infotec.dads.costos.web.rest.util.PaginationUtil;

/**
 * 
 * @author kukulkan
 * @kukulkanGenerated 20181109143229
 */
@RestController
@RequestMapping("/api")
public class DataFrameResource {

    private static final Logger log = LoggerFactory.getLogger(DataFrameResource.class);

    private static final String ENTITY_NAME = "dataFrame";

    @Autowired
    private DataFrameService service;

    @Autowired
    private ApplicationProperties appProperties;

    /**
     * GET /dataFrame : recupera todos los dataFrame.
     *
     * @param pageable
     *            información de paginación
     * @return El objeto ResponseEntity con estado de 200 (OK) y la lista de
     *         dataFrame en el cuerpo del mensaje
     */
    @GetMapping("/dataFrame")
    @Timed
    public ResponseEntity<List<DataFrame>> getAllDataFrame(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of dataFrame");
        Page<DataFrame> page = service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dataFrame");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET /dataFrame/:id : recupera por "id" de DataFrame.
     *
     * @param id
     *            el id del DataFrame que se desea recuperar
     * @return El objeto ResponseEntity con el estado de 200 (OK) y dentro del
     *         cuerpo del mensaje el DataFrame, o con estado de 404 (Not Found)
     */
    @GetMapping("/dataFrame/{id}")
    @Timed
    public ResponseEntity<DataFrame> getDataFrameFile(@PathVariable String id) {
        log.debug("REST request to get DataFrame : {}", id);
        DataFrame dataFrame = service.findById(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dataFrame));
    }

    /**
     * POST /dataFrame : Create a new dataFrame.
     *
     * @param dataFrame
     *            el dataFrame que se desea crear
     * @return El objeto ResponseEntity con estado 201 (Created) y en el cuerpo un
     *         nuevo dataFrame, con estado 400 (Bad Request) si el usuario ya tiene
     *         un ID, o con estado 415 (Unsupported Media Type) si se ha detectado
     *         que el dataFrame no corresponde a un MediaType permitido
     * @throws URISyntaxException
     *             Si la sintaxis de la URI no es correcta
     */
    @PostMapping(path = "/dataFrame")
    @Timed
    public ResponseEntity<Void> createDataFrame(@Valid @RequestBody DataFrame dataFrame) throws URISyntaxException {
        log.debug("REST request to save DataFrame : {}", dataFrame);
        String mediaType = "";

        if (dataFrame.getId() != null) {
            return ResponseEntity.badRequest().headers(
                    HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new dataFrame cannot already have an ID"))
                    .build();
        }
        try {
            mediaType = detectDocType(new ByteArrayInputStream(dataFrame.getFile()));
        } catch (IOException e) {
            log.error("Exception while detecting media type: {}", e);
        }

        /**
         * Configura los MediaType permitidos en
         * 'src/main/resources/config/application.yml'
         */
        for (String allowedMediaType : appProperties.getAllowedDataFrameMediaTypes()) {
            if (allowedMediaType.equals(mediaType)) {
                DataFrame result = service.save(dataFrame);
                return ResponseEntity.created(new URI("/api/dataFrame/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId())).build();
            }
        }

        log.info("Invalid Media Type: {}", mediaType);
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).headers(
                HeaderUtil.createFailureAlert(ENTITY_NAME, "invalidfile", "The file should be an Excel Workbook"))
                .build();

    }

    /**
     * PUT /dataFrame : Actualiza un DataFrame existente.
     *
     * @param dataFrame
     *            el dataFrame que se desea actualizar
     * @return el objeto ResponseEntity con estado de 200 (OK) y en el cuerpo de la
     *         respuesta el DataFrame actualizado, o con estatus de 400 (Bad
     *         Request) si el dataFrame no es valido, o con estatus de 500 (Internal
     *         Server Error) si el dataFrame no se puede actualizar
     * @throws URISyntaxException
     *             si la sintaxis de la URI no es correcta
     */
    // @PutMapping("/dataFrame")
    // @Timed
    // public ResponseEntity<DataFrame> updateDataFrame(@Valid @RequestBody
    // DataFrame dataFrame)
    // throws URISyntaxException {
    // log.debug("REST request to update DataFrame : {}", dataFrame);
    // if (dataFrame.getId() == null) {
    // return createDataFrame(dataFrame);
    // }
    // DataFrame result = service.save(dataFrame);
    // return ResponseEntity.ok()
    // .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME,
    // dataFrame.getId().toString())).body(result);
    // }

    /**
     * DELETE /dataFrame/:id : borrar el DataFrame con "id".
     *
     * @param id
     *            el id del DataFrame que se desea borrar
     * @return el objeto ResponseEntity con estatus 200 (OK)
     */
    @DeleteMapping("/dataFrame/{id}")
    @Timed
    public ResponseEntity<Void> deleteDataFrame(@PathVariable String id) {
        log.debug("REST request to delete DataFrame : {}", id);
        service.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }

    /**
     * SEARCH /_search/dataFrame?query=:query : buscar por el dataFrame
     * correspondiente to the query.
     *
     * @param query
     *            el query para el dataFrame que se desea buscar
     * @param pageable
     *            información de la paginación
     * @return el resultado de la busqueda
     */
    @GetMapping("/_search/dataFrame")
    @Timed
    public ResponseEntity<List<DataFrame>> searchDataFrame(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of DataFrame for query {}", query);
        Page<DataFrame> page = service.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/dataFrame");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}