package com.xsomnus.forumsignin.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author somnus_xiawenye
 * @since 2018/12/5 0005 11:11
 */

public class MemberControllerTest {


    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void addMembers() throws JsonProcessingException {
        ExcelReader reader = ExcelUtil.getReader("G:\\java\\playgrounds\\forum-signin\\src\\main\\resources\\xls\\infoList.xls");
        List<Map<String, Object>> maps = reader.readAll();
        String s = objectMapper.writeValueAsString(maps);
        System.out.println(s);
    }
}