package com.example.paktunes.repository

import android.util.Log
import com.example.paktunes.data.entities.User
import com.example.paktunes.utill.Constants.USER
import com.example.paktunes.utill.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepository(
    val firebaseAuth: FirebaseAuth,
    val firestore: FirebaseFirestore
) {
    val logTag = "AuthRepository"

//    fun getCurrentUser(): FirebaseUser? {
//        return firebaseAuth.currentUser
//    }
    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    suspend fun signUp(user: User): Resource<FirebaseUser> {
        return try {
            val authResult =
                firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await()
            val firebaseUser = authResult.user ?: return Resource.Error("User is null")

            firestore.collection(USER)
                .document(firebaseUser.uid)
                .set(user)
                .await()

            Resource.Success(firebaseUser)

        } catch (e: Exception) {

            Log.d(logTag, e.message.toString())
            Resource.Error(e.message ?: "Unknown error")
        }

    }

    suspend fun signIn(userName: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(userName, password).await()
            val user = result.user
            if (user != null) {
                Resource.Success(user)
            } else {
                Resource.Error("User object is null")
            }
        } catch (e: Exception) {
            Log.e(logTag, "Sign-in failed: ${e.message}", e)
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    fun signOut(){
        firebaseAuth.signOut()
    }


}