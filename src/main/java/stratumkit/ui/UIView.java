package stratumkit.ui;

import stratumkit.llui.Graphics;
import stratumkit.app.AppInput;
import stratumkit.ui.components.UIComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UIView {
    private final UI ui;

    private final List<UIComponent> componentList;

    public UIView(UI ui) {
        this.ui = ui;
        this.componentList = new ArrayList<>();
    }

    public UI getUI() {
        return ui;
    }

    public void add(UIComponent component) {
        componentList.add(component);
    }

    public void addAll(UIComponent ...components) {
        Collections.addAll(componentList, components);
    }

    public void remove(UIComponent component) {
        componentList.remove(component);
    }

    public void handleInput(AppInput input) {
        for (UIComponent component : componentList) {
            if (component.hitTest(input.getX(), input.getY())) {
                component.handleInput(input);
                return;
            }
        }
    }

    public void render(Graphics gfx) {
        for (UIComponent component : componentList) {
            component.render(gfx);
        }
    }
}
