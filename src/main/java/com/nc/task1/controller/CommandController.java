package com.nc.task1.controller;

import com.nc.task1.model.*;
import com.nc.task1.model.impl.JDBCMysqlHandler;

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
     * Объект DAO доступа к БД
     */
    protected FileDAO dao;

    /**
     * Основной метод выполнения команды
     * @throws FileSystemCommandException
     * @throws DataBaseCommandException
     */
    public void executeCommand() throws FileSystemCommandException, DataBaseCommandException {
        // Инициализация конкретной реализации интерфейса FileDAO
        dao = new JDBCMysqlHandler();

        // Инициализация конкретных обработчиков команд
        FactoryMethodInitCommands();

        // Выполнение команды на стороне ФС
        executeFileSystemCommand();

        // Выполнение команды на стороне БД
        executeDataBaseCommand();
    }

    /**
     * Выполнение команды на стороне файловой системы
     * @throws FileSystemCommandException
     */
    protected void executeFileSystemCommand() throws FileSystemCommandException {
        fileSystemCommand.validate();
        fileSystemCommand.execute();
    }

    /**
     * Выполнение команды на стороне БД
     * @throws DataBaseCommandException
     */
    protected void executeDataBaseCommand() throws DataBaseCommandException {
        dataBaseCommand.validate();
        dataBaseCommand.execute();
    }

    /**
     * Фабричный метод создания конкретных экземпляров модели, выполняющих выбранную команду
     */
    protected abstract void FactoryMethodInitCommands();
}
