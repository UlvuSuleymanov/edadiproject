package az.edadi.back.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Home {

    @GetMapping
    String success() {
        return "I'm still standing";
    }
}
