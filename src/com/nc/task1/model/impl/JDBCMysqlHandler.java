package com.nc.task1.model.impl;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.nc.task1.model.DataBaseCommandException;
import com.nc.task1.model.File;
import com.nc.task1.model.FileDAO;
import com.nc.task1.model.Folder;

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
     * Заменяет слеш на двойной слеш, так как в mysql это символ экранирования и при вставке одиночный слеш игнорируется
     * @param s - входный строка
     * @return - строка с заменой
     */
    private String replaceS(String s) {
        return s.replace("\\", "\\\\");
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
     * Добавление нового файла в БД
     * @param file - экземпляр класса File
     * @return обновленный объект файла
     */
    @Override
    public void create(File file) throws DataBaseCommandException {
        // Проверяем, есть ли данный файл уже в БД
        if (existFile(file)) { // Если файл уже есть, то новый не добавляем
            return;
        }

        // Определяем родительский каталог
        File parentFile = file.getParentFolder();

        if (file instanceof Folder) { // Если файл является папкой
            // Делаем вставку в таблицу t_folder
            String query = "insert into t_folder (name" + (parentFile != null ? ", link_folder": "") + ") values ('"
                    + replaceS(file.getName()) + "'" + (parentFile != null ? ", (select t.id from t_folder t where t.name = '" + replaceS(parentFile.getName()) + "')" : "") + ")";
            execute(query);
        } else { // Если файл не является папкой
            // Делаем вставку в таблицу t_file
            String query = "insert into t_file (name" + (parentFile != null ? ", link_folder": "") + ") values ('"
                    + replaceS(file.getName()) + "'" + (parentFile != null ? ", (select t.id from t_folder t where t.name = '" + replaceS(parentFile.getName()) + "')" : "") + ")";
            execute(query);
        }
    }

    /**
     * Получение файла по id в БД
     * @param id - id из БД
     * @return экземпляр класса File
     */
    @Override
    public File getFile(int id) throws DataBaseCommandException {
        // Ищем файл в таблице t_file
        // Формируем запрос
        String query = "select f.id, f.name, f.link_folder from t_file f where f.id = " + id;

        // Выполняем запрос
        ResultSet resultSet = executeQuery(query);

        // Проверяем, есть ли в запросе результат
        try {
            if (resultSet.next()) {
                return new File(resultSet.getInt("id"), resultSet.getString("name"), getFile(resultSet.getInt("link_folder")));
            }
        }
        catch (SQLException e) {
            throw new DataBaseCommandException(e.getMessage(), null);
        }

        // Ищем файл в таблице t_folder
        // Формируем запрос
        query = "select f.id, f.name, f.link_folder from t_folder f where f.id = " + id;

        // Выполняем запрос
        resultSet = executeQuery(query);

        // Проверяем, есть ли в запросе результат
        try {
            if (resultSet.next()) {
                return new Folder(resultSet.getInt("id"), resultSet.getString("name"), getFile(resultSet.getInt("link_folder")));
            }
        }
        catch (SQLException e) {
            throw new DataBaseCommandException(e.getMessage(), null);
        }

        // Если файл не найден возвращаем null
        return null;
    }

    /**
     * Получение файла по названию
     * @param name - абсолютный путь к файлу
     * @return экземпляр класса File
     */
    @Override
    public File getFile(String name) throws DataBaseCommandException {
        // Ищем файл в таблице t_file
        // Формируем запрос
        String query = "select f.id, f.name, f.link_folder from t_file f where f.name = '" + replaceS(name) + "'";

        // Выполняем запрос
        ResultSet resultSet = executeQuery(query);

        // Проверяем, есть ли в запросе результат
        try {
            if (resultSet.next()) {
                return new File(resultSet.getInt("id"), resultSet.getString("name"), getFile(resultSet.getInt("link_folder")));
            }
        }
        catch (SQLException e) {
            throw new DataBaseCommandException(e.getMessage(), null);
        }

        // Ищем файл в таблице t_folder
        // Формируем запрос
        query = "select f.id, f.name, f.link_folder from t_folder f where f.name = '" + replaceS(name) + "'";

        // Выполняем запрос
        resultSet = executeQuery(query);

        // Проверяем, есть ли в запросе результат
        try {
            if (resultSet.next()) {
                return new Folder(resultSet.getInt("id"), resultSet.getString("name"), getFile(resultSet.getInt("link_folder")));
            }
        }
        catch (SQLException e) {
            throw new DataBaseCommandException(e.getMessage(), null);
        }

        // Если файл не найден возвращаем null
        return null;
    }

    /**
     * Проверка, что файл есть в БД
     * @param file - экземпляр класса File
     * @return результат проверки
     */
    public boolean existFile(File file) throws DataBaseCommandException {
        // Формируем запрос в зависимости от типа файла
        String query;
        if (file instanceof Folder) { // Если файл является папкой
            query = "select t.id from t_folder t where t.name = '" + replaceS(file.getName()) + "'";
        } else { // Если файл не является папкой
            query = "select t.id from t_file t where t.name = '" + replaceS(file.getName()) + "'";
        }

        // Выполняем запрос
        ResultSet resultSet = executeQuery(query);
        // Проверяем, есть ли в запросе результат
        try {
            return resultSet.next();
        }
        catch (SQLException e) {
            throw new DataBaseCommandException(e.getMessage(), null);
        }
    }

    /**
     * Удаление файла
     * @param file - экземпляр класса File
     */
    @Override
    public void delete(File file) throws DataBaseCommandException {
        // Формируем запрос в зависимости от типа файла
        String query;
        if (file instanceof Folder) { // Если файл является папкой
            query = "delete from t_folder where name = '" + replaceS(file.getName()) + "'";
        } else { // Если файл не является папкой
            query = "delete from t_file where name = '" + replaceS(file.getName()) + "'";
        }

        // Выполняем единственный запрос, поскольку в БД настроено каскадное удаление
        execute(query);
    }
}
