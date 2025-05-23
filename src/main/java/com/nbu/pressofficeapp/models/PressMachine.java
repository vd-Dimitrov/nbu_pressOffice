package com.nbu.pressofficeapp.models;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PressMachine {
    private final long id;
    private final int paperCapacity;
    private final int pagesPerMinute;
    private final boolean printsColored;
    private Map<String, Long> releasesPrinted;
    private static long idCounter;

    public PressMachine(int paperCapacity, int pagesPerMinute, boolean printsColored) {
        this.paperCapacity = paperCapacity;
        this.pagesPerMinute = pagesPerMinute;
        this.printsColored = printsColored;
        this.releasesPrinted = new HashMap<>();
        id = idCounter;
        idCounter++;
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

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PressMachine{" +
                "id=" + id +
                ", paperCapacity=" + paperCapacity +
                ", pagesPerMinute=" + pagesPerMinute +
                ", printsColored=" + printsColored +
                '}';
    }
}
