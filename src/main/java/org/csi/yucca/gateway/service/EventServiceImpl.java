package org.csi.yucca.gateway.service;

import org.springframework.stereotype.Service;

import org.csi.yucca.gateway.persist.entity.Event;
import org.csi.yucca.gateway.service.dto.EventDto;

@Service
public class EventServiceImpl extends GenericServiceImpl<Event, EventDto, Long> implements EventService {


}
