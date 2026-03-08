package commands;

import collection.VehicleAdder;
import mainpart.ReturnCode;

import java.util.List;
import java.util.Scanner;

public class UpdateElementID implements Command {
    VehicleAdder vehicleManager;

    public UpdateElementID(VehicleAdder vehicleManager) {
        this.vehicleManager = vehicleManager;
    }

    @Override
    public ReturnCode execute(List<String> args, Scanner scanner, Boolean isLaud) {
        if (args.size() != 2){
            return ReturnCode.FAILED;
        }
        try {
            long identifier = Long.parseLong(args.get(1));
            vehicleManager.updateElementByID(identifier, scanner,isLaud);
            return ReturnCode.OK;
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: неверный тип! Введите число");
            return ReturnCode.FAILED;
        }
    }

    public String getDescription(){
        return " обновить значение элемента коллекции, id которого равен заданному";
    }

}
