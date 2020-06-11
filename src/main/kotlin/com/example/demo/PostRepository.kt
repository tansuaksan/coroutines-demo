package com.example.demo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.data.r2dbc.core.*
import org.springframework.stereotype.Component

@Component
class PostRepository(private val client: DatabaseClient) {

    suspend fun count(): Long =
            client.execute("SELECT COUNT(*) FROM posts")
                    .asType<Long>().fetch().awaitOne()

    fun findAll(): Flow<Post> =
            client.select().from("posts").asType<Post>().fetch().all().asFlow()

    suspend fun findOne(id: Long): Post? =
            client.execute("SELECT * FROM posts WHERE id = \$1")
                    .bind(0, id).asType<Post>()
                    .fetch()
                    .awaitOneOrNull()

    suspend fun deleteAll() =
            client.execute("DELETE FROM posts")
                    .fetch()
                    .rowsUpdated()
                    .awaitSingle()

    suspend fun save(post: Post) =
            client.insert()
                    .into<Post>()
                    .table("posts")
                    .using(post)
                    .await()

    suspend fun init() {
        save(Post(title = "My first post title", content = "Content of my first post"))
        save(Post(title = "My second post title", content = "Content of my second post"))
    }

}