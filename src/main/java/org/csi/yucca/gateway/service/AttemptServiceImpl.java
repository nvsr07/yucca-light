package org.csi.yucca.gateway.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.csi.yucca.gateway.persist.entity.Attempt;
import org.csi.yucca.gateway.service.dto.AttemptDto;

@Service
public class AttemptServiceImpl extends GenericServiceImpl<Attempt, AttemptDto, Long> implements AttemptService {

}
