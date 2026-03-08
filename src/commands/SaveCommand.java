package commands;

import collection.VehicleSaver;
import mainpart.ReturnCode;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;
import java.util.Scanner;

public class SaveCommand implements Command{
    VehicleSaver vehicleSaver;
    public SaveCommand(VehicleSaver vehicleSaver){
        this.vehicleSaver = vehicleSaver;
    }

@Override
    public ReturnCode execute(List<String> args, Scanner scanner, Boolean isLaud){
        if (args.size()!= 1) return ReturnCode.FAILED;
        try {
            vehicleSaver.saveToFile();
            return ReturnCode.OK;
        } catch (ParserConfigurationException e) {
            System.out.println("Нарушена поддержка парсера");
            return ReturnCode.FAILED;
        } catch (TransformerException e) {
            System.out.println("Ошибка записи файла");
            return ReturnCode.FAILED;
        }
    }

    @Override
    public String getDescription() {
        return " сохранить коллекцию в файл";
    }
}
