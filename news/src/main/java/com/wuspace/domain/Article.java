package com.wuspace.domain;

import com.wuspace.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "categoryId"}, callSuper = true)
@Entity
@Table(name = "articles")
public class Article extends Works implements Serializable {



}