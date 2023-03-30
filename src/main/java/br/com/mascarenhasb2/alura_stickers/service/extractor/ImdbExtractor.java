package br.com.mascarenhasb2.alura_stickers.service.extractor;

import com.fasterxml.jackson.databind.JsonNode;

public class ImdbExtractor implements Extractor {

    @Override
    public JsonNode contentsLayerInsideJson(JsonNode node) {
        return node.get("items");
    }
    @Override
    public JsonNode imageUrlLayerInsideContentJson(JsonNode urlNode) {
        return urlNode;
    }
}
