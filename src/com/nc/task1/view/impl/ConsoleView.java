package com.nc.task1.view.impl;

import com.nc.task1.controller.Command;
import com.nc.task1.controller.OutData;
import com.nc.task1.controller.impl.ConsoleCommand;
import com.nc.task1.view.View;

import java.util.Scanner;

/**
 * Created by ilpr0816 on 15.08.2016.
 * Реализация интерфейса View для консоли
 */
public class ConsoleView implements View {
    /**
     * Записать данные во view
     * @param data - данные на вывод
     */
    @Override
    public void write(OutData data) {
        // Выводим все строки из массива
        for (String s : data.getData()) {
            System.out.println(s);
        }
        // Выводим пустую строку для разделения команд
        System.out.println();
    }

    /**
     * Получить данные из view
     * @return - команда для обработки
     */
    @Override
    public Command read() {
        // Получаем входящую строку из консоли
        Scanner scanner = new Scanner(System.in);
        String commandLine = scanner.nextLine();

        // Возвращаем команду
        return new ConsoleCommand(commandLine);
    }
}
