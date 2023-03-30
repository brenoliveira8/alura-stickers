package br.com.mascarenhasb2.alura_stickers;

import br.com.mascarenhasb2.alura_stickers.service.generator.StickerGenerator;
import br.com.mascarenhasb2.alura_stickers.model.API;
import br.com.mascarenhasb2.alura_stickers.model.Content;
import br.com.mascarenhasb2.alura_stickers.service.extractor.Extractor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException {
        List<Content> contentList = createContentList(API.IMDB_MOST_POPULAR_MOVIES);
        generateStickers(contentList);
    }
    private static List<Content> createContentList(API api) {
        var url = api.getUrl();
        Extractor extractor = api.getExtractor();
        String urlImageFieldName = api.getImagePath();
        String titleFieldName = api.getTitle();
        List<Content> contentList = extractor.extractContentListFromJsonUrl(
                url,
                titleFieldName,
                urlImageFieldName
        );
        return contentList;
    }

    private static void generateStickers(List<Content> contentList) throws IOException {
        StickerGenerator stickerGenerator = new StickerGenerator();

        for (Content content : contentList) {
            URL imageUrl = checkIfImageUrlHasEmbeddedFormat(content.imageUrl());
            stickerGenerator.createSticker(
                    imageUrl.openStream(),
                    content.title().replaceAll(":", " - ") + ".png"
            );
        }
    }

    private static URL checkIfImageUrlHasEmbeddedFormat(URL imageUrl) throws MalformedURLException {
        String imageUrlAsString = imageUrl.toString();
        if (imageUrlAsString.endsWith(".jpg") || imageUrlAsString.endsWith(".jpeg") || imageUrlAsString.endsWith(".png")) {
            return imageUrl;
        }
        return new URL(imageUrlAsString + ".jpg");
    }
}