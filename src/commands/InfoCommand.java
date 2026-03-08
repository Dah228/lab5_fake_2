package commands;

import collection.VehicleManager;
import mainpart.ReturnCode;

import java.util.List;
import java.util.Scanner;

public class InfoCommand implements Command{
    private final VehicleManager vehicleCollection;

    public InfoCommand(VehicleManager vehicleCollection) {
        this.vehicleCollection = vehicleCollection;
    }

    @Override
    public ReturnCode execute(List<String> args, Scanner scanner, Boolean isLaud) {
        if (args.size() != 1) return ReturnCode.FAILED;
        else {
            vehicleCollection.getInfo();
            return ReturnCode.OK;
        }
    }

    @Override
    public String getDescription() {
        return " вывести в стандартный поток информацию о коллекции";
    }

}
