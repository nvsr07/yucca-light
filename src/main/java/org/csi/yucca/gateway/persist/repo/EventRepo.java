package org.csi.yucca.gateway.persist.repo;

import org.csi.yucca.gateway.persist.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepo extends JpaRepository<Event, Long> {

}
