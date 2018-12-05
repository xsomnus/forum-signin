package com.xsomnus.forumsignin.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author somnus_xiawenye
 * @since 2018/12/5 0005 11:11
 */

public class MemberControllerTest {

    @Test
    public void addMembers() {
        ExcelReader reader = ExcelUtil.getReader("G:\\java\\playgrounds\\forum-signin\\src\\main\\resources\\xls\\infoList.xls");
        List<List<Object>> readAll = reader.read();
        System.out.println(readAll);
    }
}