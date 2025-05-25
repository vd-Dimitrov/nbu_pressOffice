package com.nbu.pressofficeapp.models.enums;

public enum PaperSize {
    A5(0),
    A4(1),
    A3(2),
    A2(3),
    A1(4);

    public final double sizeMod;

    private PaperSize(double sizeMod){
        this.sizeMod = sizeMod;
    }
}
