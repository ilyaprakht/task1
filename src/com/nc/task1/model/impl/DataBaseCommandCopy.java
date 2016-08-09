package com.nc.task1.model.impl;

import com.nc.task1.model.DataBaseCommand;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Реализация интерфейса DataBaseCommand для команды cp
 */
public class DataBaseCommandCopy implements DataBaseCommand {

    /**
     * Валидация команды на стороне базы данных
     */
    public void validate() {
        System.out.println("validate cp in DB");
    }

    /**
     * Выполнение команды на стороне базы данных
     */
    public void execute() {
        System.out.println("execute cp in DB");
    }
}
