package org.csi.yucca.gateway.persist.repo;

import org.csi.yucca.gateway.persist.entity.StreamMetadataEntity;
import org.csi.yucca.gateway.persist.entity.StreamMetadataEntity.MetadataKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamMetadataRepo extends JpaRepository<StreamMetadataEntity, MetadataKey> {

}
