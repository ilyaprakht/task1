package com.nc.task1.controller;

import com.nc.task1.controller.impl.CommandControllerCopy;
import com.nc.task1.controller.impl.CommandControllerMove;
import com.nc.task1.controller.impl.CommandControllerRemove;
import com.nc.task1.controller.impl.CommandControllerScan;

import java.util.Scanner;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Основной контроллер всех действий с консолью
 */
public class ConsoleController {
    /**
     * Основной метод работы контроллера - считывание команды из консоли и дальнейшая обработка
     */
    public void run() {
        String commandLine;

        Scanner scanner = new Scanner(System.in);

        do {
            // Считывание ввода с консоли
            commandLine = scanner.nextLine();
            String[] commandSplit = commandLine.split(" ");

            CommandController controller = null;

            // Определение соответствующего контроллера для команды
            try {
                switch (commandSplit[0]) {
                    case "scan":
                        System.out.println("command scan action");
                        controller = new CommandControllerScan(commandSplit[1]);
                        break;
                    case "mv":
                        System.out.println("command move action");
                        controller = new CommandControllerMove(commandSplit[1], commandSplit[2]);
                        break;
                    case "cp":
                        System.out.println("command copy action");
                        controller = new CommandControllerCopy(commandSplit[1], commandSplit[2]);
                        break;
                    case "rm":
                        System.out.println("command remove action");
                        controller = new CommandControllerRemove(commandSplit[1]);
                        break;
                    case "exit":
                        System.out.println("exit program");
                        break;
                    default:
                        System.out.println("unknown command. please, try again");
                        break;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) { //Некорректный формат команды, указано неверное количество литералов
                System.out.println("incorrect command format. please, try again");
            }

            // Если контроллер определен, выполняем команду
            if (controller != null) {
                controller.executeCommand();
            }
        } while (!"exit".equals(commandLine)); // при вводе exit - выход из программы
    }
}
