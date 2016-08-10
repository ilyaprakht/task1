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
     */
    public void executeCommand() throws FileSystemCommandException {
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
     */
    private void executeFileSystemCommand() throws FileSystemCommandException {
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
    protected File getFileByPath(String path, File parentFolder) {
        java.io.File hFile = new java.io.File(path);
        if (hFile.exists()) { // Проверяем что такой файл есть в ФС
            if (hFile.isDirectory()) { // Если это директория, то рекурсивно пробегаемся по всем ее файлам и поддиректориям
                Folder sFile = new Folder(path, parentFolder);
                for (java.io.File childFile : hFile.listFiles()) { // рекурсивно пробегаемся по всем ее файлам и поддиректориям, добавляем в лист
                    sFile.getListChildFiles().add(getFileByPath(childFile.getAbsolutePath(), sFile));
                }
                return sFile;
            } else {
                File sFile = new File(path, parentFolder);
                return sFile;
            }
        }
        return null; // если файл не найден, возвращаем null - для случаев перемещения и копирования, валидация будет дальше
    }
}
