package com.pedro.raspberry.poule.audit;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pierre on 05/07/2020.
 */
@Repository
public interface AuditRepository extends PagingAndSortingRepository<Audit, Long> {
    Audit findById(long id);
}