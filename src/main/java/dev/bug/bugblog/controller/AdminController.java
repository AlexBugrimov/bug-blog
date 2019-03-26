package dev.bug.bugblog.controller;

import dev.bug.bugblog.domain.Blog;
import dev.bug.bugblog.domain.BlogForm;
import dev.bug.bugblog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private BlogRepository blogRepository;

    @Autowired
    public AdminController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @GetMapping("/blog")
    public Mono<String> create(Model model) {
        model.addAttribute("blogForm", new BlogForm());
        return Mono.just("admin/create");
    }

    @PostMapping("/blog")
    public Mono<String> save(Mono<BlogForm> blogFormMono, Model model) {
        return blogRepository.insert(blogFormMono.map(BlogForm::toBlog))
                .doOnNext(
                        blog -> {
                    model.addAttribute("message", "It`s saved");
                    model.addAttribute("blogForm", new BlogForm());
                })
                .onErrorResume(
                        throwable -> {
                            model.addAttribute("errorMessage", "Cannot saved");
                            model.addAttribute("blogForm", blogFormMono.block());
                        return Mono.just(new Blog());
                        })
                .then(Mono.just("admin/create"));
    }
}
