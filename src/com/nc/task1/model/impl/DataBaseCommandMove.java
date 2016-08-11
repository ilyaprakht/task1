package com.nc.task1.model.impl;

import com.nc.task1.model.*;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Реализация интерфейса DataBaseCommand для команды mv
 */
public class DataBaseCommandMove implements DataBaseCommand {
    /**
     * Объект файла или папки, откуда выполняется перемещение
     */
    private File fileFrom;

    /**
     * Объект файла или папки, куда выполняется перемещение
     */
    private File fileTo;

    /**
     * Объект DAO доступа к БД
     */
    private FileDAO dao;

    /**
     * Конструктор
     * @param fileFrom - объект файла или папки, откуда выполняется перемещение
     * @param fileTo - объект файла или папки, куда выполняется перемещение
     */
    public DataBaseCommandMove(File fileFrom, File fileTo, FileDAO dao) {
        this.fileFrom = fileFrom;
        this.fileTo = fileTo;
        this.dao = dao;
    }

    /**
     * Валидация команды на стороне базы данных
     */
    public void validate() throws DataBaseCommandException {
        System.out.println("validate mv in DB");

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
     */
    public void execute() throws DataBaseCommandException {
        System.out.println("execute mv in DB");

        // Записываем файлы в БД рекурсивно
        createFilesRec(fileTo);

        // Удаляем старый файл из БД
        dao.delete(fileFrom);
    }

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
