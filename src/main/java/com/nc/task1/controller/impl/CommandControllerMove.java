package com.nc.task1.controller.impl;

import com.nc.task1.controller.CommandController;
import com.nc.task1.model.File;
import com.nc.task1.model.Folder;
import com.nc.task1.model.impl.DataBaseCommandMove;
import com.nc.task1.model.impl.FileSystemCommandMove;

/**
 * Created by ilpr0816 on 10.08.2016.
 * Реализация класса контроллера для комнады scan
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
        // Создаем обработчик команды в ФС
        fileSystemCommand = new FileSystemCommandMove(pathFrom, pathTo);

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

        // Создаем обработчки команды в БД
        dataBaseCommand = new DataBaseCommandMove(fileFrom, fileTo, dao);
    }
}
