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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import mx.infotec.dads.costos.domain.DataFrameType;
import mx.infotec.dads.costos.repository.DataFrameTypeRepository;
import mx.infotec.dads.costos.service.DataFrameTypeService;

/**
 * DataFrameTypeServiceImpl
 * 
 * @author kukulkan
 * @kukulkanGenerated 20181109143229
 */
@Service
@Transactional
public class DataFrameTypeServiceImpl implements DataFrameTypeService {

    private final Logger log = LoggerFactory.getLogger(DataFrameTypeServiceImpl.class);

    @Autowired
    private DataFrameTypeRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<DataFrameType> findAll(Pageable pageable) {
        log.debug("Request to get all DataFrameType");
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public DataFrameType findById(String id) {
        log.debug("Request to get DataFrameType : {}", id);
        return repository.findOne(id);
    }

    @Override
    public DataFrameType save(DataFrameType dataFrameType) {
        return repository.save(dataFrameType);
    }

    @Override
    public boolean exists(String id) {
        return repository.exists(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete DataFrameType : {}", id);
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        log.debug("Request to delete All DataFrameType");
        repository.deleteAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<DataFrameType> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of DataFrameType");
        return repository.findAll(pageable);
    }
}
