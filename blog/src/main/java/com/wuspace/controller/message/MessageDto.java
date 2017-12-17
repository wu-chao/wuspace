package com.wuspace.controller.message;

import com.wuspace.domain.User;
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
}
