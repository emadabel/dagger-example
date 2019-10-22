package com.example.dagger2example;

import com.example.dagger2example.interfaces.RandomUserApplicationScope;
import com.example.dagger2example.interfaces.RandomUsersApi;
import com.example.dagger2example.module.PicassoModule;
import com.example.dagger2example.module.RandomUsersModule;
import com.squareup.picasso.Picasso;

import dagger.Component;

@RandomUserApplicationScope
@Component(modules = {RandomUsersModule.class, PicassoModule.class})
public interface RandomUserComponent {
    RandomUsersApi getRandomUserService();
    Picasso getPicasso();
}
