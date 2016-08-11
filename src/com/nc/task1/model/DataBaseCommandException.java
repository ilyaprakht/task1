package com.nc.task1.model;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Исключение для действий на стороне БД
 */
public class DataBaseCommandException extends Exception {
    /**
     * Файл из команды
     */
    private File file;

    /**
     * Конструктор для команды над файлом
     * @param message - сообщение об ошибке
     * @param file - файл
     */
    public DataBaseCommandException(String message, File file) {
        super(message);
        this.file = file;
    }

    /**
     * Геттер для файла
     * @return файл
     */
    public File getFile() {
        return file;
    }

    /**
     * Сеттер для файла
     * @param file - файл
     */
    public void setFile(File file) {
        this.file = file;
    }
}
