package com.nbu.pressofficeapp.commands.enums;

import java.util.stream.Stream;

public enum CommandType {
    CREATEOFFICE ("CREATEOFFICE: Used to create an office. Requires the following parameters: name(single word), " +
            "price for normal paper, glossy paper and newspaper paper, the price increase per paper size, " +
            "the minimum income required for manager bonus, how many papers before receiving a discount" +
            " and how much that discount is"),
    SHOWOFFICES("SHOWOFFICES: Shows all offices saved in repository"),
    STOCKUPPAPERS("STOCKUPPAPERS: Stocks up paper for an office. Requires the following parameters: " +
            "name of office, paper type, paper size and amount"),
    SHOWOFFICEFINANCE("SHOWOFFICEFINANCE: Shows the income and expenses for an office. Requires office name"),

    CREATEPERSON("CREATEPERSON: Used to add a person to the repository. Requires their three names and salary"),
    SHOWPERSON("SHOWPERSON: Will be used to show information about a single employee. Requires their name\n"),
    ADDEMPLOYEETOOFFICE("ADDEMPLOYEETOOFFICE: Assigns an employee to an office. Requires employee id and office name"),
    FIREEMPLOYEE ("FIREEMPLOYEE: Removes employee from all offices and sets them as fired. Requires employee name"),
    CREATEPRESSMACHINE ("CREATEPRESSMACHINE: Creates a press machine. Requires the following parameters: " +
            "paper capacity, the amount of pages per minute it prints, supported paper type and size," +
            " whether it supports colored printing and the office it is created for"),
    LOADPRESSMACHINE("LOADPRESSMACHINE: Adds paper to a pressMachine. Requires the id of the press and the amount of paper it will be loaded"),
    SHOWCOMMANDDESCRIPTION ("SHOWCOMMANDDESCRIPTION: Shows description of each command.");

    public final String commandDescription;

    private CommandType(String commandDescription){
        this.commandDescription = commandDescription;
    }

    public static Stream<CommandType> stream(){
        return Stream.of(CommandType.values());
    }
}

