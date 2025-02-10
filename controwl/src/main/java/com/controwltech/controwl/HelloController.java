package com.controwltech.controwl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Marks this class as a REST controller
@RestController
public class HelloController {

    // Maps HTTP GET requests to /greeting to this method
    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        // Returns a greeting message, using the provided name or "World" by default
        return String.format("Hello, %s!", name);
    }
}
