package com.nc.task1.controller.impl;

import com.nc.task1.controller.CommandController;
import com.nc.task1.model.File;
import com.nc.task1.model.Folder;
import com.nc.task1.model.impl.DataBaseCommandCopy;
import com.nc.task1.model.impl.FileSystemCommandCopy;

/**
 * Created by ilpr0816 on 10.08.2016.
 * Реализация класса контроллера для комнады cp
 */
public class CommandControllerCopy extends CommandController {

    /**
     * Путь к файлу, откуда выполняется копирование
     */
    private String pathFrom;

    /**
     * Путь к файлу, куда выполняется копирование
     */
    private String pathTo;

    /**
     * Конструктор
     * @param pathFrom - путь к файлу, откуда выполняется копирование
     * @param pathTo - путь к файлу, куда выполняется копирование
     */
    public CommandControllerCopy(String pathFrom, String pathTo) {
        this.pathFrom = pathFrom;
        this.pathTo = pathTo;
    }

    /**
     * Фабричный метод создания экземпляров модели, выполняющих команду copy
     */
    @Override
    protected void FactoryMethodInitCommands() {
        fileSystemCommand = new FileSystemCommandCopy(pathFrom, pathTo);

        // Определяем объект файла, который перемещается
        File fileFrom = File.getFileByPath(pathFrom, null);

        // Определяем объект файла, куда перемещается путем клонирования и замены путей
        File fileTo = null;
        if (fileFrom != null) {
            if (fileFrom instanceof Folder) {
                fileTo = ((Folder) fileFrom).clone();
            } else {
                fileTo = fileFrom.clone();
            }
        }
        if (fileTo != null) {
            File.changeFilePathToByPathFrom(fileTo, pathFrom, pathTo, null);
        }
        dataBaseCommand = new DataBaseCommandCopy(fileFrom, fileTo, dao);
    }
}
