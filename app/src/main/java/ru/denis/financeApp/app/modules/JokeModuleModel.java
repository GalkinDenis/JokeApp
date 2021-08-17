package ru.denis.financeApp.app.modules;

import dagger.Module;
import dagger.Provides;
import ru.denis.financeApp.app.annotations.JokeScope;
import ru.denis.financeApp.model.Model;

@Module
public class JokeModuleModel {
    @JokeScope
    @Provides
    Model provideModel() {
        return new Model();
    }
}
