package com.example.myviewmodelapp.factory

import com.example.myviewmodelapp.model.RepoResponse
import com.example.myviewmodelapp.model.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GitService {
    @GET("users/{user_name}/repos")
    fun getRepositories(@Path("user_name") userName: String): Observable<List<RepoResponse>>

    @GET("users/{user_name}")
    fun getSingleUser(@Path("user_name") userName: String): Observable<UserResponse>
}
