package com.pedro.raspberry.poule.ui.door;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoorRepository extends CrudRepository<Door, Long> {
    Door findById(long id);
}
