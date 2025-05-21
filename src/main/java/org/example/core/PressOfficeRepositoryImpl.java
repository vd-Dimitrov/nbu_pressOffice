package org.example.core;

import org.example.core.contracts.PressOfficeRepository;
import org.example.models.PressOffice;

import java.util.ArrayList;
import java.util.List;

public class PressOfficeRepositoryImpl implements PressOfficeRepository {
    private final List<PressOffice> pressOffices;

    public PressOfficeRepositoryImpl() {
        this.pressOffices = new ArrayList<>();
    }

}
