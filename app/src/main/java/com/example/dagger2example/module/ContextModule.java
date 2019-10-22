package com.example.dagger2example.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    @Provides
    public Context context() {
        return mContext.getApplicationContext();
    }
}
