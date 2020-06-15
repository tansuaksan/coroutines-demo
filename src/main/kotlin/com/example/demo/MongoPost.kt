package com.example.demo

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class MongoPost(
        val id: Long,
        val title: String
)