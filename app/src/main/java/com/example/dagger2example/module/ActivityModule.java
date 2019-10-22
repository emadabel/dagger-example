package com.example.dagger2example.module;

import android.content.Context;

import com.example.dagger2example.interfaces.RandomUserApplicationScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Context mContext;

    public ActivityModule(Context context) {
        mContext = context;
    }

    @Named("activity_context")
    @RandomUserApplicationScope
    @Provides
    public Context context() {
        return mContext;
    }
}
