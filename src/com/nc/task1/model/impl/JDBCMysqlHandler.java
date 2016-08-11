package com.nc.task1.model.impl;

import com.nc.task1.model.File;
import com.nc.task1.model.FileDAO;

/**
 * Created by ilpr0816 on 10.08.2016.
 * Реализация интерфейса FileDAO для подключения к Mysql через jdbc
 */
public class JDBCMysqlHandler implements FileDAO {


    /**
     * Очистка содержимого всей БД
     */
    @Override
    public void truncateAll() {

    }

    /**
     * Обновление атрибутов файла или папки
     * @param file - экземпляр класса File
     * @return обновленный объект файла
     */
    @Override
    public void update(File file) {

    }

    /**
     * Добавление нового файла в БД
     * @param file - экземпляр класса File
     * @return обновленный объект файла
     */
    @Override
    public void create(File file) {

    }

    /**
     * Получение файла по id в БД
     * @param id - id из БД
     * @return экземпляр класса File
     */
    @Override
    public File getFile(int id) {
        return null;
    }

    /**
     * Получение файла по названию
     * @param name - абсолютный путь к файлу
     * @return экземпляр класса File
     */
    @Override
    public File getFile(String name) {
        return null;
    }

    /**
     * Проверка, что файл есть в БД
     * @param file - экземпляр класса File
     * @return результат проверки
     */
    public boolean existFile(File file) {
        return true;
    }

    /**
     * Удаление файла
     * @param file - экземпляр класса File
     */
    @Override
    public void delete(File file) {

    }

    /**
     * Получение головного в иерархии файла
     * @return экземпляр класса File
     */
    @Override
    public File getHeadFile() {
        return null;
    }
}
