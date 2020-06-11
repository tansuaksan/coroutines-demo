package com.example.demo

import org.springframework.data.repository.kotlin.CoroutineSortingRepository

interface ReactivePostRepository : CoroutineSortingRepository<Post, Long> {

}