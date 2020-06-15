package com.example.demo

import kotlinx.coroutines.runBlocking
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class DataInitializer(private val postClientRepository: PostClientRepository) {

    @EventListener(value = [ApplicationReadyEvent::class])
    fun init() {
        println(" start data initialization  ...")
        runBlocking {
            val deleted = postClientRepository.deleteAll()
            println(" $deleted posts removed.")
            postClientRepository.save(Post(title = "My first post title", content = "Content of my first post", parent = null))
            postClientRepository.save(Post(title = "My second post title", content = "Content of my second post", parent = null))
        }

        println(" done data initialization  ...")
    }
}