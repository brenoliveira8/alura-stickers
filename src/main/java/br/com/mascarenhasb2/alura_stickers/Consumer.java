package br.com.mascarenhasb2.alura_stickers;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.IntStream;

public class Consumer {
    public static void main(String[] args) throws IOException, InterruptedException {
//        var dotenv = Dotenv.load();
//        String imdbApiKey = dotenv.get("IMDB_API_KEY");
//        var apiImdbUri = URI.create("https://imdb-api.com/en/API/Top250Movies/" + imdbApiKey);

        var apiImdbUrl = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json");
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNodes = mapper.readTree(apiImdbUrl);
        JsonNode movies = jsonNodes.get("items");

        StickerGenerator stickerGenerator = new StickerGenerator();

        for (JsonNode movieNode : movies) {
            String titulo = movieNode.get("title").asText();
            String url = movieNode.get("image").asText();
            String rating = movieNode.get("imDbRating").asText();

            System.out.println("Título: " + titulo);
            System.out.println("Poster: " + url);
            System.out.println(Ansi.colorize("Classificação: " + rating, Attribute.BLACK_TEXT(), Attribute.MAGENTA_BACK(), Attribute.BOLD()));

            BigDecimal imDbRating = BigDecimal.valueOf(movieNode.get("imDbRating").asDouble());
            int imDbRatingRounded = imDbRating.setScale(0, RoundingMode.HALF_UP).intValue();

            IntStream.range(0, imDbRatingRounded)
                    .mapToObj(star -> "\u2B50")
                    .forEach(System.out::print);

            System.out.println(System.lineSeparator());

            stickerGenerator.create(new URL(movieNode.get("image").asText()).openStream(), titulo.replaceAll(":", " - ") + ".png");
        }
    }
}
