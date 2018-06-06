package com.github.wuchao.webproject.controller.app.message;

import com.github.wuchao.webproject.domain.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageDto {
    private String content;
    private User user;

    public MessageDto content(String content) {
        this.content = content;
        return this;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
