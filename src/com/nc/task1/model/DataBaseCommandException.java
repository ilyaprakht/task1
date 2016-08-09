package com.nc.task1.model;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Исключение для действий на стороне БД
 */
public class DataBaseCommandException extends Exception {
    /**
     * Папка из команды
     */
    private Folder folder;

    /**
     * Файл из команды
     */
    private File file;

    /**
     * Конструктор для команды над папкой
     * @param message - сообщение об ошибке
     * @param folder - папка
     */
    DataBaseCommandException(String message, Folder folder) {
        super(message);
        this.folder = folder;
    }

    /**
     * Конструктор для команды над файлом
     * @param message - сообщение об ошибке
     * @param file - файл
     */
    DataBaseCommandException(String message, File file) {
        super(message);
        this.file = file;
    }

    /**
     * Геттер для папки
     * @return папка
     */
    public Folder getFolder() {
        return folder;
    }

    /**
     * Сеттер для папки
     * @param folder - папка
     */
    public void setFolder(Folder folder) {
        this.folder = folder;
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
