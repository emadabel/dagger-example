package com.example.dagger2example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dagger2example.model.RandomUsers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    Retrofit mRetrofit;
    RecyclerView mRecyclerView;
    RandomUserAdapter mAdapter;

    Picasso mPicasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        Timber.plant(new Timber.DebugTree());

        File cacheFile = new File(this.getCacheDir(), "HttpCache");
        cacheFile.mkdirs();

        Cache cache = new Cache(cacheFile, 10*1000*1000);

        HttpLoggingInterceptor httpLoggingInterceptor = new
                HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String message) {
                Timber.i(message);
            }
        });

        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        OkHttp3Downloader okHttpDownloader = new OkHttp3Downloader(okHttpClient);

        mPicasso = new Picasso.Builder(this).downloader(okHttpDownloader).build();

        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        populateUsers();
    }

    private void populateUsers() {
        Call<RandomUsers> randomUsersCall = getRandomUserService().getRandomUsers(10);
        randomUsersCall.enqueue(new Callback<RandomUsers>() {
            @Override
            public void onResponse(Call<RandomUsers> call, Response<RandomUsers> response) {
                if (response.isSuccessful()) {
                    mAdapter = new RandomUserAdapter(mPicasso);
                    mAdapter.setItems(response.body().getResults());
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<RandomUsers> call, Throwable t) {
                Timber.i(t.getMessage());
            }
        });
    }

    public RandomUsersApi getRandomUserService() {
        return mRetrofit.create(RandomUsersApi.class);
    }
}
