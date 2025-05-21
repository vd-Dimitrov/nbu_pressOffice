package org.example;

import org.example.core.PressOfficeEngineImpl;
import org.example.core.contracts.Engine;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Engine engine = new PressOfficeEngineImpl();
        engine.start();
    }
}