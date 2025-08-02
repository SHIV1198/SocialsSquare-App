package com.socials.square.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "test_posts")
public class Post {
    @Id
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "content")
    private String content;


}
