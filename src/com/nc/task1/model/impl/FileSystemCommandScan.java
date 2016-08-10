package com.nc.task1.model.impl;

import com.nc.task1.model.FileSystemCommand;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Реализация интерфейса FileSystemCommand для команды scan
 */
public class FileSystemCommandScan implements FileSystemCommand {
    /**
     * Путь к каталогу, в котором выполняется сканирование
     */
    private String path;

    /**
     * Конструктор
     * @param path - путь к каталогу
     */
    public FileSystemCommandScan(String path) {
        this.path = path;
    }

    /**
     * Валидация команды на стороне файловой системы
     */
    public void validate() {
        System.out.println("validate scan in FS");
    }

    /**
     * Выполнение команды на стороне файловой системы
     */
    public void execute() {
        System.out.println("execute scan in FS");
    }
}
