package com.kunfei.bookshelf;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
public class hello {
    @RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}