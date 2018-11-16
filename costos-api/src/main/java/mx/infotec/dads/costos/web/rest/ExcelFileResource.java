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

import mx.infotec.dads.costos.domain.ExcelFile;
import mx.infotec.dads.costos.service.ExcelFileService;
import mx.infotec.dads.costos.web.rest.util.HeaderUtil;
import mx.infotec.dads.costos.web.rest.util.PaginationUtil;

/**
 * 
 * @author kukulkan
 * @kukulkanGenerated 20181109143229
 */
@RestController
@RequestMapping("/api")
public class ExcelFileResource {

    private static final Logger log = LoggerFactory.getLogger(ExcelFileResource.class);

    private static final String ENTITY_NAME = "excelFile";

    @Autowired
    private ExcelFileService service;

    /**
     * GET /excelFile : recupera todos los excelFile.
     *
     * @param pageable
     *            información de paginación
     * @return El objeto ResponseEntity con estado de 200 (OK) y la lista de
     *         excelFile en el cuerpo del mensaje
     */
    @GetMapping("/excelFile")
    @Timed
    public ResponseEntity<List<ExcelFile>> getAllExcelFile(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of ExcelFile");
        Page<ExcelFile> page = service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/excelFile");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET /excelFile/:id : recupera por "id" de ExcelFile.
     *
     * @param id
     *            el id del ExcelFile que se desea recuperar
     * @return El objeto ResponseEntity con el estado de 200 (OK) y dentro del
     *         cuerpo del mensaje el ExcelFile, o con estado de 404 (Not Found)
     */
    @GetMapping("/excelFile/{id}")
    @Timed
    public ResponseEntity<ExcelFile> getExcelFile(@PathVariable String id) {
        log.debug("REST request to get ExcelFile : {}", id);
        ExcelFile excelFile = service.findById(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(excelFile));
    }

    /**
     * POST /excelFile : Create a new usuario.
     *
     * @param excelFile
     *            el excelFile que se desea crear
     * @return El objeto ResponseEntity con estado 201 (Created) y en el cuerpo un
     *         nuevo excelFile, o con estado 400 (Bad Request) si el usuario ya
     *         tiene un ID
     * @throws URISyntaxException
     *             Si la sintaxis de la URI no es correcta
     */
    @PostMapping(path = "/excelFile")
    @Timed
    public ResponseEntity<Void> createExcelFile(@RequestBody byte[] file) throws URISyntaxException {
        log.debug("REST request to save ExcelFile: {}", file);
        ExcelFile excelFile = new ExcelFile();
        excelFile.setFile(file);
        service.save(excelFile);
        return ResponseEntity.accepted().build();
    }
    // public ResponseEntity<ExcelFile> createExcelFile(@Valid @RequestBody
    // ExcelFile excelFile)
    // throws URISyntaxException {
    // log.debug("REST request to save ExcelFile : {}", excelFile);
    // if (excelFile.getId() != null) {
    // return ResponseEntity.badRequest().headers(
    // HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new excelFile
    // cannot already have an ID"))
    // .body(null);
    // }
    // ExcelFile result = service.save(excelFile);
    // return ResponseEntity.created(new URI("/api/excelFile/" + result.getId()))
    // .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME,
    // result.getId().toString())).body(result);
    // }

    /**
     * PUT /excelFile : Actualiza un ExcelFile existente.
     *
     * @param excelFile
     *            el excelFile que se desea actualizar
     * @return el objeto ResponseEntity con estado de 200 (OK) y en el cuerpo de la
     *         respuesta el ExcelFile actualizado, o con estatus de 400 (Bad
     *         Request) si el excelFile no es valido, o con estatus de 500 (Internal
     *         Server Error) si el excelFile no se puede actualizar
     * @throws URISyntaxException
     *             si la sintaxis de la URI no es correcta
     */
    // @PutMapping("/excelFile")
    // @Timed
    // public ResponseEntity<ExcelFile> updateExcelFile(@Valid @RequestBody
    // ExcelFile excelFile)
    // throws URISyntaxException {
    // log.debug("REST request to update ExcelFile : {}", excelFile);
    // if (excelFile.getId() == null) {
    // return createExcelFile(excelFile);
    // }
    // ExcelFile result = service.save(excelFile);
    // return ResponseEntity.ok()
    // .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME,
    // excelFile.getId().toString())).body(result);
    // }

    /**
     * DELETE /excelFile/:id : borrar el ExcelFile con "id".
     *
     * @param id
     *            el id del ExcelFile que se desea borrar
     * @return el objeto ResponseEntity con estatus 200 (OK)
     */
    @DeleteMapping("/excelFile/{id}")
    @Timed
    public ResponseEntity<Void> deleteExcelFile(@PathVariable String id) {
        log.debug("REST request to delete ExcelFile : {}", id);
        service.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH /_search/excelFile?query=:query : buscar por el excelFile
     * correspondiente to the query.
     *
     * @param query
     *            el query para el excelFile que se desea buscar
     * @param pageable
     *            información de la paginación
     * @return el resultado de la busqueda
     */
    @GetMapping("/_search/excelFile")
    @Timed
    public ResponseEntity<List<ExcelFile>> searchExcelFile(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of ExcelFile for query {}", query);
        Page<ExcelFile> page = service.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/excelFile");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}