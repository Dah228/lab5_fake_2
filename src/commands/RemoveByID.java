package commands;

import collection.VehicleAdder;
import mainpart.ReturnCode;

import java.util.List;
import java.util.Scanner;

public class RemoveByID implements Command{
    VehicleAdder vehicleManager;
    public RemoveByID(VehicleAdder vehicleManager){
        this.vehicleManager = vehicleManager;
    }

    @Override
    public ReturnCode execute(List<String> args, Scanner scanner, Boolean isLaud) {
        if(args.size() != 2) return ReturnCode.FAILED;
        try {
            long number = Long.parseLong(args.get(1));
            vehicleManager.rmByID(number, isLaud);
            if(isLaud) System.out.println("Успешно удален");
            return ReturnCode.OK;
        } catch (IllegalArgumentException e) {
            if(isLaud) System.out.println("Ошибка: неверный тип! Введите число");
            return ReturnCode.FAILED;
        }
    }


    @Override
    public String getDescription(){
        return " удалить элемент из коллекции по его id";
    }
}
