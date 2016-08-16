package com.nc.task1.controller;

/**
 * Created by ilpr0816 on 15.08.2016.
 * Класс, инкапсулирующий команду
 */
public class Command {

    /**
     * Статический константы для обозначения соответствующих команд
     */
    protected static final String COMMAND_SCAN = "scan";
    protected static final String COMMAND_MOVE = "mv";
    protected static final String COMMAND_COPY = "cp";
    protected static final String COMMAND_REMOVE = "rm";
    static final String COMMAND_PRINT = "print";
    static final String COMMAND_EXIT = "exit";

    /**
     * Тип команды
     */
    protected String commandType;

    /**
     * Путь к первому файлу
     */
    protected String path1;

    /**
     * Путь ко второму файлу
     */
    protected String path2;

    /**
     * Конструктор по умолчанию
     */
    public Command() {

    }

    /**
     * Геттер для типа команды
     * @return тип команды
     */
    String getCommandType() {
        return commandType;
    }

    /**
     * Геттер для пути к первому файлу
     * @return путь к первому файлу
     */
    String getPath1() {
        return path1;
    }

    /**
     * Геттер для пути ко второму файлу
     * @return - путь ко второму файлу
     */
    String getPath2() {
        return path2;
    }
}
