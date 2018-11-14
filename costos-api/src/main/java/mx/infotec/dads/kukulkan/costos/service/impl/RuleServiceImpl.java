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
package mx.infotec.dads.kukulkan.costos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.infotec.dads.kukulkan.costos.RulePersistable;
import mx.infotec.dads.kukulkan.costos.repository.RuleRepository;
import mx.infotec.dads.kukulkan.costos.service.RuleService;

/**
 * RulePersistableServiceImpl
 * 
 * @author kukulkan
 * @kukulkanGenerated 20181109143229
 */
@Service
@Transactional
public class RuleServiceImpl implements RuleService {

    private final Logger log = LoggerFactory.getLogger(RuleServiceImpl.class);

    @Autowired
    private RuleRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<RulePersistable> findAll(Pageable pageable) {
        log.debug("Request to get all RulePersistable");
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public RulePersistable findById(String id) {
        log.debug("Request to get RulePersistable : {}", id);
        return repository.findOne(id);
    }

    @Override
    public RulePersistable save(RulePersistable rulePersistable) {
        return repository.save(rulePersistable);
    }

    @Override
    public boolean exists(String id) {
        return repository.exists(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete RulePersistable : {}", id);
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        log.debug("Request to delete All RulePersistable");
        repository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RulePersistable> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of RulePersistable");
        return repository.findAll(pageable);
    }
}
