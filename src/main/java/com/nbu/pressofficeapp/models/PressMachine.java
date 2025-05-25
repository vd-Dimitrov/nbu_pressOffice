package com.nbu.pressofficeapp.models;

import com.nbu.pressofficeapp.models.enums.PaperType;
import com.nbu.pressofficeapp.models.enums.PaperSize;

import java.util.HashMap;
import java.util.Map;

public class PressMachine {
    private final long id;
    private final int paperCapacity;
    private final int pagesPerMinute;
    private final Paper supportedPaper;
    private final boolean printsColored;
    private int currentPaperCount;
    private PressOffice currentLocation;
    private Map<String, Long> releasesPrinted;
    private static long idCounter;

    public PressMachine(int paperCapacity, int pagesPerMinute, Paper supportedPaper, boolean printsColored, PressOffice currentLocation) {
        this.paperCapacity = paperCapacity;
        this.pagesPerMinute = pagesPerMinute;
        this.supportedPaper = supportedPaper;
        this.printsColored = printsColored;
        this.currentLocation = currentLocation;
        this.releasesPrinted = new HashMap<>();
        currentPaperCount = 0;
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


    public PressOffice getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(PressOffice currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Paper getSupportedPaper() {
        return supportedPaper;
    }

    public int getCurrentPaperCount() {
        return currentPaperCount;
    }

    public void setCurrentPaperCount(int currentPaperCount) {
        this.currentPaperCount = currentPaperCount;
    }

    public void loadPaper(int loadedPaper){
        if (loadedPaper + currentPaperCount > paperCapacity){
            currentPaperCount = paperCapacity;
        }
        else {
            currentPaperCount += loadedPaper;
        }
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
