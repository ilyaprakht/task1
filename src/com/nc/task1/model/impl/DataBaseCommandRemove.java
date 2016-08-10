package com.nc.task1.model.impl;

import com.nc.task1.model.DataBaseCommand;
import com.nc.task1.model.File;

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
     * Конструктор
     * @param file - объект файла или папки
     */
    public DataBaseCommandRemove(File file) {
        this.file = file;
    }

    /**
     * Валидация команды на стороне базы данных
     */
    public void validate() {
        System.out.println("validate rm in DB");
    }

    /**
     * Выполнение команды на стороне базы данных
     */
    public void execute() {
        System.out.println("execute rm in DB");
    }
}