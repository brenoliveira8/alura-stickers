package br.com.mascarenhasb2.alura_stickers.model;

import br.com.mascarenhasb2.alura_stickers.service.extractor.Extractor;
import br.com.mascarenhasb2.alura_stickers.service.extractor.ImdbExtractor;
import br.com.mascarenhasb2.alura_stickers.service.extractor.MarvelExtractor;
import br.com.mascarenhasb2.alura_stickers.service.extractor.NasaExtractor;
import io.github.cdimascio.dotenv.Dotenv;

import java.net.MalformedURLException;
import java.net.URL;

public enum API {

    IMDB_TOP_250_MOVIES("https://imdb-api.com/en/API/Top250Movies/" + Dotenv.load().get("IMDB_API_KEY"), new ImdbExtractor(), "title", "image"),
    IMDB_MOST_POPULAR_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json", new ImdbExtractor(), "title", "image"),
    MARVEL_SERIES("https://gateway.marvel.com/v1/public/series?titleStartsWith=x-men&ts=1&apikey=" + Dotenv.load().get("MARVEL_API_KEY") + "&hash=" + Dotenv.load().get("MARVEL_MD5_HASH"), new MarvelExtractor(), "title", "path"),
    NASA_APOD("https://api.nasa.gov/planetary/apod?api_key=" + Dotenv.load().get("NASA_API_KEY") + "&start_date=2022-05-30&end_date=2022-06-01", new NasaExtractor(), "title", "url");

    private URL url;
    private Extractor extractor;
    String title;
    String imagePath;

    API(String url, Extractor extractor, String title, String imagePath) {
        try {
            this.url = new URL(url);
            this.extractor = extractor;
            this.title = title;
            this.imagePath = imagePath;
        } catch (MalformedURLException ex){
            ex.printStackTrace();
        }
    }

    public Extractor getExtractor() {
        return extractor;
    }
    public URL getUrl() {
        return this.url;
    }

    public String getTitle() {
        return title;
    }

    public String getImagePath() {
        return imagePath;
    }
}
