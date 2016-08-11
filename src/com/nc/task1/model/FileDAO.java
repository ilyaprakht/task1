package com.nc.task1.model;

import java.sql.SQLException;

/**
 * Created by ilpr0816 on 10.08.2016.
 * Интерфейс для реализации DAO-доступа к БД файлов
 */
public interface FileDAO {

    /**
     * Очистка содержимого всей БД
     */
    public void truncateAll() throws DataBaseCommandException;

    /**
     * Добавление нового файла в БД
     * @param file - экземпляр класса File
     * @return обновленный объект файла
     */
    public void create(File file) throws DataBaseCommandException;

    /**
     * Получение файла по id в БД
     * @param id - id из БД
     * @return экземпляр класса File
     */
    public File getFile(int id) throws DataBaseCommandException;

    /**
     * Получение файла по названию
     * @param name - абсолютный путь к файлу
     * @return экземпляр класса File
     */
    public File getFile(String name) throws DataBaseCommandException;

    /**
     * Проверка, что файл есть в БД
     * @param file - экземпляр класса File
     * @return результат проверки
     */
    public boolean existFile(File file) throws DataBaseCommandException;

    /**
     * Удаление файла
     * @param file - экземпляр класса File
     */
    public void delete(File file) throws DataBaseCommandException;
}
