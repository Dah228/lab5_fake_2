package commands;

import collection.VehicleRandom;
import mainpart.ReturnCode;

import java.util.List;
import java.util.Scanner;

public class ShuffleCommand implements Command{
    private final VehicleRandom vehicleRandom;
    public ShuffleCommand(VehicleRandom vehicleRandom){
        this.vehicleRandom = vehicleRandom;
    }

    @Override
    public ReturnCode execute(List<String> args, Scanner scanner, Boolean isLaud){
        if (args.size() != 1) return ReturnCode.FAILED;
        else{
            vehicleRandom.shuffle();
            return ReturnCode.OK;
        }
    }

    @Override
    public String getDescription(){
        return " перемешать элементы коллекции в случайном порядке";
    }
}
