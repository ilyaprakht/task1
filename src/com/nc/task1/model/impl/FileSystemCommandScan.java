package com.nc.task1.model.impl;

import com.nc.task1.model.FileSystemCommand;
import com.nc.task1.model.FileSystemCommandException;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Реализация интерфейса FileSystemCommand для команды scan
 */
public class FileSystemCommandScan implements FileSystemCommand {
    /**
     * Путь к каталогу, в котором выполняется сканирование
     */
    private String path;

    /**
     * Конструктор
     * @param path - путь к каталогу
     */
    public FileSystemCommandScan(String path) {
        this.path = path;
    }

    /**
     * Валидация команды на стороне файловой системы
     * @throws FileSystemCommandException
     */
    @Override
    public void validate() throws FileSystemCommandException {
        System.out.println("validate scan in FS");

        java.io.File hFile = new java.io.File(path);
        // Проверяем что такой файл есть в ФС
        if (!hFile.exists()) {
            throw new FileSystemCommandException("Указанный файл не найден", path);
        }

        //Делаем рекурсивную валидацию от указанного файла и по всем его вложениям
        validateChild(path);
    }

    /**
     * Валидация вложенного файла или папки
     * @param path - путь к файлу или папке
     * @throws FileSystemCommandException
     */
    @Override
    public void validateChild(String path) throws FileSystemCommandException {
        java.io.File hFile = new java.io.File(path);

        // Проверяем, что есть доступ на чтение
        if (!hFile.canRead()) {
            throw new FileSystemCommandException("Невозможно прочитать файл", path);
        }

        // Если указана директория, то проверяем все ее вложенные файлы
        if (hFile.isDirectory()) {
            for (java.io.File childFile : hFile.listFiles()) { // рекурсивно пробегаемся по всем ее файлам и поддиректориям
                validateChild(childFile.getAbsolutePath());
            }
        }
    }

    /**
     * Выполнение команды на стороне файловой системы
     * @throws FileSystemCommandException
     */
    @Override
    public void execute() throws FileSystemCommandException {
        System.out.println("execute scan in FS");
        // В текущей реализации никаких действий на стороне ФС при сканировании не выполняется
    }
}
