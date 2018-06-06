package com.github.wuchao.webproject.controller.app.command;

import com.github.wuchao.webproject.domain.MediaInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogCreateCommand {

    private String title;

    private String content;

    public MediaInfo toBlog(MediaInfo blog) {
        if (blog == null) {
            blog = new MediaInfo();
        }

        blog.setTitle(this.getTitle());

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
