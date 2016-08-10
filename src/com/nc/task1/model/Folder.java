package com.nc.task1.model;

import java.util.ArrayList;

/**
 * Created by ilpr0816 on 10.08.2016.
 */
public class Folder extends File {
    /**
     * Список дочерних файлов
     */
    protected ArrayList<File> listChildFiles;

    /**
     * Конструктор для папки
     * @param id - id записи в БД
     * @param name - название файла
     * @param parentFolder - родительская папка
     */
    Folder(int id, String name, File parentFolder) {
        super(id, name, parentFolder);
        listChildFiles = new ArrayList<File>();
    }

    /**
     * Конструктор для папки, создаваемых при сканировании до записи в БД
     * @param name - название файла
     * @param parentFolder - родительская папка
     */
    public Folder(String name, File parentFolder) {
        super(name, parentFolder);
        listChildFiles = new ArrayList<File>();
    }

    /**
     * Геттер для списка дочерних файлов
     * @return список дочерних файлов
     */
    public ArrayList<File> getListChildFiles() {
        return listChildFiles;
    }

    /**
     * Сеттер для списка дочерних файлов
     * @param listChildFiles - список дочерних файлов
     */
    public void setListChildFiles(ArrayList<File> listChildFiles) {
        this.listChildFiles = listChildFiles;
    }
}
