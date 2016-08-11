package com.nc.task1.model.impl;

import com.nc.task1.model.FileSystemCommand;
import com.nc.task1.model.FileSystemCommandException;

import java.nio.file.Files;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Реализация интерфейса FileSystemCommand для команды rm
 */
public class FileSystemCommandRemove implements FileSystemCommand {
    /**
     * Путь к файлу, который нужно удалить
     */
    private String path;

    /**
     * Конструктор
     * @param path - путь к файлу, который нужно удалить
     */
    public FileSystemCommandRemove(String path) {
        this.path = path;
    }
    /**
     * Валидация команды на стороне файловой системы
     */
    public void validate() throws FileSystemCommandException {
        System.out.println("validate rm in FS");

        java.io.File hFile = new java.io.File(path);
        // Проверяем что такой файл есть в ФС
        if (!hFile.exists()) {
            throw new FileSystemCommandException("Указанный файл не найден", path);
        }

        //Делаем рекурсивную валидацию от указанного файла и по всем его вложениям
        validateChild(path);
    }

    @Override
    public void validateChild(String path) throws FileSystemCommandException {
        java.io.File hFile = new java.io.File(path);

        // Проверяем, что есть доступ на чтение
        if (!hFile.canWrite()) {
            throw new FileSystemCommandException("Невозможно удалить файл", path);
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
     */
    public void execute() throws FileSystemCommandException {
        System.out.println("execute rm in FS");

        // Удаляем рекурсивно файлы
        deleteFilesRec(path);
    }

    private void deleteFilesRec(String path) throws FileSystemCommandException {
        java.io.File hFile = new java.io.File(path);
        try {
            // Если это директория, то рекурсивно пробегаемся по всем ее файлам и поддиректориям и удаляем их
            if (hFile.isDirectory()) {
                for (java.io.File childFile : hFile.listFiles()) {
                    deleteFilesRec(childFile.getAbsolutePath());
                }
            }
            // Удаляем сам файл
            Files.delete(hFile.toPath());
        }
        catch (Exception e) {
            throw new FileSystemCommandException("Невозможно удалить файл", path);
        }
    }
}
