package br.com.mascarenhasb2.alura_stickers.service.extractor;

import com.fasterxml.jackson.databind.JsonNode;

public class NasaExtractor implements Extractor{

    @Override
    public JsonNode contentsLayerInsideJson(JsonNode node) {
        return node;
    }
    @Override
    public JsonNode imageUrlLayerInsideContentJson(JsonNode urlNode) {
        return urlNode;
    }
}
