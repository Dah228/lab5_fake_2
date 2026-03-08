package commands;

import collection.VehicleAdder;
import mainpart.ReturnCode;

import java.util.List;
import java.util.Scanner;

public class AddCommand implements Command {
    private final VehicleAdder vehicle;

    public AddCommand(VehicleAdder vehicle) {
        this.vehicle = vehicle;
    }


    @Override
    public ReturnCode execute(List<String> args, Scanner scanner, Boolean isLaud) {
        if (args.size() != 1) {
            if (isLaud) System.out.println("Add required 1 args, got = " + args.size());
            return ReturnCode.FAILED;
        }
        try {
            vehicle.addElement(scanner,isLaud);
            return ReturnCode.OK;
        } catch (IllegalArgumentException e){
            System.out.println("произошла ошибка, в параметры команды add были введены не валидные данные");
            return ReturnCode.FAILED;
        }
    }

    @Override
    public String getDescription() {
        return "добавить элемент в колллекцию";
    }
}
