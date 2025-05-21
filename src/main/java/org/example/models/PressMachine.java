package org.example.models;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PressMachine {
    private final int paperCapacity;
    private final int pagesPerMinute;
    private final boolean printsColored;
    private Map<String, Long> releasesPrinted;

    public PressMachine(int paperCapacity, int pagesPerMinute, boolean printsColored) {
        this.paperCapacity = paperCapacity;
        this.pagesPerMinute = pagesPerMinute;
        this.printsColored = printsColored;
        this.releasesPrinted = new HashMap<>();
    }

    public int getPaperCapacity() {
        return paperCapacity;
    }

    public int getPagesPerMinute() {
        return pagesPerMinute;
    }

    public boolean isPrintsColored() {
        return printsColored;
    }

    public Map<String, Long> getReleasesPrinted() {
        return releasesPrinted;
    }

    public void setReleasesPrinted(Map<String, Long> releasesPrinted) {
        this.releasesPrinted = releasesPrinted;
    }

    public long pagesPrinted(){
        return releasesPrinted.values().stream()
                .mapToLong(p -> p)
                .sum();
    }
}
