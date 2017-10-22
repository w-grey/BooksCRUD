package com.minidart.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minidart.spring.containers.SearchContainer;
import com.minidart.spring.containers.TestContainer;
import com.minidart.spring.orm.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringReader;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {
    @Resource(name = "bookService")
    private BookService bookService;
    @RequestMapping(value = "",method = RequestMethod.GET)
    public SearchContainer doGet(@RequestParam(value="param") String param){
        SearchContainer container;
        try {
            container=new ObjectMapper().readValue(new StringReader(param),SearchContainer.class);
        }
        catch (IOException e){
            container=new SearchContainer();
            container.setAuthor("Error");
            container.setTitle(e.getMessage());
        }
        return container;
    }
}