package com.nc.task1.controller;

import com.nc.task1.model.DataBaseCommand;
import com.nc.task1.model.File;
import com.nc.task1.model.FileSystemCommand;

/**
 * Created by ilpr0816 on 10.08.2016.
 * Абстрактный класс выполнения команды
 */
public abstract class CommandController {
    /**
     * Экземпляр модели, выполняющий команду в файловой системе
     */
    protected FileSystemCommand fileSystemCommand;

    /**
     * Экземпляр модели, выполняющий команду в БД
     */
    protected DataBaseCommand dataBaseCommand;

    /**
     * Основной метод выполнения команды
     */
    public void executeCommand() {
        FactoryMethodInitCommands();
        executeFileSystemCommand();
        executeDataBaseCommand();
    }

    /**
     * Выполнение команды на стороне файловой системы
     */
    private void executeFileSystemCommand() {
        fileSystemCommand.validate();
        fileSystemCommand.execute();
    }

    /**
     * Выполнение команды на стороне БД
     */
    private void executeDataBaseCommand() {
        dataBaseCommand.validate();
        dataBaseCommand.execute();
    }

    /**
     * Фабричный метод создания конкретных экземпляров модели, выполняющих выбранную команду
     */
    protected abstract void FactoryMethodInitCommands();

    /**
     * Возвращает объект файла или папки по пути
     * @param path - путь к файлу
     * @return - объект соответствующего файла
     */
    protected File getFileByPath(String path) {
        return new File(path, null); //TODO определение файла правильное
    }
}
