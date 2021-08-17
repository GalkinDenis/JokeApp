package ru.denis.financeApp.app.modules;

import dagger.Module;
import dagger.Provides;
import ru.denis.financeApp.app.annotations.JokeScope;
import ru.denis.financeApp.model.Model;
import ru.denis.financeApp.presenter.Controller;

@Module
public class JokeModuleController {
    @JokeScope
    @Provides
    Controller provideController(Model model) {
        return new Controller(model);
    }
}