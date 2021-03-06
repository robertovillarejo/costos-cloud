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
package mx.infotec.dads.costos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import mx.infotec.dads.costos.domain.PartidaConcepto;
import mx.infotec.dads.costos.repository.PartidaConceptoRepository;
import mx.infotec.dads.costos.service.PartidaConceptoService;

/**
 * PartidaConceptoServiceImpl
 * 
 * @author kukulkan
 * @kukulkanGenerated 20181109143229
 */
@Service
@Transactional
public class PartidaConceptoServiceImpl implements PartidaConceptoService {

    private final Logger log = LoggerFactory.getLogger(PartidaConceptoServiceImpl.class);

    @Autowired
    private PartidaConceptoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<PartidaConcepto> findAll(Pageable pageable) {
        log.debug("Request to get all PartidaConcepto");
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PartidaConcepto> findById(String id) {
        log.debug("Request to get PartidaConcepto : {}", id);
        return repository.findById(id);
    }

    @Override
    public PartidaConcepto save(PartidaConcepto partidaConcepto) {
        return repository.save(partidaConcepto);
    }

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete PartidaConcepto : {}", id);
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        log.debug("Request to delete All PartidaConcepto");
        repository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PartidaConcepto> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PartidaConcepto");
        return repository.findAll(pageable);
    }

    @Override
    public Optional<PartidaConcepto> findByExample(Example<PartidaConcepto> partidaConceptoExample) {
        return repository.findOne(partidaConceptoExample);
    }
}
