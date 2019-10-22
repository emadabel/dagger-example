package com.example.dagger2example;

import com.squareup.picasso.Picasso;

import dagger.Component;

@Component
public interface RandomUserComponent {
    RandomUsersApi getRandomUserService();
    Picasso getPicasso();
}
