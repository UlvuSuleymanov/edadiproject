package az.edadi.back.controller;

import az.edadi.back.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class Home {
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    String success() {
        return "Hello World";
    }
}
