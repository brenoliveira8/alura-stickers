package br.com.mascarenhasb2.alura_stickers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class StickerGenerator {

    public void create(InputStream path, String name) throws IOException {
        BufferedImage imagemOriginal = ImageIO.read(path);
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);
        graphics.setFont(new Font("Impact", Font.PLAIN, 100));
        graphics.setColor(Color.RED);
        String text = "TOP-ZERA";

        //Ajustando posicionamento do texto
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        int x = (largura - textWidth) / 2;
        int y = altura + ((novaAltura - altura) / 2);

        //Desenhando o texto
        graphics.drawString(text, x, y);
        ImageIO.write(novaImagem, "png", new File("imagens/" + name));
    }
}
