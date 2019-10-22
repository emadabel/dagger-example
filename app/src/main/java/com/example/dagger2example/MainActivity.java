package com.example.dagger2example;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dagger2example.model.RandomUsers;
import com.example.dagger2example.module.ContextModule;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RandomUserAdapter mAdapter;

    Context mContext;
    Picasso mPicasso;

    RandomUsersApi mRandomUsersApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Timber.plant(new Timber.DebugTree());

        mContext = this;

        RandomUserComponent daggerRandomUserComponent = DaggerRandomUserComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        mPicasso = daggerRandomUserComponent.getPicasso();
        mRandomUsersApi = daggerRandomUserComponent.getRandomUserService();

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
        return mRandomUsersApi;
    }
}
