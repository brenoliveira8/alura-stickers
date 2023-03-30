package br.com.mascarenhasb2.alura_stickers.service.extractor;

import br.com.mascarenhasb2.alura_stickers.model.Content;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public interface Extractor {

    JsonNode contentsLayerInsideJson(JsonNode node);

    JsonNode imageUrlLayerInsideContentJson(JsonNode urlNode);

    default List<Content> extractContentListFromJsonUrl(URL jsonUrl, String title, String imageUrl) {
        List<Content> contentList = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode nodesList = mapper.readTree(jsonUrl);
            nodesList = contentsLayerInsideJson(nodesList);

            for (JsonNode node : nodesList) {
                var content = new Content(
                        node.get(title).asText(),
                        new URL(imageUrlLayerInsideContentJson(node).get(imageUrl).asText())
                );
                contentList.add(content);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return contentList;
    }
}
