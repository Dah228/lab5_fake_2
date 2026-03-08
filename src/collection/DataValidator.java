package collection;

import parser.FuelType;
import parser.VehicleType;
import printer.ResponseSender;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DataValidator {
    Scanner scanner;
    Boolean isLaud;
    private final ResponseSender responseSender;

    public DataValidator(Scanner scanner, Boolean isLaud, ResponseSender responseSender) {
        this.scanner = scanner;
        this.isLaud = isLaud;
        this.responseSender = responseSender;
    }


    /**
     * Универсальный метод для чтения и валидации данных.
     *
     * @param prompt   текст подсказки
     * @param isLaud   режим (true = консоль с циклом, false = файл с одной попыткой)
     * @param parser   функция, которая превращает String в T или бросает IllegalArgumentException
     * @param emptyMsg сообщение, если введена пустая строка (для консоли)
     * @param <T>      тип возвращаемого значения
     * @return валидное значение типа T
     */
    private <T> T readValidatedInput(
            String prompt,
            Boolean isLaud,
            java.util.function.Function<String, T> parser,
            String emptyMsg,
            String errorMsg
    ) {
        if (isLaud) {
            while (true) {
                responseSender.send(prompt);
                String input = scanner.nextLine().trim();
//
//                if (input.isEmpty()) {
//                    responseSender.send(emptyMsg);
//                    continue;
//                }

                try {
                    return parser.apply(input);
                } catch (IllegalArgumentException e) {
                    responseSender.send(errorMsg);
                } catch (NoSuchElementException e) {
                    responseSender.send("\n[Ввод прерван, ожидаем новые данные...]");
                }
            }
        } else {
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("Файл закончился: " + prompt);
            }

            String input = scanner.nextLine().trim();

//            if (input.isEmpty()) {
//                throw new IllegalArgumentException("Ошибка в файле: " + emptyMsg);
//            }

            try {
                return parser.apply(input);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(errorMsg + ": '" + input + "'");
            }
        }
    }

    public Integer readValidInteger(String prompt, Boolean isLaud) {
        return readValidatedInput(
                prompt, isLaud,
                Integer::valueOf,
                "Поле не может быть пустым!",
                "Ошибка ввода, ожидалось целое число"
        );
    }

    public Float readValidFloat(String prompt, Float min, Boolean isLaud) {
        return readValidatedInput(
                prompt, isLaud,
                s -> {
                    float val = Float.parseFloat(s);
                    if (val <= min) throw new IllegalArgumentException("Некорректное значение!");
                    return val;
                },
                "Поле не может быть пустым!",
                "Ошибка ввода, ожидалось число"
        );
    }

    public VehicleType readVehicleType(String prompt, Boolean isLaud) {
        return readValidatedInput(
                prompt, isLaud,
                s -> s.isEmpty() ? null : VehicleType.valueOf(s.toUpperCase()),
                "Введите тип или пустую строку для пропуска",
                "Неверный тип! Доступны: " + Arrays.toString(VehicleType.values())
        );
    }

    public FuelType readFuelType(String prompt, Boolean isLaud) {
        return readValidatedInput(
                prompt, isLaud,
                s -> FuelType.valueOf(s.toUpperCase()),
                "Тип топлива не может быть пустым!",
                "Неверный тип топлива! Доступны: " + Arrays.toString(FuelType.values())
        );
    }

    private boolean isValidForXml(String text) {
        for (char c : text.toCharArray()) {
            if (c == '<' || c == '>' || c == '&' || c == '"' || c == '\'') {
                return false;
            }
        }
        return true;
    }

    public String readValidName(String prompt, Boolean isLaud) {
        return readValidatedInput(
                prompt, isLaud,
                s -> {
                    if (!isValidForXml(s)) {
                        throw new IllegalArgumentException("XML-unsafe символы");
                    }
                    return s;
                },
                "Имя не может быть пустым!",
                "Имя содержит недопустимые символы для XML: < > & \" '"
        );
    }

}


