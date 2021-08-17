package ru.denis.financeApp.app.modules;

import dagger.Module;
import dagger.Provides;
import ru.denis.financeApp.app.annotations.WebScope;
import ru.denis.financeApp.model.Model;

@Module
public class WebModuleModel {
    @WebScope
    @Provides
    Model provideModel() {
        return new Model();
    }
}
