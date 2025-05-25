package com.nbu.pressofficeapp.models;

import com.nbu.pressofficeapp.models.enums.PaperType;
import com.nbu.pressofficeapp.models.enums.PaperSize;

public class Article {
    private String articleType;
    private long amount;
    private PaperSize articlePaperSize;
    private PaperType paperType;

    public Article(String articleType, long amount, PaperSize articlePaperSize, PaperType paperType) {
        this.articleType = articleType;
        this.amount = amount;
        this.articlePaperSize = articlePaperSize;
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

    public PaperSize getArticleSize() {
        return articlePaperSize;
    }

    public void setArticleSize(PaperSize articlePaperSize) {
        this.articlePaperSize = articlePaperSize;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(PaperType paperType) {
        this.paperType = paperType;
    }
}
