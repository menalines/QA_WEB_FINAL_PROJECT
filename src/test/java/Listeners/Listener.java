package Listeners;

//import io.qameta.allure.Attachment;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Listener implements TestWatcher {
    @Attachment
    public byte[] takeScreenShot() throws AWTException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ImageIO.write(
                new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())),
                "png",
                baos
        );

        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        return imageInByte;
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        try {
            takeScreenShot();
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }
}