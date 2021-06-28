package com.tech.news;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tech.news.ModelClasses.ArticlesItem;
import com.tech.news.ModelClasses.MainResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
 private RecyclerView newsRecycler;
 private Retrofit retrofit;
 private Newsinterface newsinterface;
    private ActionBar actionBar;
 private final String generalCategory="General",healthCategory="Health",sportCategory="Sports",technologyCategory="Technology";
  private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRecycler=findViewById(R.id.newsRecyclerView);
       bottomNavigationView=findViewById(R.id.bottomNavigation);
       progressBar=findViewById(R.id.progressBar);
       actionBar=getSupportActionBar();

       setNavigationListener();



        setNewsRetrofit("Gweneral");

    }

    private void setNavigationListener() {

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                int id=item.getItemId();
                switch (id){
                    case R.id.general:

                        setNewsRetrofit("General");
                    return true;
                    case R.id.tech:

                        setNewsRetrofit("Technology");

                        return true;


                    case R.id.business:

                        setNewsRetrofit("Business");
                        return true;

                    case R.id.sports:

                        setNewsRetrofit("Sports");
                        return true;

                    case R.id.health:

                        setNewsRetrofit("Health");
                        return true;

                    default:
                        return false;
                }
            }
        });


    }

    private void setNewsRetrofit(String category) {
        actionBar.setTitle(category);
        progressBar.setVisibility(View.VISIBLE);
        newsRecycler.setVisibility(View.INVISIBLE);


        retrofit=new Retrofit.Builder().baseUrl("https://newsapi.org/").addConverterFactory(GsonConverterFactory.create()).build();
        newsinterface=retrofit.create(Newsinterface.class);

        Call<MainResponse> responseCall=newsinterface.getNewsData(category);
        responseCall.enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if(response.isSuccessful()){

                    MainResponse mainResponse= response.body();
                    List<ArticlesItem> news=mainResponse.getArticles();


                    newsRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    NewsAdapter adapter=new NewsAdapter(MainActivity.this,news);
                    newsRecycler.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    progressBar.setVisibility(View.INVISIBLE);
                    newsRecycler.setVisibility(View.VISIBLE);

                }

                else{

                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {

            }
        });






    }





}