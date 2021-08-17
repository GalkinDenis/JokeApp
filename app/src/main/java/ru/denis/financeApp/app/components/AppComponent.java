package ru.denis.financeApp.app.components;

import dagger.Component;

@Component
public interface AppComponent {
     JokeComponent getJokeComponent();
     WebComponent getWebComponent();
}
