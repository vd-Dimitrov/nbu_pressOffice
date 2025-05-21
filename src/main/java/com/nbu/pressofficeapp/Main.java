package com.nbu.pressofficeapp;

import com.nbu.pressofficeapp.core.PressOfficeEngineImpl;
import com.nbu.pressofficeapp.core.contracts.Engine;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Engine engine = new PressOfficeEngineImpl();
        engine.start();
    }
}