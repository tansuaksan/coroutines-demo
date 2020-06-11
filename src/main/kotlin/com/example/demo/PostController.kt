package com.example.demo

import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController(private val postRepository: PostRepository,
private val reactivePostRepository: ReactivePostRepository) {

    @GetMapping
    fun findAll(): Flow<Post> =
            postRepository.findAll()

    @GetMapping("{id}")
    suspend fun findOne(@PathVariable id: Long): Post? =
            reactivePostRepository.findById(id) ?: throw RuntimeException(id.toString())

    @PostMapping
    suspend fun save(@RequestBody post: Post) =
            postRepository.save(post)

}