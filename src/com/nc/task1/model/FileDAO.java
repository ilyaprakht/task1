package com.nc.task1.model;

/**
 * Created by ilpr0816 on 10.08.2016.
 * Интерфейс для реализации DAO-доступа к БД файлов
 */
public interface FileDAO {

    /**
     * Очистка содержимого всей БД
     */
    public void truncateAll();

    /**
     * Обновление атрибутов файла или папки
     * @param file - экземпляр класса File
     * @return обновленный объект файла
     */
    public void update(File file);

    /**
     * Добавление нового файла в БД
     * @param file - экземпляр класса File
     * @return обновленный объект файла
     */
    public void create(File file);

    /**
     * Получение файла по id в БД
     * @param id - id из БД
     * @return экземпляр класса File
     */
    public File getFile(int id);

    /**
     * Получение файла по названию
     * @param name - абсолютный путь к файлу
     * @return экземпляр класса File
     */
    public File getFile(String name);

    /**
     * Проверка, что файл есть в БД
     * @param file - экземпляр класса File
     * @return результат проверки
     */
    public boolean existFile(File file);

    /**
     * Удаление файла
     * @param file - экземпляр класса File
     */
    public void delete(File file);

    /**
     * Получение головного в иерархии файла
     * @return экземпляр класса File
     */
    public File getHeadFile();
}
