package com.example.dagger2example;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dagger2example.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RandomUserAdapter extends RecyclerView.Adapter<RandomUserAdapter.RandomUserViewHolder> {

    private List<Result> mResultList = new ArrayList<>();

    public RandomUserAdapter() {
    }

    @NonNull
    @Override
    public RandomUserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,
                viewGroup, false);
        return new RandomUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomUserViewHolder holder, int position) {
        Result result = mResultList.get(position);
        holder.mTextView.setText(String.format("%s %s", result.getName().getFirst(), result.getName().getLast()));
        Picasso.get().load(result.getPicture().getLarge()).into(holder.mImageView);
    }

    public void setItems(List<Result> results) {
        mResultList = results;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }

    public class RandomUserViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;

        public RandomUserViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.name);
            mImageView = itemView.findViewById(R.id.image);
        }
    }
}
