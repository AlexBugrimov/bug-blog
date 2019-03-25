package dev.bug.bugblog.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

@EqualsAndHashCode(callSuper = true)
@Data
public class BlogEvent extends ApplicationEvent {

    private Blog blog;

    public BlogEvent(Object source, Blog blog) {
        super(source);
        this.blog = blog;
    }
}
