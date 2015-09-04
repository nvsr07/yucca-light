package org.csi.yucca.gateway.web;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.csi.yucca.gateway.service.AttemptService;
import org.csi.yucca.gateway.service.dto.AttemptDto;

@Controller
@Scope("request")
public class AttemptController {

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private AttemptService attemptService;

    @Autowired
    private MessageSource ms;
   
    @RequestMapping(value = "/attempts", method = RequestMethod.GET)
    public @ResponseBody List<AttemptDto> attemptsList(String gwId) {
        return attemptService.findAll(gwId);
    }
}

 
