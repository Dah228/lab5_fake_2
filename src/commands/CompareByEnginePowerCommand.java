package commands;

import collection.VehicleManager;
import mainpart.ReturnCode;

import java.util.List;
import java.util.Scanner;

public class CompareByEnginePowerCommand implements Command{
    private final VehicleManager vehicleManager;

    public CompareByEnginePowerCommand(VehicleManager vehicleCollection) {
        this.vehicleManager = vehicleCollection;
    }

    @Override
    public ReturnCode execute(List<String> parameter, Scanner scanner, Boolean isLaud) {
        if (parameter.size() != 2) {
            return ReturnCode.FAILED;
        }
        try {
            Float number = Float.parseFloat(String.valueOf(parameter.get(1)));
            vehicleManager.filterByEnginePower(number);
            return ReturnCode.OK;
            } catch (IllegalArgumentException e) {
                if(isLaud) System.out.println("Ошибка: неверный тип! Введите число");
                return ReturnCode.FAILED;
            }
    }


    @Override
    public String getDescription() {
        return "вывести элементы, значение поля enginePower которых больше заданного";
    }
}
