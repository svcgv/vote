/**
 * 文件名：CommonController
 * 版权： Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述： 无
 */

package com.indihx.baseTableUtil.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indihx.system.entity.CodeData;
import com.indihx.system.service.impl.CodeDataServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CodeDataServiceImpl ICodeDataService;

    private Logger logger = LoggerFactory.getLogger(CommonController.class);

    @RequestMapping(value = "/queryCode", produces = "application/javascript;charset=utf8")
    @ResponseBody
    public String getCommonCode(@RequestParam Map<String, String> params) throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        params.forEach((k, v) -> {
            List<CodeData> enabledCodeValues = ICodeDataService.getByCodeNo(v);
            try {
                if (enabledCodeValues == null ) {
                    toJson(sb, k, Collections.EMPTY_LIST);
                } else {
                    toJson(sb, k, enabledCodeValues);
                }
                sb.append("\n");
            } catch (JsonProcessingException e) {
                if (logger.isErrorEnabled()) {
                    logger.error(e.getMessage(), e);
                }
            }
        });
        return sb.toString();
    }

    /**
     * 基础数据转json格式字符串
     * @param sb
     * @param var
     * @param data
     * @throws JsonProcessingException
     */
    private void toJson(StringBuilder sb, String var, Object data) throws JsonProcessingException {
        boolean hasVar = var != null && var.length() > 0;
        if (hasVar) {
            sb.append("var ").append(var).append('=');
        }
        sb.append(objectMapper.writeValueAsString(data));
        if (hasVar) {
            sb.append(';');
        }
    }
}
