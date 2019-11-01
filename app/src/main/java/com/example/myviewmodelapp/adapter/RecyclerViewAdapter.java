package com.example.myviewmodelapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myviewmodelapp.R;
import com.example.myviewmodelapp.model.RepoResponse;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyCustomViewHolder> {

    private List<RepoResponse> myRepositories;

    public RecyclerViewAdapter(List<RepoResponse> myRepositories) {
        this.myRepositories = myRepositories;
    }

    @NonNull
    @Override
    public MyCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.repository_item_layout,
                        parent,
                        false);
        return new MyCustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCustomViewHolder holder, int position) {
        holder.repositoryDescriptionText.setText(myRepositories.get(position).getDescription());
        holder.repositoryNameTextView.setText(myRepositories.get(position).getFullName());
    }

    @Override
    public int getItemCount() {
        return myRepositories.size();
    }

    class MyCustomViewHolder extends RecyclerView.ViewHolder {

        TextView repositoryNameTextView;
        TextView repositoryDescriptionText;

        public MyCustomViewHolder(@NonNull View itemView) {
            super(itemView);
            repositoryNameTextView = itemView.findViewById(R.id.repository_name_textview);
            repositoryDescriptionText = itemView.findViewById(R.id.description_textview);
        }
    }
}
