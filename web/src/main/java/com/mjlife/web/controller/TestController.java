package com.mjlife.web.controller;

import com.mjlife.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    public TestController() {
        System.out.println("------------------------");
    }

    @RequestMapping("/test.form")
    public void test() {
        System.out.println("--------------");
        testService.test();
    }

    @RequestMapping("/download.form")
    public ResponseEntity<byte[]> download() {
        HttpHeaders headers = new HttpHeaders();//设置响应头
        headers.add("Content-Disposition", "attachment;filename=test.xls");
        HttpStatus statusCode = HttpStatus.OK;//设置响应
        ResponseEntity<byte[]> result = new ResponseEntity<byte[]>(testService.getContent(), headers, statusCode);
        return result;
    }
}
