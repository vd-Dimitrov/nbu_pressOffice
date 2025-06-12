package com.nbu.pressofficeapp;

import com.nbu.pressofficeapp.core.PressOfficeEngineImpl;
import com.nbu.pressofficeapp.core.contracts.Engine;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Engine engine = new PressOfficeEngineImpl();
        System.out.println("Welcome to the Press Office program. If you're not sure where to start, just type SHOWCOMMANDDESCRIPTION for a list of all commands and type Exit to quit the program");
        engine.start();
    }
}