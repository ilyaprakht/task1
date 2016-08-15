package com.nc.task1.view;

import com.nc.task1.controller.Command;
import com.nc.task1.controller.OutData;

import java.util.ArrayList;

/**
 * Created by ilpr0816 on 15.08.2016.
 * Интерфейс для уровня view
 */
public interface View {

    /**
     * Записать данные во view
     * @param data - данные на вывод
     */
    public void write(OutData data);

    /**
     * Получить данные из view
     * @return - команда для обработки
     */
    public Command read();
}
