package com.example.dagger2example.MainActivityFeatures;

import com.example.dagger2example.MainActivity;
import com.example.dagger2example.RandomUserAdapter;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private final MainActivity mMainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public RandomUserAdapter randomUserAdapter(Picasso picasso) {
        return new RandomUserAdapter(picasso);
    }
}
