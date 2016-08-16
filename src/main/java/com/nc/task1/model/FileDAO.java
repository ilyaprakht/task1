package com.nc.task1.model;

import java.util.ArrayList;

/**
 * Created by ilpr0816 on 10.08.2016.
 * Интерфейс для реализации DAO-доступа к БД файлов
 */
public interface FileDAO {

    /**
     * Очистка содержимого всей БД
     */
    void truncateAll() throws DataBaseCommandException;

    /**
     * Добавление нового файла в БД
     * @param file - экземпляр класса File
     */
    void create(File file) throws DataBaseCommandException;

    /**
     * Получение файла по id в БД
     * @param id - id из БД
     * @return экземпляр класса File
     */
    File getFile(int id) throws DataBaseCommandException;

    /**
     * Получение файла по названию
     * @param name - абсолютный путь к файлу
     * @return экземпляр класса File
     */
    File getFile(String name) throws DataBaseCommandException;

    /**
     * Проверка, что файл есть в БД
     * @param file - экземпляр класса File
     * @return результат проверки
     */
    boolean existFile(File file) throws DataBaseCommandException;

    /**
     * Удаление файла
     * @param file - экземпляр класса File
     */
    void delete(File file) throws DataBaseCommandException;

    /**
     * Проверяет, есть ли записи в БД
     * @return true, если записей нет, false, если записи есть
     */
    boolean isEmpty() throws DataBaseCommandException;

    /**
     * Получение головного файла
     * @return экземпляр File для головного файла в выборке
     */
    File getHeadFile() throws DataBaseCommandException;

    /**
     * Получение дочерних файлов
     * @param parentFile - экземпляр File родительского файла
     */
    ArrayList<File> getChildFiles(File parentFile) throws DataBaseCommandException;
}
