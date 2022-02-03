package stratumkit.app;

import java.io.*;
import java.net.Socket;

public abstract class App {
    private String appServerHostname = "localhost";
    private int appServerPort = 50665;

    private boolean connected = false;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private String appId;

    public void setAppServer(String hostname, int port) {
        if (connected) {
            return;
        }

        appServerHostname = hostname;
        appServerPort = port;
    }

    private void receiveCommand() throws IOException {
        int command = inputStream.readUnsignedByte();

        switch (command) {
            case 0x0F: // Input
                int x = inputStream.readUnsignedByte();
                int y = inputStream.readUnsignedByte();
                int dragging = inputStream.readUnsignedByte();

                onInput(new AppInput(x, y, dragging != 0));
        }
    }

    private void sendBackAppId(String appId) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(byteStream);

        dataStream.writeByte(0);
        dataStream.writeBytes(appId);

        byte[] data = byteStream.toByteArray();

        outputStream.write(byteStream.toByteArray());
    }

    public void sendCommand(int command, byte[] payload) throws IOException {
        if (!connected) {
            return;
        }

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream dataStream = new DataOutputStream(byteStream);

        dataStream.writeByte(command);
        dataStream.writeShort(payload.length);
        dataStream.write(payload);

        byte[] data = byteStream.toByteArray();

        outputStream.write(byteStream.toByteArray());
    }

    public void start(String appId) throws IOException {
        if (connected) {
            return;
        }
        connected = true;

        Socket socket = new Socket(appServerHostname, appServerPort);

        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());

        sendBackAppId(appId);

        if (inputStream.readUnsignedByte() == 1) {
            // We're in!
            launched();
        }

        while (true) {
            receiveCommand();
        }
    }

    abstract protected void launched() throws IOException;
    abstract protected void onInput(AppInput input) throws IOException;
}
