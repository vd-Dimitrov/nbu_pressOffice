package com.nbu.pressofficeapp.models;

import com.nbu.pressofficeapp.models.enums.PaperType;
import com.nbu.pressofficeapp.models.enums.PaperSize;

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


}
