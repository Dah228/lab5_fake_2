package mainpart;

import collection.*;
import commands.*;
import parser.Parser;
import parser.Vehicle;
import printer.ConsoleResponseSender;
import printer.ResponseSender;

import static parser.Vehicle.printVehicle;

public class Main {
    public static void main(String[] arg) {


        VehicleCollection collection = new VehicleCollection();
        Invoker invoker = new Invoker();
        ResponseSender responseSender = new ConsoleResponseSender();


        VehicleManager manager = new VehicleManager(collection, responseSender);
        VehicleRandom random = new VehicleRandom(collection);
        VehicleSaver saver = new VehicleSaver(collection, responseSender);
        VehicleAdder adder = new VehicleAdder(collection, responseSender);


        invoker.registerCommand("clear", new ClearCommand(manager));
        invoker.registerCommand("filter_greater_than_engine_power", new CompareByEnginePowerCommand(manager));
        invoker.registerCommand("info", new InfoCommand(manager));
        invoker.registerCommand("show", new ShowCommand(manager));
        invoker.registerCommand("remove_by_id", new RemoveByID(adder));
        invoker.registerCommand("print_descending", new PrintDescendingCommand(random));
        invoker.registerCommand("save", new SaveCommand(saver));
        invoker.registerCommand("shuffle", new ShuffleCommand(random));
        invoker.registerCommand("sort", new SortCommand(random));
        invoker.registerCommand("filter_less_than_type", new FilterLessThatType(manager));

        invoker.registerCommand("add", new AddCommand(adder));
        invoker.registerCommand("add_if_max", new AddIfMax(adder));
        invoker.registerCommand("update", new UpdateElementID(adder));


        invoker.registerCommand("help", new HelpCommand(invoker.getCommands()));


        try {
            String filePath = arg.length > 0 ? arg[0] : "collection.xml";
            collection.addList(Parser.parse(filePath));
//            collection.addList(Parser.parse("src/collection/collection.xml"));
            for (Vehicle v : collection.getVehicles()) printVehicle(v);
        } catch (Exception e) {
            System.out.println("Указанного файла не существует/ не соответствует заданному формату введите корректный файл");
        }


        Executor executor = new Executor(invoker);


        executor.Execute(0, System.in);


    }
}