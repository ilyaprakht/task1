package com.nc.task1.model;

import java.util.ArrayList;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Класс файла или папки
 */
public class File {
    /**
     * id записи в БД
     */
    protected int id;

    /**
     * Название файла
     */
    protected String name;

    /**
     * Родительская папка
     */
    protected File parentFolder;

    /**
     * Конструктор для файла
     * @param id - id записи в БД
     * @param name - название файла
     * @param parentFolder - родительская папка
     */
    File(int id, String name, File parentFolder) {
        this.id = id;
        this.name = name;
        this.parentFolder = parentFolder;
    }

    /**
     * Конструктор для файла, создаваемого при сканировании до записи в БД
     * @param name - название файла
     * @param parentFolder - родительская папка
     */
    public File(String name, File parentFolder) {
        this.id = 0;
        this.name = name;
        this.parentFolder = parentFolder;
    }

    /**
     * Геттер для id записи в БД
     * @return id записи в БД
     */
    public int getId() {
        return id;
    }

    /**
     * Сеттер для id записи в БД
     * @param id id записи в БД
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Геттер для названия файла
     * @return название файла
     */
    public String getName() {
        return name;
    }

    /**
     * Сеттер для названия файла
     * @param name - название файла
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Геттер для родительской папки
     * @return родительская папка
     */
    public File getParentFolder() {
        return parentFolder;
    }

    /**
     * Сеттер для родительской папки
     * @param parentFolder - родительская папка
     */
    public void setParentFolder(File parentFolder) {
        this.parentFolder = parentFolder;
    }
}
