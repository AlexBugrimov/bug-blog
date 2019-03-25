package dev.bug.bugblog.repository;

import dev.bug.bugblog.domain.Blog;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.awt.print.Pageable;
import java.util.List;

public interface BlogRepository extends ReactiveMongoRepository<Blog, String> {

    Flux<Blog> findAllByClosedIsTrue(Pageable pageable);

    @Query(value = "{ hashTags : { $all : ?0 }}", sort = "{ createdOn : -1 }")
    Flux<Blog> findAllByClosedIsTrueAndHashTagsAndPageable(List<String> list, Pageable pageable);

}
