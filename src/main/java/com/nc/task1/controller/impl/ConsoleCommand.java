package com.nc.task1.controller.impl;

import com.nc.task1.controller.Command;

/**
 * Created by ilpr0816 on 15.08.2016.
 * Расширение класса Command для обработки команд из консоли
 */
public class ConsoleCommand extends Command {

    /**
     * Входная строка команды из консоли
     */
    private String commandLine;

    /**
     * Конструктор
     */
    public ConsoleCommand(String commandLine) {
        super();
        this.commandLine = commandLine;
    }

    /**
     * Обработка входящей из консоли строки команды
     */
    void parseCommandLine() throws ArrayIndexOutOfBoundsException {
        // Делим входную строку на литералы по пробелам
        String[] commandSplit = commandLine.split(" ");

        // Инициализируем параметры в зависимости от типа команды
        commandType = commandSplit[0];
        switch (commandType) {
            case COMMAND_MOVE:
            case COMMAND_COPY:
                path1 = commandSplit[1];
                path2 = commandSplit[2];
                break;
            case COMMAND_SCAN:
            case COMMAND_REMOVE:
                path1 = commandSplit[1];
                break;
        }
    }
}
