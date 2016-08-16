package com.nc.task1.model.impl;

import com.nc.task1.controller.OutDataBuffer;
import com.nc.task1.model.FileSystemCommand;
import com.nc.task1.model.FileSystemCommandException;

import java.io.IOException;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Реализация интерфейса FileSystemCommand для команды cp
 */
public class FileSystemCommandCopy implements FileSystemCommand {
    /**
     * Путь к файлу, откуда нужно скопировать
     */
    private String pathFrom;

    /**
     * Путь к файлу, куда нужно скопировать
     */
    private String pathTo;

    /**
     * Конструктор
     * @param pathFrom - путь к файлу, откуда нужно скопировать
     * @param pathTo - путь к файлу, куда нужно скопировать
     */
    public FileSystemCommandCopy(String pathFrom, String pathTo) {
        this.pathFrom = pathFrom;
        this.pathTo = pathTo;
    }

    /**
     * Валидация команды на стороне файловой системы
     */
    @Override
    public void validate() throws FileSystemCommandException {
        OutDataBuffer.outData.append("validate cp in FS");

        // Проверяем файл, который копируем
        java.io.File hFileFrom = new java.io.File(pathFrom);
        // Проверяем что такой файл есть в ФС
        if (!hFileFrom.exists()) {
            throw new FileSystemCommandException("Указанный файл не найден", pathFrom);
        }

        //Делаем рекурсивную валидацию от указанного файла и по всем его вложениям
        validateChild(pathFrom);

        // Проверяем файл, куда копируем
        java.io.File hFileTo = new java.io.File(pathTo);
        // Проверяем что такой файл есть в ФС
        if (hFileTo.exists()) {
            // Если файл существует, проверяем права на запись
            if (!hFileTo.canWrite()) {
                throw new FileSystemCommandException("Невозможно записать файл", pathTo);
            }
        } else {
            // Если файла нет, то проверяем права на запись у родительского каталога
            java.io.File parentFile = hFileTo.getParentFile();

            if (!parentFile.exists()) {
                throw new FileSystemCommandException("Некорректный путь для нового файла", parentFile.getAbsolutePath());
            }

            if (!parentFile.canWrite()) {
                throw new FileSystemCommandException("Невозможно записать файл", parentFile.getAbsolutePath());
            }
        }
    }

    /**
     * Валидация вложенного файла или папки
     * @param path - путь к файлу или папке
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
     */
    @Override
    public void execute() throws FileSystemCommandException {
        OutDataBuffer.outData.append("execute cp in FS");

        // Копируем рекурсивно файлы
        copyFilesRec(pathFrom);
    }

    /**
     * Рекурсивное копирование файлов
     * @param path - путь к файлу, кеоторый нужно скопировать
     */
    private void copyFilesRec(String path) throws FileSystemCommandException {
        java.io.File hFileFrom = new java.io.File(path);
        java.io.File hFileTo = new java.io.File(path.replace(pathFrom, pathTo));

        try {
            // Копируем сам файл
            Files.copy(hFileFrom.toPath(), hFileTo.toPath(), REPLACE_EXISTING);
            // Если это директория, то рекурсивно пробегаемся по всем ее файлам и поддиректориям и копируем их
            if (hFileFrom.isDirectory()) {
                for (java.io.File childFile : hFileFrom.listFiles()) {
                    copyFilesRec(childFile.getAbsolutePath());
                }
            }
        }
        catch (IOException e) {
            throw new FileSystemCommandException("Невозможно скопировать файлы", hFileFrom.getAbsolutePath(), hFileTo.getAbsolutePath());
        }
    }
}
