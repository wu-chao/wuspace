package com.wuspace.admin.controller.blogs;

import com.wuspace.domain.Blog;

public class BlogCommmand {

    private String title;
    private String content;

    public Blog setBlog(Blog blog) {
        if (blog == null) {
            blog = new Blog();
        }

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
