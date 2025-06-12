package com.nbu.pressofficeapp.models;

import com.nbu.pressofficeapp.models.enums.PaperType;
import com.nbu.pressofficeapp.models.enums.PaperSize;

import java.util.Objects;

public class Paper {
    final PaperType paperType;
    final PaperSize paperSize;

    public Paper(PaperType paperType, PaperSize paperSize) {
        this.paperType = paperType;
        this.paperSize = paperSize;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public PaperSize getSize() {
        return paperSize;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Paper o)) return false;
        return paperType == o.paperType && paperSize == o.paperSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(paperType, paperSize);
    }

    @Override
    public String toString() {
        return paperType + "-" + paperSize;
    }
}
