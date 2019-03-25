package dev.bug.bugblog.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "subscribers")
public class Subscriber {

    @Id
    private String id;

    private String email;

    private Set<String> hashTags;

    private Long key;
}
