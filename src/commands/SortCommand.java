package commands;

import collection.VehicleRandom;
import mainpart.ReturnCode;


import java.util.List;
import java.util.Scanner;

public class SortCommand implements Command{
    private final VehicleRandom vehicleRandom;
    public SortCommand(VehicleRandom vehicleRandom){
        this.vehicleRandom = vehicleRandom;
    }

    @Override
    public ReturnCode execute(List<String> args, Scanner scanner, Boolean isLaud) {
        if (args.size() != 1) return ReturnCode.FAILED;
        else {
            vehicleRandom.sortByID();
            return ReturnCode.OK;
        }
    }

    @Override
    public String getDescription(){
        return " отсортировать коллекцию в естественном порядке";
    }


}
