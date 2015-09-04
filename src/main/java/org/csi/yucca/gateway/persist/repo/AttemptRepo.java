package org.csi.yucca.gateway.persist.repo;

import org.csi.yucca.gateway.persist.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AttemptRepo extends JpaRepository<Attempt, Long> {

}
