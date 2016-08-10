package com.nc.task1;

import com.nc.task1.controller.ConsoleController;

public class Main {

    public static void main(String[] args) {
        // Создаем и запускаем контроллер для консоли
        ConsoleController controller = new ConsoleController();
        controller.run();
    }
}
