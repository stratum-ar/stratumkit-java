package stratumkit.app;

public class AppInput {
    private final int x;
    private final int y;
    private final boolean dragging;

    public AppInput(int x, int y, boolean dragging) {
        this.x = x;
        this.y = y;
        this.dragging = dragging;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDragging() {
        return dragging;
    }
}
