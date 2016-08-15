package com.nc.task1.controller;

/**
 * Created by ilpr0816 on 15.08.2016.
 * Класс, инкапсулирующий команду
 */
public class Command {

    /**
     * Статический константы для обозначения соответствующих команд
     */
    public static final String COMMAND_SCAN = "scan";
    public static final String COMMAND_MOVE = "mv";
    public static final String COMMAND_COPY = "cp";
    public static final String COMMAND_REMOVE = "rm";
    public static final String COMMAND_PRINT = "print";
    public static final String COMMAND_EXIT = "exit";

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
     * Конструктор
     * @param commandType - тип команды
     */
    public Command(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Конструктор
     * @param commandType - тип команды
     * @param path1 - путь к первому файлу
     */
    public Command(String commandType, String path1) {
        this(commandType);
        this.path1 = path1;
    }

    /**
     * Конструктор
     * @param commandType - тип команды
     * @param path1 - путь к первому файлу
     * @param path2 - путь ко второму файлу
     */
    public Command(String commandType, String path1, String path2) {
        this(commandType, path1);
        this.path2 = path2;
    }

    /**
     * Геттер для типа команды
     * @return тип команды
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Геттер для пути к первому файлу
     * @return путь к первому файлу
     */
    public String getPath1() {
        return path1;
    }

    /**
     * Геттер для пути ко второму файлу
     * @return - путь ко второму файлу
     */
    public String getPath2() {
        return path2;
    }
}
