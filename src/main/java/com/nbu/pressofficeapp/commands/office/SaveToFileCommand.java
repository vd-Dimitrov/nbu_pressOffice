package com.nbu.pressofficeapp.commands.office;

import com.nbu.pressofficeapp.commands.BaseCommand;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.models.Employee;
import com.nbu.pressofficeapp.models.Paper;
import com.nbu.pressofficeapp.models.PressMachine;
import com.nbu.pressofficeapp.models.PressOffice;
import com.nbu.pressofficeapp.models.enums.PaperType;
import com.nbu.pressofficeapp.utils.ValidationHelpers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class SaveToFileCommand extends BaseCommand {

    public static final String OFFICE_SUCCESSFULLY_SAVED = "Press Office %s successfully saved to file %s";
    public static final String ERROR_IN_SAVING_THE_FILE = "Error in saving the file: %s";

    public SaveToFileCommand(PressOfficeRepository pressOfficeRepository) {
        super(pressOfficeRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, 2);

        PressOffice office = getPressOfficeRepository().findOfficeByName(parameters.get(0));
        String filePath = "src/main/java/com/nbu/pressofficeapp/data/" + parameters.get(1) + ".txt";
        return saveToTextFile(office, filePath);
    }

    public String saveToTextFile(PressOffice office, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // PRESS_OFFICE
            writer.write("# PRESS_OFFICE\n");
            writer.write("name=" + office.getName() + "\n");
            writer.write("priceIncreasePercent=" + office.getPriceIncreasePercent() + "\n");
            writer.write("managerBonusThreshold=" + office.getManagerBonusThreshold() + "\n");
            writer.write("paperDiscountAmount=" + office.getPaperDiscountAmount() + "\n");
            writer.write("paperDiscountPercent=" + office.getPaperDiscountPercent() + "\n\n");

            // BASE_PAPER_PRICE
            writer.write("# BASE_PAPER_PRICE\n");
            for (Map.Entry<PaperType, BigDecimal> entry : office.getBasePaperPrice().entrySet()) {
                writer.write(entry.getKey().name() + "=" + entry.getValue() + "\n");
            }
            writer.write("\n");

            // EMPLOYEES
            writer.write("# EMPLOYEES\n");
            for (Employee emp : office.getEmployeeList()) {
                writer.write(emp.getName() + ";" + emp.getSalary() + ";" + emp.isManager() + "\n");
            }
            writer.write("\n");

            // PAPERS
            writer.write("# PAPERS\n");
            for (Map.Entry<Paper, Long> entry : office.getPaperAmount().entrySet()) {
                Paper paper = entry.getKey();
                writer.write(paper.getPaperType().name() + ";" + paper.getSize().name() + ";" + entry.getValue() + "\n");
            }
            writer.write("\n");

            // MACHINES
            writer.write("# MACHINES\n");
            for (PressMachine machine : office.getPressMachines()) {
                Paper paper = machine.getSupportedPaper();
                writer.write(machine.getPaperCapacity() + ";" +
                        machine.getPagesPerMinute() + ";" +
                        paper.getPaperType().name() + ";" +
                        paper.getSize().name() + ";" +
                        machine.isPrintsColored() + "\n");
            }
            return String.format(OFFICE_SUCCESSFULLY_SAVED, office.getName(), filename);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format(ERROR_IN_SAVING_THE_FILE, e.getMessage()));
        }
    }
}
