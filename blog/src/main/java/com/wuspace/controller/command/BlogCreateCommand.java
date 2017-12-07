package com.wuspace.controller.command;

import com.wuspace.domain.Blog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogCreateCommand {

    private String title;

    private String content;

    public Blog toBlog(Blog blog) {
        if (blog == null) {
            blog = new Blog();
        }

        blog.setTitle(this.getTitle());
        blog.setContent(this.getContent());

        return blog;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}