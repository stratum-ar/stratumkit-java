package stratumkit.ui.components.shapes;

import stratumkit.llui.Graphics;
import stratumkit.ui.Color;
import stratumkit.ui.components.UIComponent;
import stratumkit.ui.components.UIInputFunction;

public class Rectangle extends UIComponent {
    private Color color;

    public Rectangle(int x, int y, int width, int height, Color color, UIInputFunction inputFunction) {
        super(x, y, width, height, inputFunction);

        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void render(Graphics gfx) {
        gfx.fillRect(getX(), getY(), getWidth(), getHeight(), color.getRed(), color.getGreen(), color.getBlue());
    }
}
