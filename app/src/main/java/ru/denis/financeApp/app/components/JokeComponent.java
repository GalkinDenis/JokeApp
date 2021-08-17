package ru.denis.financeApp.app.components;

import dagger.Subcomponent;
import ru.denis.financeApp.app.annotations.JokeScope;
import ru.denis.financeApp.app.modules.JokeModuleController;
import ru.denis.financeApp.app.modules.JokeModuleModel;
import ru.denis.financeApp.joke_fragment.JokeFragment;

@JokeScope
@Subcomponent(modules = {JokeModuleController.class, JokeModuleModel.class})
public interface JokeComponent {
    void injectJokeList(JokeFragment view);
}