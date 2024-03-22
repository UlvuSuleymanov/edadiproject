package az.edadi.back.controller;

import az.edadi.back.constants.type.EntityType;
import az.edadi.back.entity.post.Post;
import az.edadi.back.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/")
public class Home {
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    String success() {
        Post post = new Post();
        post.setDate(new Date());
        post.setText("asads");
        post.setParent(EntityType.TOPIC);
        Post post1= postRepository.save(post);

        Post p = postRepository.findById(post.getId()).get();

        System.out.println(p.getParent());
        return "Hello World";
    }
}
