package com.example.dagger2example.module;

import android.content.Context;

import com.example.dagger2example.interfaces.ApplicationContext;
import com.example.dagger2example.interfaces.RandomUserApplicationScope;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = OkHttpClientModule.class)
public class PicassoModule {

    @RandomUserApplicationScope
    @Provides
    public Picasso picasso(@ApplicationContext Context context, OkHttp3Downloader okHttpDownloader) {
        return new Picasso.Builder(context)
                .downloader(okHttpDownloader)
                .build();
    }

    @Provides
    public OkHttp3Downloader mOkHttpDownloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }
}
