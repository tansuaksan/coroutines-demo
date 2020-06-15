package com.example.demo

import org.springframework.data.annotation.Id

data class Post(
        @Id
        val id: Long? = null,
        val title: String? = null,
        val content: String? = null,
        var parent: Post? = null
)