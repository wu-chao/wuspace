package com.wuspace.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class BaseIdEntity implements java.io.Serializable {

    private Long id;
}
