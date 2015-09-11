package org.csi.yucca.gateway.service;

import org.csi.yucca.gateway.persist.entity.StreamMetadataEntity;
import org.csi.yucca.gateway.persist.entity.StreamMetadataEntity.MetadataKey;
import org.csi.yucca.gateway.service.dto.StreamMetadataDto;

public interface StreamMetadataService extends GenericService<StreamMetadataEntity, StreamMetadataDto, MetadataKey>{

}
