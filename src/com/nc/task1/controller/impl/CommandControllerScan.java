package com.nc.task1.controller.impl;

import com.nc.task1.controller.CommandController;
import com.nc.task1.model.File;
import com.nc.task1.model.impl.DataBaseCommandScan;
import com.nc.task1.model.impl.FileSystemCommandScan;

/**
 * Created by ilpr0816 on 10.08.2016.
 * Реализация класса контроллера для комнады scan
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
        // Создаем обработчик команды в ФС
        fileSystemCommand = new FileSystemCommandScan(path);

        // Определяем объект файла
        File file = File.getFileByPath(path, null);

        // Создаем обработчик команды в ФС
        dataBaseCommand = new DataBaseCommandScan(file, dao);
    }
}
