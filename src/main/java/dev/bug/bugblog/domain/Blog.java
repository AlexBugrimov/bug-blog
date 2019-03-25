package dev.bug.bugblog.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.var;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;

@Data
@Document(collection = "blog")
public class Blog {

    @Id
    @JsonView(View.Basic.class)
    private String id;

    @JsonView(View.Basic.class)
    private String subject;

    @JsonView(View.Body.class)
    private String body;

    @JsonView(View.Basic.class)
    private String bodyMin;

    @JsonView(View.Basic.class)
    private Set<String> hashTags;

    @JsonView(View.Basic.class)
    private Long views;

    @JsonView(View.Full.class)
    private LocalDate createdOn;

    @JsonView(View.Basic.class)
    private String prettyCreatedOn;

    @JsonView(View.Full.class)
    private Boolean closed;

    public BlogForm toBlogForm() {
        var blogForm = new BlogForm();
        blogForm.setSubject(this.subject);
        blogForm.setBody(this.body);
        blogForm.setHashTags(String.join(" ", this.hashTags));
        return blogForm;
    }
}
