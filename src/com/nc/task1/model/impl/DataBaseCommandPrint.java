package com.nc.task1.model.impl;

import com.nc.task1.controller.OutDataBuffer;
import com.nc.task1.model.*;

/**
 * Created by ilpr0816 on 15.08.2016.
 * Реализация интерфейса DataBaseCommand для команды print
 */
public class DataBaseCommandPrint implements DataBaseCommand {
    /**
     * Объект DAO доступа к БД
     */
    private FileDAO dao;

    /**
     * Конструктор
     */
    public DataBaseCommandPrint(FileDAO dao) {
        this.dao = dao;
    }

    /**
     * Валидация команды на стороне базы данных
     * @throws DataBaseCommandException
     */
    @Override
    public void validate() throws DataBaseCommandException {
        OutDataBuffer.outData.append("validate scan in DB");

        // Проверяем, что в БД есть записи
        if (dao.isEmpty()) {
            throw new DataBaseCommandException("В БД нет данных. Проведите сканирование", null);
        }
    }

    /**
     * Выполнение команды на стороне базы данных
     * @throws DataBaseCommandException
     */
    @Override
    public void execute() throws DataBaseCommandException {
        OutDataBuffer.outData.append("execute scan in DB");

        // Опрделеяем головной файл в выборке
        File headFile = dao.getHeadFile();

        // Проверяем, что полученный файл не null, иначе модель данных не корректна
        if (headFile == null) {
            throw new DataBaseCommandException("Некорректная модель данных в БД", null);
        }

        // Если файл является папкой, то ищем все подпапки и вложенные файлы
        if (headFile instanceof Folder) {
            // TODO заполнение данных - рекурсивная функция
        }

        // TODO вывод данных - рекурсивная функция
    }
}
