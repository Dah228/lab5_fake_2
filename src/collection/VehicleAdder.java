package collection;


import parser.FuelType;
import parser.Vehicle;
import parser.VehicleType;
import printer.ResponseSender;

import java.util.List;
import java.util.Scanner;

import static java.util.Collections.max;

public class VehicleAdder {
    private final VehicleCollection collection;
    private final ResponseSender responseSender;

    public VehicleAdder(VehicleCollection collection, ResponseSender responseSender) {
        this.responseSender = responseSender;
        this.collection = collection;

    }


    public void updateElementByID(long id, Scanner scanner, Boolean isLaud) {
        if (collection.getVehicleByID(id) != null) {
            collection.rmEl(collection.getVehicleByID(id));
            Vehicle veh = parseVehicle(scanner, isLaud);
            collection.add(veh);
            collection.changeID(id, veh.getId());
            VehicleRandom veeh = new VehicleRandom(collection);
            veeh.sortByID();
            if (isLaud) responseSender.send("Элемент обновлен");
        }
    }


    public void rmByID(long id, Boolean isLaud) {
        List arr = collection.getAllID();
        for (Object i : arr) {
            if ((long) i == id) {
                collection.rmEl(collection.getVehicleByID(id));
            }
        }
        if (isLaud) responseSender.send("Элемент удален");

    }

    public Vehicle parseVehicle(Scanner scanner, Boolean isLaud) {
        DataValidator validator = new DataValidator(scanner, isLaud, responseSender);
        Vehicle veh = new Vehicle();
        var Id = collection.getAllID();
        if (!Id.isEmpty()) {
            veh.setId((long) (max(collection.getAllID())) + 1);
        } else veh.setId(1);

        String name = validator.readValidName("Введите имя: ", isLaud);
        veh.setName(name);
        veh.setCreationDate(); // автогенерация

        // Координаты X
        Integer x = validator.readValidInteger("Введите X (целое число): ", isLaud);

        // Координаты Y
        Float y = validator.readValidFloat("Введите Y (> -668): ", -668F, isLaud);

        veh.setCoordinates(x, y);

        // Engine Power
        Float enginePower = validator.readValidFloat("Введите мощность двигателя (> 0): ", 0f, isLaud);
        veh.setEnginePower(enginePower);

        // Distance Traveled
        Float distance = validator.readValidFloat("Введите пройденное расстояние (> 0): ", 0f, isLaud);
        veh.setDistanceTravelled(distance);

        // Vehicle Type (может быть null)
        VehicleType type = validator.readVehicleType("\"Введите тип (PLANE, HELICOPTER, BOAT, SHIP, HOVERBOARD) или пустую строку для null:\"", isLaud);
        veh.setType(type);

        // Fuel Type (не может быть null)
        FuelType fuelType = validator.readFuelType("\"Введите тип топлива (GASOLINE, KEROSENE, ELECTRICITY, DIESEL, NUCLEAR):\"", isLaud);
        veh.setFuelType(fuelType);
        return veh;
    }


    public void addElement(Scanner scanner, Boolean isLaud) {
        Vehicle veh = parseVehicle(scanner, isLaud);
        collection.add(veh);
        if (isLaud) responseSender.send("Элемент добавлен");

    }


    public void addIfMax(Scanner scanner, Boolean isLaud) {
        Vehicle veh = parseVehicle(scanner, isLaud);
        if (collection.getVehicles().stream().allMatch(v -> v.getDistanceTravelled() < veh.getDistanceTravelled())) {
            collection.add(veh);
            if (isLaud) responseSender.send("Элемент добавлен");
        } else {
            if (isLaud)
                responseSender.send("Не добавлено: в коллекции есть элементы с distanceTravelled >= " + veh.getDistanceTravelled());
        }

    }


}
