package dev.bug.bugblog.domain;

import lombok.Data;
import lombok.var;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;

@Data
public class BlogForm {

    private static final int MAX_LENGTH = 240;
    private static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    private String subject;
    private String body;
    private String hashTags;

    public Blog toBlog() {
        var blog = new Blog();
        blog.setSubject(this.subject);
        blog.setBody(this.body);
        blog.setBodyMin(
                this.body.length() > MAX_LENGTH ?
                        this.body.substring(0, MAX_LENGTH) :
                        this.body
        );
        var readyHashTags = new HashSet<String>();
        Arrays.stream(this.hashTags.split(" "))
                .filter(tag -> tag.length() > 2)
                .forEach(tag -> readyHashTags.add(tag.trim()));
        blog.setHashTags(readyHashTags);
        blog.setViews(0L);
        var date = LocalDate.now();
        blog.setCreatedOn(date);
        blog.setPrettyCreatedOn(date.format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM)));
        blog.setClosed(false);
        return blog;
    }
}
