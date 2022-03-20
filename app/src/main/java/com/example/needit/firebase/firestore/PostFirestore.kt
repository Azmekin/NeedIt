package com.example.needit.firebase.firestore

import com.example.needit.firebase.models.Post
import com.example.needit.firebase.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

class PostFirestore {
    private val firestore = FirebaseFirestore.getInstance()

    fun set(post: Post){
        firestore.collection(Constants.POSTS).document(post.id).set(post, SetOptions.merge())
            .addOnFailureListener{e -> throw e }
    }

    fun delete(postId: String){
        firestore.collection(Constants.POSTS).document(postId).delete()
            .addOnFailureListener{ e -> throw e }
    }

    //TODO getAll не работает, сообщение в exception - this feature is not compiled into this build
    //решение взято отсюда https://medium.com/firebase-tips-tricks/how-to-read-data-from-cloud-firestore-using-get-bf03b6ee4953
    suspend fun getAll(): List<Post> {
        try{
            val posts = firestore.collection(Constants.POSTS).get().await().documents
                .mapNotNull { snapShot ->
                    snapShot.toObject(Post::class.java)
                }
            return posts
        }
        catch(e: Exception){
            throw e
        }
    }
}