package com.nc.task1.model;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Исключение для действий на стороне файловой системы
 */
public class FileSystemCommandException extends Exception {
    /**
     * Путь к файлу 1 из команды
     */
    private String path1;

    /**
     * Путь к файлу 2 из команды
     */
    private String path2;

    /**
     * Конструктор для команд с указанием пути к 1 файлу или каталогу
     * @param message - сообщение об ошибке
     * @param path - путь к файлу или каталогу
     */
    public FileSystemCommandException(String message, String path) {
        super(message);
        this.path1 = path;
    }

    /**
     * Конструктор для команд с указанием путей к 2 файлам или каталогам
     * @param message - сообщение об ошибке
     * @param path1 - путь 1 к файлу или каталогу
     * @param path2 - путь 2 к файлу или каталогу
     */
    public FileSystemCommandException(String message, String path1, String path2) {
        super(message);
        this.path1 = path1;
        this.path2 = path2;
    }

    /**
     * Геттер для пути 1
     * @return путь 1 к файлу или каталогу
     */
    public String getPath1() {
        return path1;
    }

    /**
     * Сеттер для пути 1
     * @param path1 - путь 1 к файлу или каталогу
     */
    public void setPath1(String path1) {
        this.path1 = path1;
    }

    /**
     * Геттер для пути 2
     * @return путь 2 к файлу или каталогу
     */
    public String getPath2() {
        return path2;
    }

    /**
     * Сеттер для пути 2
     * @param path2 - путь 2 к файлу или каталогу
     */
    public void setPath2(String path2) {
        this.path2 = path2;
    }
}
