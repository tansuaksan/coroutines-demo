package com.example.demo

import org.springframework.data.repository.kotlin.CoroutineSortingRepository

interface MongoPostRepository : CoroutineSortingRepository<MongoPost, Long>