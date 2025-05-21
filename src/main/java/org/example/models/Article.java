package org.example.models;

import org.example.models.enums.PaperType;
import org.example.models.enums.Size;

public class Article {
    private String articleType;
    private long amount;
    private Size articleSize;
    private PaperType paperType;

    public Article(String articleType, long amount, Size articleSize, PaperType paperType) {
        this.articleType = articleType;
        this.amount = amount;
        this.articleSize = articleSize;
        this.paperType = paperType;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Size getArticleSize() {
        return articleSize;
    }

    public void setArticleSize(Size articleSize) {
        this.articleSize = articleSize;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(PaperType paperType) {
        this.paperType = paperType;
    }
}
