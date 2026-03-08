package commands;

import collection.VehicleAdder;
import mainpart.ReturnCode;

import java.util.List;
import java.util.Scanner;
//import collection.VehicleComaperator;

public class AddIfMax implements Command{
    VehicleAdder vehicleAdder;
    public AddIfMax(VehicleAdder vehicleComaperator){
        this.vehicleAdder = vehicleComaperator;
    }


    @Override
    public ReturnCode execute(List<String> args, Scanner scanner, Boolean isLaud){
        if (args.size() != 1){
            return ReturnCode.FAILED;
        }

        try {
            vehicleAdder.addIfMax(scanner, isLaud);
            return ReturnCode.OK;
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: неверный тип! Введите число");
            return ReturnCode.FAILED;
        }
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }


}

