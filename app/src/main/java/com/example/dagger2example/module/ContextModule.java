package com.example.dagger2example.module;

import android.content.Context;

public class ContextModule {

    Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    public Context context() {
        return mContext.getApplicationContext();
    }
}
