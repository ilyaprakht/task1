package com.nc.task1.model;

import java.util.ArrayList;

/**
 * Created by ilpr0816 on 10.08.2016.
 * Расширение класса File для папок
 */
public class Folder extends File {
    /**
     * Список дочерних файлов
     */
    private ArrayList<File> listChildFiles;

    /**
     * Конструктор для папки
     * @param id - id записи в БД
     * @param name - название файла
     * @param parentFolder - родительская папка
     */
    public Folder(int id, String name, File parentFolder) {
        super(id, name, parentFolder);
        listChildFiles = new ArrayList<>();
    }

    /**
     * Конструктор для папки, создаваемых при сканировании до записи в БД
     * @param name - название файла
     * @param parentFolder - родительская папка
     */
    Folder(String name, File parentFolder) {
        super(name, parentFolder);
        listChildFiles = new ArrayList<>();
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

    /**
     * Переопределенный метод клонирования
     * @return - склонированный объект
     */
    @Override
    public Folder clone() {
        Folder newFolder =  new Folder(name, parentFolder);
        newFolder.setListChildFiles(listChildFiles);
        return newFolder;
    }
}
