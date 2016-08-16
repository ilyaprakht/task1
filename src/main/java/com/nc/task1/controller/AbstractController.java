package com.nc.task1.controller;

import com.nc.task1.view.View;

/**
 * Created by ilpr0816 on 15.08.2016.
 */
public abstract class AbstractController {
    /**
     * Представление
     */
    protected View view;

    /**
     * Фабричный метод создания конкретных экземпляров представления
     */
    public abstract void initView();

    /**
     * Основной метод работы контроллера
     */
    public abstract void run();
}
