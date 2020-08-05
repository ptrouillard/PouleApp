package com.pedro.raspberry.poule.ui.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by pierre on 05/07/2020.
 */
@Service
public class AuditService {

    @Autowired
    private AuditRepository repository;

    @Transactional
    public void audit(String comment) {
        repository.save(new Audit(new Date(), comment));
    }

    @Transactional
    public void audit(String comment, long time) {
        repository.save(new Audit(new Date(), comment, time));
    }

    @Transactional
    public void audit(String comment, String remoteAddr) {
        repository.save(new Audit(new Date(), comment, 0L, remoteAddr));
    }

    @Transactional
    public void audit(String comment, long time, String remoteAddr) {
        repository.save(new Audit(new Date(), comment, time, remoteAddr));
    }

    @Transactional
    public void audit(String comment, String oldValue, String newValue) {
        repository.save(new Audit(new Date(), comment, 0L, null, oldValue, newValue));
    }

    private Pageable getPageable(final int page) {
       return new Pageable() {
           @Override
           public int getPageNumber() {
               return page;
           }

           @Override
           public int getPageSize() {
               return 10;
           }

           @Override
           public long getOffset() {
               return (page-1)*10;
           }

           @Override
           public Sort getSort() {
               return Sort.by(Sort.Direction.DESC, "date");
           }

           @Override
           public Pageable next() {
               return getPageable(page+1);
           }

           @Override
           public Pageable previousOrFirst() {
               return getPageable(page-1 < 1 ? 1 : page-1);
           }

           @Override
           public Pageable first() {
               return getPageable(1);
           }

           @Override
           public boolean hasPrevious() {
               return page > 1;
           }
       };
    }


    public Page<Audit> getPage(Integer page) {
        return repository.findAll(getPageable(page));
    }
}
