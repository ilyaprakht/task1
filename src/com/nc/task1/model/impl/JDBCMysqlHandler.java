package com.nc.task1.model.impl;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.nc.task1.model.DataBaseCommandException;
import com.nc.task1.model.File;
import com.nc.task1.model.FileDAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ilpr0816 on 10.08.2016.
 * Реализация интерфейса FileDAO для подключения к Mysql через jdbc
 */
public class JDBCMysqlHandler implements FileDAO {
    /**
     * url подключения драйвера jdbc
     */
    private final String url = "jdbc:mysql://localhost:3306/task1";

    /**
     * Логин доступа к БД
     */
    private final String user = "root";

    /**
     * Пароль доступа к БД
     */
    private final String password = "123456";

    /**
     * Таймаут проверки валидности подключения
     */
    private final int timeout = 10;

    /**
     * Экземпляр подключения
     */
    private Connection connection;

    /**
     * Возвращает statement, делает реконнект, если коннекш потерян
     * @return statement
     * @throws SQLException
     */
    private Statement getStatement() throws SQLException {
        // Проверяем, что коннекш активен
        if (connection == null || !connection.isValid(timeout)) {
            connection = (Connection) DriverManager.getConnection(url, user, password);
        }
        // Возвращаем действующий statement
        return (Statement) connection.createStatement();
    }

    /**
     * Выполняет запрос, пордразумевающий возвращаемое значение
     * @param query - запрос
     * @return - значение в виде ResultSet
     * @throws DataBaseCommandException
     */
    private ResultSet executeQuery(String query) throws DataBaseCommandException {
        ResultSet resultSet = null;
        try {
            resultSet = getStatement().executeQuery(query);
        }
        catch (SQLException e) {
            throw new DataBaseCommandException(e.getMessage(), null);
        }
        return resultSet;
    }

    /**
     * Выполняет запрос, не подразумевающий возвращаемое значение
     * @param query - запрос
     * @throws DataBaseCommandException
     */
    private void execute(String query) throws DataBaseCommandException {
        try {
            getStatement().execute(query);
        }
        catch (SQLException e) {
            throw new DataBaseCommandException(e.getMessage(), null);
        }
    }

    /**
     * Очистка содержимого всей БД
     */
    @Override
    public void truncateAll() throws DataBaseCommandException {
        // Очищаем таблицу t_folder
        String query = "delete from t_folder";
        execute(query);

        // Очищаем таблицу t_file
        query = "delete from t_file";
        execute(query);
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
