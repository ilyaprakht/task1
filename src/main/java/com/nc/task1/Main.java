package com.nc.task1;

import com.nc.task1.controller.AbstractController;
import com.nc.task1.controller.impl.ConsoleController;

public class Main {

    public static void main(String[] args) {
        // Создаем и запускаем контроллер для консоли
        AbstractController controller = new ConsoleController();
        controller.run();
    }
}
