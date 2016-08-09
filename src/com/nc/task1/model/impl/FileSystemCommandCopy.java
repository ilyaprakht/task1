package com.nc.task1.model.impl;

import com.nc.task1.model.FileSystemCommand;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Реализация интерфейса FileSystemCommand для команды cp
 */
public class FileSystemCommandCopy implements FileSystemCommand {
    /**
     * Путь к файлу, откуда нужно скопировать
     */
    private String pathFrom;

    /**
     * Путь к файлу, куда нужно скопировать
     */
    private String pathTo;

    /**
     * Конструктор
     * @param pathFrom - путь к файлу, откуда нужно скопировать
     * @param pathTo - путь к файлу, куда нужно скопировать
     */
    FileSystemCommandCopy(String pathFrom, String pathTo) {
        this.pathFrom = pathFrom;
        this.pathTo = pathTo;
    }
    /**
     * Валидация команды на стороне файловой системы
     */
    public void validate() {
        System.out.println("validate cp paths");
    }

    /**
     * Выполнение команды на стороне файловой системы
     */
    public void execute() {
        System.out.println("execute cp files");
    }
}
