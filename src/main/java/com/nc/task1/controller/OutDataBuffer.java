package com.nc.task1.controller;

/**
 * Created by ilpr0816 on 15.08.2016.
 * Класс для агрегации буфера вывода
 */
public class OutDataBuffer {
    /**
     * Сборник данных на вывод
     */
    public static OutData outData;

    /**
     * Сброс и обновление буфера данных на вывод
     */
    static void reset() {
        if (outData == null) { // Если буфер не создан, то создаем его
            outData = new OutData();
        } else { // Если буфер уже создан, то очищаем его
            outData.clear();
        }
    }
}
