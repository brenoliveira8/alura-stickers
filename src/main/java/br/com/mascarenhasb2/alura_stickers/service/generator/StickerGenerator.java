package br.com.mascarenhasb2.alura_stickers.service.generator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class StickerGenerator {
    BufferedImage originalImage;
    BufferedImage newImage;
    Graphics2D graphics;

    public void createSticker(InputStream imageUrl, String stickerName) throws IOException {
        originalImage = ImageIO.read(imageUrl);
        createNewImage(200);
        configureGraphics();
        drawTextOnSticker("TOP-CONTENT");
        new File("images/").mkdir();
        createStickerFile("png", new File("images/" + stickerName));
    }

    private void createStickerFile(String format, File stickerFile) throws IOException {
        ImageIO.write(
                newImage,
                format,
                stickerFile);
    }

    private void drawTextOnSticker(String text) {
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        int x = (newImage.getWidth() - textWidth) / 2;
        int y = originalImage.getHeight() + ((newImage.getHeight() - originalImage.getHeight()) / 2);
        graphics.drawString(text, x, y);
    }

    private void configureGraphics() {
        graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);
        graphics.setFont(new Font("Impact", Font.PLAIN, 100));
        graphics.setColor(Color.RED);
    }

    private void createNewImage(int plusHeight) {
        newImage = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight() + plusHeight,
                BufferedImage.TRANSLUCENT
        );
    }
}
