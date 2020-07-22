package com.pedro.raspberry.poule.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by pierre on 05/07/2020.
 */
@Service
public class AuditService {

    @Autowired
    private AuditRepository repository;

    public void audit(String comment) {
        repository.save(new Audit(new Date(), comment));
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
