package com.nc.task1.model;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Класс файла
 */
public class File {
    /**
     * id записи в БД
     */
    private int id;

    /**
     * Название файла
     */
    private String name;

    /**
     * Родительская папка
     */
    private Folder parentFolder;

    /**
     * Конструктор для файла
     * @param id - id записи в БД
     * @param name - название файла
     * @param parentFolder - родительская папка
     */
    File(int id, String name, Folder parentFolder) {
        this.id = id;
        this.name = name;
        this.parentFolder = parentFolder;
    }

    /**
     * Конструктор для файла, создаваемого при сканировании до записи в БД
     * @param name - название файла
     * @param parentFolder - родительская папка
     */
    File(String name, Folder parentFolder) {
        this.id = 0;
        this.name = name;
        this.parentFolder = parentFolder;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Folder getParentFolder() {
        return parentFolder;
    }

    /**
     *
     * @param parentFolder
     */
    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }
}
