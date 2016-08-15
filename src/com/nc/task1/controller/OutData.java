package com.nc.task1.controller;

import java.util.ArrayList;

/**
 * Created by ilpr0816 on 15.08.2016.
 * Класс, инкапсулирующий выводимые данные
 */
public class OutData {

    /**
     * Массив строк с данными
     */
    private ArrayList<String> data;

    /**
     * Конструктор
     */
    public OutData() {
        data = new ArrayList<String>();
    }

    /**
     * Добавление новой строки в список
     * @param s - новая строка
     */
    public void append(String s) {
        data.add(s);
    }

    /**
     * Очистка массива строк
     */
    public void clear() {
        data.clear();
    }

    /**
     * Геттер для массива строк
     * @return массив строк с данными
     */
    public ArrayList<String> getData() {
        return data;
    }
}
