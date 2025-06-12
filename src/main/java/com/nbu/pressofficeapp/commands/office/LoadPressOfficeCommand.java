package com.nbu.pressofficeapp.commands.office;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.models.Employee;
import com.nbu.pressofficeapp.models.Paper;
import com.nbu.pressofficeapp.models.PressMachine;
import com.nbu.pressofficeapp.models.PressOffice;
import com.nbu.pressofficeapp.models.enums.PaperSize;
import com.nbu.pressofficeapp.models.enums.PaperType;
import com.nbu.pressofficeapp.utils.ValidationHelpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadPressOfficeCommand extends BaseCommand {

    public static final String FILE_READING_ERROR = "Error during file reading: %s";
    public static final String OFFICE_SUCCESSFULLY_CREATED = "Office successfully created: %s";

    public LoadPressOfficeCommand(PressOfficeRepository pressOfficeRepository) {
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, 1);
        String fileName = "src/main/java/com/nbu/pressofficeapp/data/" + parameters.get(0);

        return loadPrintHouseFromText(fileName);
    }

    public String loadPrintHouseFromText(String filename) {

        String name = "";
        double priceIncreasePercent = 0;
        BigDecimal managerBonusThreshold = BigDecimal.ZERO;
        int paperDiscountAmount = 0;
        double paperDiscountPercent = 0;

        Map<PaperType, BigDecimal> basePaperPrice = new HashMap<>();
        List<Employee> employeeList = new ArrayList<>();
        List<Paper> paperList = new ArrayList<>();
        List<PressMachine> pressMachines = new ArrayList<>();

        String currentSection = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("#")) {
                    currentSection = line;
                    continue;
                }

                switch (currentSection) {
                    case "# PRESS_OFFICE" -> {
                        String[] parts = line.split("=");
                        switch (parts[0]) {
                            case "name" -> name = parts[1];
                            case "priceIncreasePercent" -> priceIncreasePercent = Double.parseDouble(parts[1]);
                            case "managerBonusThreshold" -> managerBonusThreshold = new BigDecimal(parts[1]);
                            case "paperDiscountAmount" -> paperDiscountAmount = Integer.parseInt(parts[1]);
                            case "paperDiscountPercent" -> paperDiscountPercent = Double.parseDouble(parts[1]);
                        }
                    }

                    case "# BASE_PAPER_PRICE" -> {
                        String[] parts = line.split("=");
                        PaperType type = PaperType.valueOf(parts[0]);
                        BigDecimal price = new BigDecimal(parts[1]);
                        basePaperPrice.put(type, price);
                    }

                    case "# EMPLOYEES" -> {
                        String[] parts = line.split(";");
                        String empName = parts[0];
                        BigDecimal salary = new BigDecimal(parts[1]);
                        boolean isManager = Boolean.parseBoolean(parts[2]);

                        Employee e = new Employee(empName, salary);
                        e.setManager(isManager);
                        employeeList.add(e);
                    }

                    case "# PAPERS" -> {
                        String[] parts = line.split(";");
                        PaperType type = PaperType.valueOf(parts[0]);
                        PaperSize size = PaperSize.valueOf(parts[1]);
                        paperList.add(new Paper(type, size));
                    }

                    case "# MACHINES" -> {
                        String[] parts = line.split(";");
                        int capacity = Integer.parseInt(parts[0]);
                        int ppm = Integer.parseInt(parts[1]);
                        PaperType type = PaperType.valueOf(parts[2]);
                        PaperSize size = PaperSize.valueOf(parts[3]);
                        boolean color = Boolean.parseBoolean(parts[4]);

                        Paper paper = new Paper(type, size);
                        PressMachine machine = new PressMachine(capacity, ppm, paper, color, null); // ще сложим office по-долу
                        pressMachines.add(machine);
                    }
                }
            }
            PressOffice office = new PressOffice(name, basePaperPrice, priceIncreasePercent,
                    managerBonusThreshold, paperDiscountAmount, paperDiscountPercent);

            for (Employee e : employeeList) {
                e.setAssignedOffice(office);
                office.getEmployeeList().add(e);
            }

            for (Paper paper : paperList) {
                office.getPaperAmount().put(paper, 0L);
            }

            for (PressMachine machine : pressMachines) {
                machine.setCurrentLocation(office);
                office.getPressMachines().add(machine);
            }

            this.getPressOfficeRepository().addOffice(office);

            return String.format(OFFICE_SUCCESSFULLY_CREATED, office.toString());
        } catch (IOException e) {
           throw new IllegalArgumentException(String.format(FILE_READING_ERROR, e.getMessage()));
        }
    }
}
