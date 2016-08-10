package com.nc.task1.controller.impl;

import com.nc.task1.controller.CommandController;
import com.nc.task1.model.File;
import com.nc.task1.model.FileDAO;
import com.nc.task1.model.impl.DataBaseCommandCopy;
import com.nc.task1.model.impl.FileSystemCommandCopy;
import com.nc.task1.model.impl.JDBCMysqlHandler;

/**
 * Created by ilpr0816 on 10.08.2016.
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

        File fileFrom = getFileByPath(pathFrom, null);
        File fileTo = getFileByPath(pathTo, null);
        dataBaseCommand = new DataBaseCommandCopy(fileFrom, fileTo, dao);
    }
}
