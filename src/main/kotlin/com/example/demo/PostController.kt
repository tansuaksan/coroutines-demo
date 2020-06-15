package com.example.demo

import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController(private val postClientRepository: PostClientRepository,
                     private val mongoPostRepository: MongoPostRepository) {

    @GetMapping
    fun findAll(): Flow<Post> =
            postClientRepository.findAll()

    @GetMapping("{id}")
    suspend fun findOne(@PathVariable id: Long): MongoPost? =
            mongoPostRepository.findById(id) ?: throw RuntimeException()

    @PostMapping
    suspend fun save(@RequestBody mongoPost: MongoPost) =
            mongoPostRepository.save(mongoPost)

}