package com.nc.task1.model.impl;

import com.nc.task1.model.DataBaseCommand;
import com.nc.task1.model.File;
import com.nc.task1.model.FileDAO;

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
    private FileDAO fileDAO;

    /**
     * Конструктор
     * @param file - объект файла или папки
     */
    public DataBaseCommandScan(File file, FileDAO fileDAO) {
        this.file = file;
        this.fileDAO = fileDAO;
    }

    /**
     * Валидация команды на стороне базы данных
     */
    public void validate() {
        System.out.println("validate scan in DB");
    }

    /**
     * Выполнение команды на стороне базы данных
     */
    public void execute() {
        System.out.println("execute scan in DB");
    }
}
