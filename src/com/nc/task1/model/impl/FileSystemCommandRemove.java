package com.nc.task1.model.impl;

import com.nc.task1.model.FileSystemCommand;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Реализация интерфейса FileSystemCommand для команды rm
 */
public class FileSystemCommandRemove implements FileSystemCommand {
    /**
     * Путь к файлу, который нужно удалить
     */
    private String path;

    /**
     * Конструктор
     * @param path - путь к файлу, который нужно удалить
     */
    FileSystemCommandRemove(String path) {
        this.path = path;
    }
    /**
     * Валидация команды на стороне файловой системы
     */
    public void validate() {
        System.out.println("validate rm path");
    }

    /**
     * Выполнение команды на стороне файловой системы
     */
    public void execute() {
        System.out.println("execute rm files");
    }
}
