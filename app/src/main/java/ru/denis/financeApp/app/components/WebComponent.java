package ru.denis.financeApp.app.components;

import dagger.Subcomponent;
import ru.denis.financeApp.app.annotations.WebScope;
import ru.denis.financeApp.app.modules.WebModuleController;
import ru.denis.financeApp.app.modules.WebModuleModel;
import ru.denis.financeApp.web_fragment.WebFragment;

@WebScope
@Subcomponent(modules = {WebModuleController.class, WebModuleModel.class})
public interface WebComponent {
    void injectWebFragment(WebFragment view);
}
