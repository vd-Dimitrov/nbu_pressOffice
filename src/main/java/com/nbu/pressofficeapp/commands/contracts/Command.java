package com.nbu.pressofficeapp.commands.contracts;

import java.util.List;

public interface Command {
    String execute(List<String> parameters);
}
