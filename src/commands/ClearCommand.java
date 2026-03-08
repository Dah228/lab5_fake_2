package commands;

import collection.VehicleManager;
import mainpart.ReturnCode;

import java.util.List;
import java.util.Scanner;

public class ClearCommand implements Command {
    private final VehicleManager manager;

    public ClearCommand(VehicleManager manager) {
        this.manager = manager;
    }

    @Override
    public ReturnCode execute(List<String> param, Scanner scanner, Boolean isLaud) {
        if (param.size() != 1) return ReturnCode.FAILED;
        manager.clearCollection();
        if (isLaud) System.out.println("Коллекция очищена");
        return ReturnCode.OK;
        }



    public String getDescription() {
        return "очистить коллекцию";
    }
}
