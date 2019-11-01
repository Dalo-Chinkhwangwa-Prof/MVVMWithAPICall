package com.example.myviewmodelapp.factory

import com.example.myviewmodelapp.model.RepoResponse
import com.example.myviewmodelapp.model.UserResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GitFactory {

    private lateinit var gitService: GitService

    private val BASE_URL = "https://api.github.com/"

    init {
        gitService = createServiceInstance(getRetrofitInstance())
    }

    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createServiceInstance(retrofitInstance: Retrofit): GitService {
        return retrofitInstance.create(GitService::class.java)
    }


    fun getSingleUser(userName: String): Observable<UserResponse> {
        return gitService.getSingleUser(userName)
    }

    fun getUserRepositories(userName: String): Observable<List<RepoResponse>> {
        return gitService.getRepositories(userName)
    }

}