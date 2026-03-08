package mainpart;

import commands.Invoker;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

//record ExecutionContext(Scanner sc, List<String> argc, boolean shouldBeLaud){}

public class Executor {
    private final Invoker invoker;

    public Executor(Invoker invoker) {
        this.invoker = invoker;
    }

    public ReturnCode Execute(int depth, InputStream stream) {
//        System.out.println("Execute. Depth = " + depth);
        if (depth == 4) {
            return ReturnCode.FAILED;
        }

        Scanner scanner = new Scanner(stream);
        
        while (scanner.hasNextLine()) {

            String input = scanner.nextLine().trim();

            if (input.equals("exit")) {
//                System.out.println("Выход из программы...");
                break;
            }

            if (input.isEmpty()) continue;

            String[] parts = input.split("\\s+");
            String commandName = parts[0];

            if (!commandName.equals("execute_script")) {
                invoker.executeCommand(commandName, List.of(parts), scanner, !(stream instanceof FileInputStream));
                continue;
            }

            if (parts.length != 2) {
                return ReturnCode.FAILED;
            }
//            System.out.println(parts[1]);
            try {
                ReturnCode code = Execute(depth + 1, new FileInputStream(parts[1]));
                if (code != ReturnCode.OK) {
                    return code;
                }
            } catch (NoSuchElementException e) {
                System.out.println("\n[Ожидание ввода...]");
            } catch (Exception e) {
                System.out.println("the file is not exist");
            }


        }
        return ReturnCode.OK;
    }


}
