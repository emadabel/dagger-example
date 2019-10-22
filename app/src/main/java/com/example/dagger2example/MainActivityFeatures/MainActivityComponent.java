package com.example.dagger2example.MainActivityFeatures;

import com.example.dagger2example.MainActivity;
import com.example.dagger2example.RandomUserComponent;

import dagger.Component;

@Component(modules = MainActivityModule.class, dependencies = RandomUserComponent.class)
@MainActivityScope
public interface MainActivityComponent {
    void injectMainActivity(MainActivity mainActivity);
}
