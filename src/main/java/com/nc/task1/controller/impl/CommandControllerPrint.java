package com.nc.task1.controller.impl;

import com.nc.task1.controller.CommandController;
import com.nc.task1.model.impl.DataBaseCommandPrint;

/**
 * Created by ilpr0816 on 15.08.2016.
 * Реализация класса контроллера для комнады print
 */
public class CommandControllerPrint extends CommandController {
    /**
     * Конструктор
     */
    public CommandControllerPrint() {
    }

    /**
     * Фабричный метод создания экземпляров модели, выполняющих команду scan
     */
    @Override
    protected void FactoryMethodInitCommands() {
        // Создаем обработчик команды в ФС
        dataBaseCommand = new DataBaseCommandPrint(dao);
    }

    /**
     * Выполнение команды на стороне файловой системы
     */
    @Override
    protected void executeFileSystemCommand() {
        // Действия не предусмотрены, поэтому перегруаем метод
    }
}
