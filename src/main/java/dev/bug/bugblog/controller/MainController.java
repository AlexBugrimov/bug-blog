package dev.bug.bugblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public Mono<String> index() {
        return Mono.just("index");
    }
}
