package br.com.mascarenhasb2.alura_stickers;

import java.math.BigDecimal;

public class Movie {
//
    private String title;
    private BigDecimal imDbRating;
    private String image;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public BigDecimal getImDbRating() {
        return imDbRating;
    }

    public void setImDbRating(BigDecimal imDbRating) {
        this.imDbRating = imDbRating;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
