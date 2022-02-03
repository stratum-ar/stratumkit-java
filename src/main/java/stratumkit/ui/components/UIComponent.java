package stratumkit.ui.components;

import stratumkit.llui.Graphics;
import stratumkit.app.AppInput;

public abstract class UIComponent {
    private int x;
    private int y;
    private int width;
    private int height;

    private final UIInputFunction inputHandler;

    public UIComponent(int x, int y, int width, int height, UIInputFunction inputHandler) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.inputHandler = inputHandler;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean hitTest(int hitX, int hitY) {
        return (hitX >= x) && (hitY >= y) && (hitX < x + width) && (hitY < y + height);
    }

    public void handleInput(AppInput input) {
        if (inputHandler != null) {
            inputHandler.handleInput(this, input);
        }
    }
    public abstract void render(Graphics gfx);
}
