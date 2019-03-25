package dev.bug.bugblog.repository;

import dev.bug.bugblog.domain.Subscriber;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SubscriberRepository extends ReactiveMongoRepository<Subscriber, String> {
}
