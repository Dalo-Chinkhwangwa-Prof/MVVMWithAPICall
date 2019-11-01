package com.example.myviewmodelapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.myviewmodelapp.factory.GitFactory
import com.example.myviewmodelapp.model.RepoResponse
import com.example.myviewmodelapp.model.UserResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val gitFactory = GitFactory()

    fun getGitRepositories(username: String): Observable<List<RepoResponse>> {
        return gitFactory.getUserRepositories(username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getGitUser(username: String): Observable<UserResponse> {
        return gitFactory.getSingleUser(username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}
