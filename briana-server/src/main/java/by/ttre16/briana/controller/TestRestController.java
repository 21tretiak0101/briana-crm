package by.ttre16.briana.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestRestController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Message test() {
       return new Message("working!");
    }
}
