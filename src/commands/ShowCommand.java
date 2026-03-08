package commands;

import collection.VehicleManager;
import mainpart.ReturnCode;

import java.util.List;
import java.util.Scanner;

public class ShowCommand implements Command {
    private final VehicleManager manager;

    public ShowCommand(VehicleManager manager) {
        this.manager = manager;
    }

    @Override
    public ReturnCode execute(List<String> args, Scanner scanner, Boolean isLaud) {
        if (args.size() != 1) return ReturnCode.FAILED;
        else {
            manager.showCollection();
            return ReturnCode.OK;
        }
    }

    @Override
    public String getDescription() {
        return " вывести все элементы";
    }
}