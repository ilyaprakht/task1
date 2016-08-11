package com.nc.task1.model.impl;

import com.nc.task1.model.*;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Реализация интерфейса DataBaseCommand для команды cp
 */
public class DataBaseCommandCopy implements DataBaseCommand {
    /**
     * Объект файла или папки, откуда выполняется копирование
     */
    private File fileFrom;

    /**
     * Объект файла или папки, куда выполняется копирование
     */
    private File fileTo;

    /**
     * Объект DAO доступа к БД
     */
    private FileDAO dao;

    /**
     * Конструктор
     * @param fileFrom - объект файла или папки, откуда выполняется копирование
     * @param fileTo - объект файла или папки, куда выполняется копирование
     */
    public DataBaseCommandCopy(File fileFrom, File fileTo, FileDAO dao) {
        this.fileFrom = fileFrom;
        this.fileTo = fileTo;
        this.dao = dao;
    }

    /**
     * Валидация команды на стороне базы данных
     * @throws DataBaseCommandException
     */
    @Override
    public void validate() throws DataBaseCommandException {
        System.out.println("validate cp in DB");

        // Проверяем, что файл, откуда выполняется копирование, есть в списке сканированных в БД
        if (!dao.existFile(fileFrom)) {
            throw new DataBaseCommandException("Выбранный файл не был ранее сканирован", fileFrom);
        }

        // Проверяем, что родительсий каталог для файла, куда выполняется копирование, есть в списке сканированных в БД
        File parentFile = dao.getFile(File.getParentFolderPath(fileTo));
        if (parentFile == null) {
            throw new DataBaseCommandException("Выбранный файл не был ранее сканирован", parentFile);
        }

        // Сохраняем родительскую папку для файла, куда выполняется копирование
        fileTo.setParentFolder(parentFile);
    }

    /**
     * Выполнение команды на стороне базы данных
     * @throws DataBaseCommandException
     */
    @Override
    public void execute() throws DataBaseCommandException {
        System.out.println("execute cp in DB");

        // Записываем файлы в БД рекурсивно
        createFilesRec(fileTo);
    }

    /**
     * Рекурсивное создание файлов
     * @param file - экземпляр файла
     * @throws DataBaseCommandException
     */
    private void createFilesRec(File file) throws DataBaseCommandException {
        // Записываем файл
        dao.create(file);
        // Если файл является папкой, то рекурсивно пробегаемся по всем его файлам и подпапкам
        if (file instanceof Folder) {
            for (File childFile : ((Folder) file).getListChildFiles()) {
                createFilesRec(childFile);
            }
        }
    }
}
