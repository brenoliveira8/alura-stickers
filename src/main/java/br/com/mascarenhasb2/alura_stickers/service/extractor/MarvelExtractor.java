package br.com.mascarenhasb2.alura_stickers.service.extractor;

import com.fasterxml.jackson.databind.JsonNode;

public class MarvelExtractor implements Extractor {
    @Override
    public JsonNode contentsLayerInsideJson(JsonNode node) {
        return node.get("data").get("results");
    }

    @Override
    public JsonNode imageUrlLayerInsideContentJson(JsonNode urlNode) {
        return urlNode.get("thumbnail");
    }
}
