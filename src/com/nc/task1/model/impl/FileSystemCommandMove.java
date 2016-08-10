package com.nc.task1.model.impl;

import com.nc.task1.model.FileSystemCommand;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Реализация интерфейса FileSystemCommand для команды mv
 */
public class FileSystemCommandMove implements FileSystemCommand {
    /**
     * Путь к файлу, откуда нужно перенести
     */
    private String pathFrom;

    /**
     * Путь к файлу, куда нужно перенести
     */
    private String pathTo;

    /**
     * Конструктор
     * @param pathFrom - путь к файлу, откуда нужно перенести
     * @param pathTo - путь к файлу, куда нужно перенести
     */
    public FileSystemCommandMove(String pathFrom, String pathTo) {
        this.pathFrom = pathFrom;
        this.pathTo = pathTo;
    }
    /**
     * Валидация команды на стороне файловой системы
     */
    public void validate() {
        System.out.println("validate mv in FS");
    }

    /**
     * Выполнение команды на стороне файловой системы
     */
    public void execute() {
        System.out.println("execute mv in FS");
    }
}
