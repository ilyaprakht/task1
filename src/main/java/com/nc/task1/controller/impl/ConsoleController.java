package com.nc.task1.controller.impl;

import com.nc.task1.controller.*;
import com.nc.task1.view.impl.ConsoleView;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Основной контроллер всех действий с консолью
 */
public class ConsoleController extends AbstractController {
    /**
     * Фабричный метод создания конкретных экземпляров представления
     */
    @Override
    protected void initView() {
        // Инициализация представления для работы с консолью
        view = new ConsoleView();
    }

    /**
     * Получение новой команды из представления
     * @return - новая команда
     */
    protected Command getNewCommand() {
        ConsoleCommand command = (ConsoleCommand) view.read();
        command.parseCommandLine();
        return command;
    }
}
