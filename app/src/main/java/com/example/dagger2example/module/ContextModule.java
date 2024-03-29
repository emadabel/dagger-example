package com.example.dagger2example.module;

import android.content.Context;

import com.example.dagger2example.interfaces.ApplicationContext;
import com.example.dagger2example.interfaces.RandomUserApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    @ApplicationContext
    @RandomUserApplicationScope
    @Provides
    public Context context() {
        return mContext.getApplicationContext();
    }
}
