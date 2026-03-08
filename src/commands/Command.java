package commands;

import mainpart.ReturnCode;


import java.util.List;
import java.util.Scanner;

public interface Command {
    ReturnCode execute(List<String> args, Scanner scanner, Boolean isLaud);
    String getDescription();

}
