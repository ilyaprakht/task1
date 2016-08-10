package com.nc.task1.controller.impl;

import com.nc.task1.controller.CommandController;
import com.nc.task1.model.File;
import com.nc.task1.model.impl.DataBaseCommandScan;
import com.nc.task1.model.impl.FileSystemCommandScan;

/**
 * Created by ilpr0816 on 10.08.2016.
 */
public class CommandControllerScan extends CommandController {
    /**
     * Путь к файлу
     */
    private String path;

    /**
     * Конструктор
     * @param path - путь к файлу
     */
    public CommandControllerScan(String path) {
        this.path = path;
    }

    /**
     * Фабричный метод создания экземпляров модели, выполняющих команду scan
     */
    @Override
    protected void FactoryMethodInitCommands() {
        fileSystemCommand = new FileSystemCommandScan(path);
        dataBaseCommand = new DataBaseCommandScan(getFileByPath(path));
    }
}
