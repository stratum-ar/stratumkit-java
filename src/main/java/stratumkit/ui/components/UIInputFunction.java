package stratumkit.ui.components;

import stratumkit.app.AppInput;

@FunctionalInterface
public interface UIInputFunction {
    void handleInput(UIComponent component, AppInput input);
}