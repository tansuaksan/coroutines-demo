package com.example.demo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.data.r2dbc.core.*
import org.springframework.stereotype.Component

@Component
class PostClientRepository(private val client: DatabaseClient) {

    suspend fun count(): Long =
            client.execute("SELECT COUNT(*) FROM post")
                    .asType<Long>().fetch().awaitOne()

    fun findAll(): Flow<Post> =
            client.select().from("post").asType<Post>().fetch().all().asFlow()

    suspend fun findOne(id: Long): Post? =
            client.execute("SELECT * FROM post WHERE id = \$1")
                    .bind(0, id).asType<Post>()
                    .fetch()
                    .awaitOneOrNull()

    suspend fun deleteAll(): Int =
            client.execute("DELETE FROM post")
                    .fetch()
                    .rowsUpdated()
                    .awaitSingle()

    suspend fun save(post: Post) =
            client.insert()
                    .into<Post>()
                    .table("post")
                    .using(post)
                    .await()
}