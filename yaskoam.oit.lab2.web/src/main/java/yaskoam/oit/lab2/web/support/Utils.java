package yaskoam.oit.lab2.web.support;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.image.resource.BufferedDynamicImageResource;
import org.apache.wicket.request.resource.IResource;

public class Utils {

    public static Image createImage(String id, byte[] photo) {
        Image image = photo != null ? new NonCachingImage(id, Utils.createImageResource(photo)) : new Image(id, "");
        image.setVisible(photo != null);
        return image;
    }

    public static IResource createImageResource(byte[] imageBytes) {
        BufferedImage bufferedImage;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
            bufferedImage = ImageIO.read(inputStream);
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }

        BufferedDynamicImageResource imageResource = new BufferedDynamicImageResource();
        imageResource.setImage(bufferedImage);

        return imageResource;
    }
}