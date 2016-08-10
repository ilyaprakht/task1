package com.nc.task1.controller.impl;

import com.nc.task1.controller.CommandController;
import com.nc.task1.model.impl.DataBaseCommandMove;
import com.nc.task1.model.impl.DataBaseCommandScan;
import com.nc.task1.model.impl.FileSystemCommandMove;

/**
 * Created by ilpr0816 on 10.08.2016.
 */
public class CommandControllerMove extends CommandController {

    /**
     * Путь к файлу, откуда выполняется перенос
     */
    private String pathFrom;

    /**
     * Путь к файлу, куда выполняется перенос
     */
    private String pathTo;

    /**
     * Конструктор
     * @param pathFrom - путь к файлу, откуда выполняется перенос
     * @param pathTo - путь к файлу, куда выполняется перенос
     */
    public CommandControllerMove(String pathFrom, String pathTo) {
        this.pathFrom = pathFrom;
        this.pathTo = pathTo;
    }

    /**
     * Фабричный метод создания экземпляров модели, выполняющих команду move
     */
    @Override
    protected void FactoryMethodInitCommands() {
        fileSystemCommand = new FileSystemCommandMove(pathFrom, pathTo);
        dataBaseCommand = new DataBaseCommandMove(getFileByPath(pathFrom), getFileByPath(pathTo));
    }
}
