package com.tech.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tech.news.ModelClasses.ArticlesItem;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
Context context;
List<ArticlesItem> newsList;



    public NewsAdapter(Context context, List<ArticlesItem> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);
        return new NewsHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull NewsAdapter.NewsHolder holder, int position) {
           ArticlesItem singleNewsItem=newsList.get(position);
           String title="",description="",imageUrl="";
           imageUrl=singleNewsItem.getUrlToImage();
           title=singleNewsItem.getTitle();
           description=singleNewsItem.getDescription();


           holder.newsTitle.setText(title);
           holder.newsDescription.setText(description);
        Glide.with(context).load(imageUrl).into(holder.newsImage);


    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder{
      TextView newsTitle,newsDescription;
      ImageView newsImage;
        public NewsHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            newsTitle=itemView.findViewById(R.id.title);
            newsDescription=itemView.findViewById(R.id.desc);
            newsImage=itemView.findViewById(R.id.newsImage);

        }
    }
}
