package ru.denis.financeApp.app;

import android.app.Application;
import ru.denis.financeApp.app.components.AppComponent;
import ru.denis.financeApp.app.components.DaggerAppComponent;
import ru.denis.financeApp.app.components.JokeComponent;
import ru.denis.financeApp.app.components.WebComponent;

public class App extends Application  {

    public static JokeComponent joke;
    public static WebComponent web;

    @Override
    public void onCreate() {
        super.onCreate();
        AppComponent mainComponent = DaggerAppComponent.create();
        joke = mainComponent.getJokeComponent();
        web = mainComponent.getWebComponent();
    }
}

