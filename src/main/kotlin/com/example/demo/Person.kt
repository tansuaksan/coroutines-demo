package com.example.demo

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("posts")
data class Post(
        @Id
        val id: Long? = null,
        val title: String? = null,
        val content: String? = null,
        var parent: Post? = null
)