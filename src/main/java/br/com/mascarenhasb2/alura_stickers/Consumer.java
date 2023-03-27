package br.com.mascarenhasb2.alura_stickers;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Consumer {
    public static void main(String[] args) throws IOException, InterruptedException {
//        var dotenv = Dotenv.load();
//        String imdbApiKey = dotenv.get("IMDB_API_KEY");
//        var apiImdbUri = URI.create("https://imdb-api.com/en/API/Top250Movies/" + imdbApiKey);

        var apiImdbUrl = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json");
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNodes = mapper.readTree(apiImdbUrl);
        JsonNode movies = jsonNodes.get("items");

        for (JsonNode movieNode : movies) {
            System.out.println("Título: " + movieNode.get("title").asText());
            System.out.println("Poster: " + movieNode.get("image").asText());
            System.out.println(Ansi.colorize("Classificação: " + movieNode.get("imDbRating").asText(), Attribute.BLACK_TEXT(), Attribute.MAGENTA_BACK(), Attribute.BOLD()));
            BigDecimal imDbRating = BigDecimal.valueOf(movieNode.get("imDbRating").asDouble());
            int imDbRatingRounded = imDbRating.setScale(0, RoundingMode.HALF_UP).intValue();

            IntStream.range(0, imDbRatingRounded).mapToObj(star -> "\u2B50").forEach(System.out::print);
            System.out.println(System.lineSeparator());
        }
    }
}
