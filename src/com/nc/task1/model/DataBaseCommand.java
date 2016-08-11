package com.nc.task1.model;

import java.sql.SQLException;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Интерфейс выполнения действий на стороне базы данных
 */
public interface DataBaseCommand {
    /**
     * Валидация команды на стороне базы данных
     */
    public void validate() throws DataBaseCommandException;

    /**
     * Выполнение команды на стороне базы данных
     */
    public void execute() throws DataBaseCommandException;
}
