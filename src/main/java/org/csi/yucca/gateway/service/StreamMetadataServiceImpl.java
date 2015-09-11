package org.csi.yucca.gateway.service;

import org.csi.yucca.gateway.persist.entity.StreamMetadataEntity;
import org.csi.yucca.gateway.persist.entity.StreamMetadataEntity.MetadataKey;
import org.csi.yucca.gateway.service.dto.StreamMetadataDto;
import org.springframework.stereotype.Service;
@Service
public class StreamMetadataServiceImpl extends GenericServiceImpl<StreamMetadataEntity, StreamMetadataDto, MetadataKey> implements StreamMetadataService{

}
