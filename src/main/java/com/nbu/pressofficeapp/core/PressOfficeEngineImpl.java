package com.nbu.pressofficeapp.core;

import com.nbu.pressofficeapp.commands.contracts.Command;
import com.nbu.pressofficeapp.core.contracts.CommandFactory;
import com.nbu.pressofficeapp.core.contracts.Engine;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PressOfficeEngineImpl implements Engine {
    private static final String EMPTY_COMMAND = "Command cannot be empty.";
    private static final String TERMINATION_COMMAND = "Exit";
    public static final String MAIN_SPLIT_SYMBOL = " ";


    private final PressOfficeRepositoryImpl pressOfficeRepository;
    private final CommandFactory commandFactory;

    public PressOfficeEngineImpl() {
        this.pressOfficeRepository = new PressOfficeRepositoryImpl();
        this.commandFactory = new CommandFactoryImpl();
    }


    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            try {
                String inputLine = scanner.nextLine();
                if (inputLine.isEmpty()){
                    System.out.println(EMPTY_COMMAND);
                    continue;
                }
                if (inputLine.equalsIgnoreCase(TERMINATION_COMMAND)){
                    break;
                }
                processCommand(inputLine);
            } catch (Exception ex){
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()){
                    System.out.println(ex.getMessage());
                } else {
                    System.out.println(ex.toString());
                }
            }
        }

    }
    private void processCommand(String inputLine){
        String commandName = extractCommandName(inputLine);
        Command command = commandFactory.createCommandFromCommandName(commandName, pressOfficeRepository);
        List<String> parameters = extractCommandParameters(inputLine);
        String executionResult = command.execute(parameters);
        System.out.println(executionResult);
    }


    private String extractCommandName(String inputLine) {
        return inputLine.split(MAIN_SPLIT_SYMBOL)[0];
    }
    private List<String> extractCommandParameters(String inputLine) {
        String[] commandParts = inputLine.split(MAIN_SPLIT_SYMBOL);
        return Arrays.asList(commandParts).subList(1, commandParts.length);
    }


}
