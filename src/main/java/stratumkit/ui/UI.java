package stratumkit.ui;

import stratumkit.app.*;
import stratumkit.llui.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UI {
    private final App app;

    private final List<UIView> viewList;
    private int viewListPosition = -1;

    public UI(App app) {
        this.app = app;

        this.viewList = new ArrayList<>();
    }

    public UIView getCurrentView() {
        if (viewList.size() == 0) {
            return null;
        }

        return viewList.get(viewListPosition);
    }

    public void handleInput(AppInput input) {
        UIView view = getCurrentView();
        if (view == null) {
            return;
        }

        view.handleInput(input);
    }

    public void navigate(UIView view) {
        int nextIndex = viewListPosition + 1;

        if (nextIndex >= viewList.size()) {
            viewList.add(view);
        } else {
            viewList.set(nextIndex, view);
        }

        viewListPosition = nextIndex;
    }

    public void render() throws IOException {
        UIView view = getCurrentView();
        if (view == null) {
            return;
        }

        RequestGenerator requestGenerator = new RequestGenerator();
        Graphics gfx = requestGenerator.getGraphics();

        view.render(gfx);

        byte[] payload = requestGenerator.getRequest();

        app.sendCommand(AppServerCommand.SENDUI, payload);
    }
}
