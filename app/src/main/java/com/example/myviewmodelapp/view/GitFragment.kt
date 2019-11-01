package com.example.myviewmodelapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myviewmodelapp.R
import com.example.myviewmodelapp.adapter.RecyclerViewAdapter
import com.example.myviewmodelapp.model.RepoResponse
import com.example.myviewmodelapp.model.UserResponse
import com.example.myviewmodelapp.viewmodel.MainViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.repository_fragment_layout.*
import kotlinx.android.synthetic.main.user_fragment_layout.*

class GitFragment : Fragment() {

    companion object {
        private const val fragmentIdKey = "fragment_id"
        const val fragmentCount = 2

        fun provideFragment(fragmentId: Int): GitFragment {
            val fragment = GitFragment()
            val bundle = Bundle()
            bundle.putInt(fragmentIdKey, (fragmentId + 1))
            fragment.arguments = bundle
            return fragment
        }
    }

    val compositeDisposable = CompositeDisposable()
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        lateinit var fragmentView: View
        arguments?.getInt(fragmentIdKey)?.let { fragmentId ->
            when (fragmentId) {
                1 -> {
                    fragmentView =
                        layoutInflater.inflate(R.layout.user_fragment_layout, container, false)
                }
                2 -> {
                    fragmentView = layoutInflater.inflate(
                        R.layout.repository_fragment_layout,
                        container,
                        false
                    )
                }
            }
        }

        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)

        arguments?.getInt(fragmentIdKey)?.let { fragmentId ->
        }
    }

    fun makeCalls(userName: String) {
        Log.d("TAG_X", "Make calls")
        compositeDisposable.addAll(
            viewModel.getGitRepositories(userName).subscribe({ repoResponse ->
                Log.d("TAG_X", repoResponse.size.toString())
                displayRepositoryResponse(repoResponse)
            }, { throwable ->
                Log.e("TAG_Error", throwable.message)
            }),
            viewModel.getGitUser(userName).subscribe({ userResponse ->
                displayGitUser(userResponse)
            }, { throwable ->
                Log.e("TAG_Error", throwable.message)
            })
        )
    }

    private fun displayRepositoryResponse(repositories: List<RepoResponse>) {
        if (arguments?.getInt(fragmentIdKey) == 2) {
            git_repository_recyclerview.adapter = RecyclerViewAdapter(repositories)
            git_repository_recyclerview.layoutManager = LinearLayoutManager(requireContext())
            (git_repository_recyclerview.adapter as RecyclerViewAdapter).notifyDataSetChanged()
        }
    }

    private fun displayGitUser(userResponse: UserResponse) {
        if (arguments?.getInt(fragmentIdKey) == 1) {
            Glide.with(requireActivity())
                .load(userResponse.avatarUrl)
                .into(profile_picture_imageview)

            username_textview.text = userResponse.name.toString()

            number_of_repos_textview.text = userResponse.publicRepos.toString()
        }
    }


}