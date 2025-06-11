package com.nbu.pressofficeapp.commands.office;


import com.nbu.pressofficeapp.core.PressOfficeRepositoryImpl;
import com.nbu.pressofficeapp.core.contracts.PressOfficeRepository;
import com.nbu.pressofficeapp.exceptions.InvalidValueException;
import com.nbu.pressofficeapp.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import static com.nbu.pressofficeapp.utils.TestUtils.*;


public class CreateOfficeCommandTests {
    PressOfficeRepository pressOfficeRepository;
    CreateOfficeCommand createOfficeCommand;
    TestUtils testUtils;


    @BeforeEach
    public void setup(){
        pressOfficeRepository = new PressOfficeRepositoryImpl();
        createOfficeCommand = new CreateOfficeCommand(pressOfficeRepository);
    }

    @Test
    public void should_ThrowException_When_InvalidParameterCount(){
        List<String> parameters = Collections.emptyList();
        Assertions.assertThrows(InvalidValueException.class, () -> {createOfficeCommand.execute(parameters);});
    }

    @Test
    public void should_ThrowException_When_OfficeAlreadyExists(){
        String existingOfficeName = VALID_OFFICE_NAME;
        pressOfficeRepository.createOffice(VALID_OFFICE_NAME, VALID_BASE_PAPER_PRICES, VALID_PRICE_INCREASE, VALID_MANAGER_BONUS_THRESHOLD, VALID_PAPER_DISCOUNT_AMOUNT, VALID_PAPER_DISCOUNT_PERCENT );

        Assertions.assertThrows(IllegalArgumentException.class, () -> createOfficeCommand.throwsIfOfficeExists(existingOfficeName));
    }

    @Test
    public void should_CreateTeam_When_ArgumentsAreValid(){
        List<String> parameters = TestUtils.VALID_OFFICE_CREATION_PARAMETERS;

        Assertions.assertDoesNotThrow(() -> {createOfficeCommand.execute(parameters);});

    }
}
