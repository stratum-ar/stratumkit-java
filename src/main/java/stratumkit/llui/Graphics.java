package stratumkit.llui;

import stratumkit.ui.enums.Alignment;
import stratumkit.ui.enums.ButtonState;
import stratumkit.ui.enums.EditBoxState;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Graphics {
    private final RequestGenerator requestGenerator;

    public Graphics(RequestGenerator requestGenerator) {
        this.requestGenerator = requestGenerator;
    }

    public void drawLine(int sx, int sy, int ex, int ey, int r, int g, int b) {
        byte[] arr = {
                (byte) 16,
                (byte) sx,
                (byte) sy,
                (byte) ex,
                (byte) ey,
                (byte) r, (byte) g, (byte) b
        };
        requestGenerator.addByteArrayToRequest(arr);
    }

    public void fillEllipse(int x, int y, int width, int height, int r, int g, int b) {
        byte[] arr = {
                (byte) 17,
                (byte) x,
                (byte) y,
                (byte) width,
                (byte) height,
                (byte) r, (byte) g, (byte) b
        };
        requestGenerator.addByteArrayToRequest(arr);
    }

    public void fillRect(int x, int y, int width, int height, int r, int g, int b) {
        byte[] arr = {
                (byte) 18,
                (byte) x,
                (byte) y,
                (byte) width,
                (byte) height,
                (byte) r, (byte) g, (byte) b
        };
        requestGenerator.addByteArrayToRequest(arr);
    }

    public void drawQuadratic(int sx, int sy, int cx, int cy, int ex, int ey, int r, int g, int b) {
        byte[] arr = {
                (byte) 18,
                (byte) sx,
                (byte) sy,
                (byte) cx,
                (byte) cy,
                (byte) ex,
                (byte) ey,
                (byte) r, (byte) g, (byte) b
        };
        requestGenerator.addByteArrayToRequest(arr);
    }

    public void drawPolygon(int[] xs, int[] ys, int r, int g, int b) {
        byte[] array = new byte[2 * xs.length + 5];
        array[0] = (byte) 23;
        array[1] = (byte) xs.length;

        for (int i = 2; i < 2 * xs.length + 2; i++) {
            array[i] = i % 2 == 0 ? (byte) xs[(i - 2) / 2] : (byte) ys[(i - 2) / 2];
        }
        array[array.length - 3] = (byte) r;
        array[array.length - 2] = (byte) g;
        array[array.length - 1] = (byte) b;

        requestGenerator.addByteArrayToRequest(array);
    }

    public void drawText(int x, int y, String text, int r, int g, int b) {
        byte[] arr1 = {
                (byte) 30,
                (byte) x,
                (byte) y,
                (byte) text.length()
        };
        byte[] arr2 = {
                (byte) r,
                (byte) g,
                (byte) b
        };
        byte[] array = concatenateTwoArrays(
                concatenateTwoArrays(
                        arr1, text.getBytes(StandardCharsets.UTF_8)),
                arr2);
        requestGenerator.addByteArrayToRequest(array);
    }

    public void drawIcon(int x, int y, int iconNo, int r, int g, int b) {
        byte[] arr = {
                (byte) 22,
                (byte) x,
                (byte) y,
                (byte) iconNo,
                (byte) r, (byte) g, (byte) b
        };
        requestGenerator.addByteArrayToRequest(arr);
    }

    public void drawAlignedText(int x, int y, int width, int height, Alignment alignment,
                                String text, int r, int g, int b){
        byte [] arr1 = {
                (byte) 32,
                (byte) x,
                (byte) y,
                (byte) width,
                (byte) height,
                (byte) alignment.ordinal(),
                (byte) text.length(),
        };
        byte[] arr2 = {
                (byte) r,
                (byte) g,
                (byte) b
        };

        byte[] array = concatenateTwoArrays(
                concatenateTwoArrays(
                        arr1, text.getBytes(StandardCharsets.UTF_8)),
                arr2);
        requestGenerator.addByteArrayToRequest(array);
    }

    public void drawButton(int x, int y, int width, int height, ButtonState buttonState, String text,
                           int ix, int iy, int iconNo, int ir, int ig, int ib) {
        byte [] arr1 = {
                (byte) 33,
                (byte) x,
                (byte) y,
                (byte) width,
                (byte) height,
                (byte) buttonState.ordinal(),
                (byte) text.length()
        };
        byte[] arr2 = {
                (byte) ix,
                (byte) iy,
                (byte) iconNo,
                (byte) ir, (byte) ig, (byte) ib
        };
        byte[] array = concatenateTwoArrays(
                concatenateTwoArrays(
                        arr1, text.getBytes(StandardCharsets.UTF_8)),
                arr2);
        requestGenerator.addByteArrayToRequest(array);
    }


    public void drawComboBox(int x, int y, int width, int height, ButtonState buttonState, String text) {
        byte [] arr1 = {
                (byte) 34,
                (byte) x,
                (byte) y,
                (byte) width,
                (byte) height,
                (byte) buttonState.ordinal(),
                (byte) text.length()
        };
        byte [] array = concatenateTwoArrays(arr1, text.getBytes(StandardCharsets.UTF_8));
        requestGenerator.addByteArrayToRequest(array);
    }

    public void drawCheckBox(int x, int y, int width, int height, boolean isChecked) {
        byte[] arr = {
                (byte) 35,
                (byte) x,
                (byte) y,
                (byte) width,
                (byte) height,
                (byte) (isChecked ? 1 : 0)
        };

        requestGenerator.addByteArrayToRequest(arr);
    }

    public void drawSlider(int x, int y, int width, int height, double fillPercentage, boolean vertical) {
        byte[] arr = {
                (byte) 37,
                (byte) x,
                (byte) y,
                (byte) width,
                (byte) height,
                (byte) (fillPercentage * 255.0),
                (byte) (vertical ? 1 : 0)
        };

        requestGenerator.addByteArrayToRequest(arr);
    }

    public void drawProgress (int x, int y, int width, int height, double progress) {
        byte[] arr = {
                (byte) 38,
                (byte) x,
                (byte) y,
                (byte) width,
                (byte) height,
                (byte) (progress * 255.0)
        };

        requestGenerator.addByteArrayToRequest(arr);
    }

    // TODO: FIX
    public void drawEditBox(int x, int y, int width, int height, EditBoxState editBoxState, String text, int isSelected ) {
        byte [] arr1 = {
                (byte) 39,
                (byte) x,
                (byte) y,
                (byte) width,
                (byte) height,
                (byte) editBoxState.ordinal(),
                (byte) text.length()
        };
        byte [] arr2 = {
                (byte) isSelected
        };

        byte [] array = concatenateTwoArrays(concatenateTwoArrays(arr1, text.getBytes(StandardCharsets.UTF_8)),
                arr2);

        requestGenerator.addByteArrayToRequest(array);
    }

    public void drawPager(int x, int y, int width, int height, int noOfItems, int selectedIndex, boolean vertical) {
        byte[] arr = {
                (byte) 43,
                (byte) x,
                (byte) y,
                (byte) width,
                (byte) height,
                (byte) noOfItems,
                (byte) selectedIndex,
                (byte) (vertical ? 0 : 1)
        };
        requestGenerator.addByteArrayToRequest(arr);
    }

    byte[] concatenateTwoArrays(byte[] array1, byte[] array2) {
        byte[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        array2 = Arrays.copyOf(result, result.length);

        return array2;
    }
}
