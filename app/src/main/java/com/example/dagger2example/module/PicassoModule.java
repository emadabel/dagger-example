package com.example.dagger2example.module;

import android.content.Context;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = OkHttpClientModule.class)
public class PicassoModule {

    @Provides
    public Picasso picasso(Context context, OkHttp3Downloader okHttpDownloader) {
        return new Picasso.Builder(context)
                .downloader(okHttpDownloader)
                .build();
    }

    @Provides
    public OkHttp3Downloader mOkHttpDownloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }
}
