package com.example.dagger2example;

import android.app.Activity;
import android.app.Application;

import com.example.dagger2example.module.ContextModule;

import timber.log.Timber;

public class RandomUserApplication extends Application {

    private RandomUserComponent randomUserApplicationComponent;

    public static RandomUserApplication get(Activity activity) {
        return (RandomUserApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        randomUserApplicationComponent = DaggerRandomUserComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public RandomUserComponent getRandomUserApplicationComponent() {
        return randomUserApplicationComponent;
    }
}
