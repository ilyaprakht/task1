package com.nc.task1.model.impl;

import com.nc.task1.model.*;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Реализация интерфейса DataBaseCommand для команды scan
 */
public class DataBaseCommandScan implements DataBaseCommand {
    /**
     * Объект файла или папки, для которого выполняется сканирование
     */
    private File file;

    /**
     * Объект DAO доступа к БД
     */
    private FileDAO dao;

    /**
     * Конструктор
     * @param file - объект файла или папки
     */
    public DataBaseCommandScan(File file, FileDAO dao) {
        this.file = file;
        this.dao = dao;
    }

    /**
     * Валидация команды на стороне базы данных
     */
    public void validate() throws DataBaseCommandException {
        System.out.println("validate scan in DB");
        // В текущей реализации никакая валидация на стороне БД при сканировании не выполняется
    }

    /**
     * Выполнение команды на стороне базы данных
     */
    public void execute() {
        System.out.println("execute scan in DB");

        // Очищаем текущую БД
        dao.truncateAll();

        // Записываем файлы в БД рекурсивно
        createFilesRec(file);
    }

    private void createFilesRec(File file) {
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
