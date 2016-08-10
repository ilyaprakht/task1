package com.nc.task1.model.impl;

import com.nc.task1.model.DataBaseCommand;
import com.nc.task1.model.File;
import com.nc.task1.model.FileDAO;

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
    private FileDAO fileDAO;

    /**
     * Конструктор
     * @param fileFrom - объект файла или папки, откуда выполняется перемещение
     * @param fileTo - объект файла или папки, куда выполняется перемещение
     */
    public DataBaseCommandMove(File fileFrom, File fileTo, FileDAO fileDAO) {
        this.fileFrom = fileFrom;
        this.fileTo = fileTo;
        this.fileDAO = fileDAO;
    }

    /**
     * Валидация команды на стороне базы данных
     */
    public void validate() {
        System.out.println("validate mv in DB");
    }

    /**
     * Выполнение команды на стороне базы данных
     */
    public void execute() {
        System.out.println("execute mv in DB");
    }
}
