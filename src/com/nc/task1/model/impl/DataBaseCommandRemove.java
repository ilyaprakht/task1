package com.nc.task1.model.impl;

import com.nc.task1.model.DataBaseCommand;
import com.nc.task1.model.DataBaseCommandException;
import com.nc.task1.model.File;
import com.nc.task1.model.FileDAO;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Реализация интерфейса DataBaseCommand для команды rm
 */
public class DataBaseCommandRemove implements DataBaseCommand {
    /**
     * Объект файла или папки, для которого выполняется удаление
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
    public DataBaseCommandRemove(File file, FileDAO dao) {
        this.file = file;
        this.dao = dao;
    }

    /**
     * Валидация команды на стороне базы данных
     */
    public void validate() throws DataBaseCommandException {
        System.out.println("validate rm in DB");

        // Проверяем, что файл есть в списке сканированных в БД
        if (!dao.existFile(file)) {
            throw new DataBaseCommandException("Выбранный файл не был ранее сканирован", file);
        }
    }

    /**
     * Выполнение команды на стороне базы данных
     */
    public void execute() throws DataBaseCommandException {
        System.out.println("execute rm in DB");

        // Удаляем файл из БД
        dao.delete(file);
    }
}