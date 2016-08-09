package com.nc.task1.model;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Класс папки с файлами
 */
public class Folder {

    /**
     * id записи в БД
     */
    private int id;

    /**
     * Название папки
     */
    private String name;

    /**
     * Родительская папка
     */
    private Folder parentFolder;

    /**
     * Конструктор для папки, у которой нет родительских папок
     * @param id - id записи в БД
     * @param name - Название папки
     */
    Folder(int id, String name) {
        this.id = id;
        this.name = name;
        this.parentFolder = null;
    }

    /**
     * Конструктор для папки, у которой есть родительская папка
     * @param id - id записи в БД
     * @param name - название папки
     * @param parentFolder - родительская папка
     */
    Folder(int id, String name, Folder parentFolder) {
        this.id = id;
        this.name = name;
        this.parentFolder = parentFolder;
    }

    /**
     * Конструктор для папки, у которой нет родительских папок, создаваемой при сканировании до записи в БД
     * @param name - название папки
     */
    Folder(String name) {
        this.id = 0;
        this.name = name;
        this.parentFolder = null;
    }

    /**
     * Конструктор для папки, у которой есть родительская папка, создаваемой при сканировании до записи в БД
     * @param name
     * @param parentFolder
     */
    Folder(String name, Folder parentFolder) {
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
     * @param id - id записи в БД
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Геттер для названия папки
     * @return название папки
     */
    public String getName() {
        return name;
    }

    /**
     * Сеттер для названия папки
     * @param name - название папки
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Геттер для родительской папки
     * @return родительская папка
     */
    public Folder getParentFolder() {
        return parentFolder;
    }

    /**
     * Сеттер для родительской папки
     * @param parentFolder - родительская папка
     */
    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }
}
