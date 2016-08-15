package com.nc.task1.model.impl;

import com.nc.task1.controller.OutDataBuffer;
import com.nc.task1.model.FileSystemCommand;
import com.nc.task1.model.FileSystemCommandException;

import java.io.IOException;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Реализация интерфейса FileSystemCommand для команды mv
 */
public class FileSystemCommandMove implements FileSystemCommand {
    /**
     * Путь к файлу, откуда нужно перенести
     */
    private String pathFrom;

    /**
     * Путь к файлу, куда нужно перенести
     */
    private String pathTo;

    /**
     * Конструктор
     * @param pathFrom - путь к файлу, откуда нужно перенести
     * @param pathTo - путь к файлу, куда нужно перенести
     */
    public FileSystemCommandMove(String pathFrom, String pathTo) {
        this.pathFrom = pathFrom;
        this.pathTo = pathTo;
    }

    /**
     * Валидация команды на стороне файловой системы
     * @throws FileSystemCommandException
     */
    @Override
    public void validate() throws FileSystemCommandException {
        OutDataBuffer.outData.append("validate mv in FS");

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
     * @throws FileSystemCommandException
     */
    @Override
    public void validateChild(String path) throws FileSystemCommandException {
        java.io.File hFile = new java.io.File(path);

        // Проверяем, что есть доступ на чтение
        if (!hFile.canWrite()) {
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
        OutDataBuffer.outData.append("execute mv in FS");

        java.io.File hFileFrom = new java.io.File(pathFrom);
        java.io.File hFileTo = new java.io.File(pathTo);
        try {
            Files.move(hFileFrom.toPath(), hFileTo.toPath(), REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileSystemCommandException("Невозможно перенести файлы", pathFrom, pathTo);
        }
    }
}
