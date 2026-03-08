package commands;

import collection.VehicleRandom;
import mainpart.ReturnCode;

import java.util.List;
import java.util.Scanner;

public class PrintDescendingCommand implements Command{
    private final VehicleRandom vehicleRandom;
    public PrintDescendingCommand(VehicleRandom vehicleRandom){
        this.vehicleRandom = vehicleRandom;
    }

    @Override
    public ReturnCode execute(List<String> args, Scanner scanner, Boolean isLaud){
        if (args.size() != 1) return ReturnCode.FAILED;
        else{
            vehicleRandom.sortByIDDescending();
            return ReturnCode.OK;
        }

    }

    @Override
    public String getDescription(){
        return "вывести элементы коллекции в порядке убывания";
    }
}
