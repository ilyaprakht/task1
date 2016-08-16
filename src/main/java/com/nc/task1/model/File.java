package com.nc.task1.model;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Класс файла или папки
 */
public class File {
    /**
     * id записи в БД
     */
    protected int id;

    /**
     * Название файла
     */
    protected String name;

    /**
     * Родительская папка
     */
    protected File parentFolder;

    /**
     * Конструктор для файла
     * @param id - id записи в БД
     * @param name - название файла
     * @param parentFolder - родительская папка
     */
    public File(int id, String name, File parentFolder) {
        this.id = id;
        this.name = name;
        this.parentFolder = parentFolder;
    }

    /**
     * Конструктор для файла, создаваемого при сканировании до записи в БД
     * @param name - название файла
     * @param parentFolder - родительская папка
     */
    public File(String name, File parentFolder) {
        this.id = 0;
        this.name = name;
        this.parentFolder = parentFolder;
    }

    /**
     * Геттер для id записи в БД
     * @return id записи в БД
     */
    public int getId() {
        return id;
    }

    /**
     * Сеттер для id записи в БД
     * @param id id записи в БД
     */
    private void setId(int id) {
        this.id = id;
    }

    /**
     * Геттер для названия файла
     * @return название файла
     */
    public String getName() {
        return name;
    }

    /**
     * Сеттер для названия файла
     * @param name - название файла
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * Геттер для родительской папки
     * @return родительская папка
     */
    public File getParentFolder() {
        return parentFolder;
    }

    /**
     * Сеттер для родительской папки
     * @param parentFolder - родительская папка
     */
    public void setParentFolder(File parentFolder) {
        this.parentFolder = parentFolder;
    }

    /**
     * Определение пути к родительскому каталогу на основании пути файла
     * @param file - экземпляр файла
     * @return путь к родительскому каталогу
     */
    public static String getParentFolderPath(File file) {
        int pos = file.getName().lastIndexOf("\\"); // для windows-путей
        if (pos == -1) {
            pos = file.getName().lastIndexOf("/"); // для unix-путей
        }
        return file.getName().substring(0, pos);
    }

    /**
     * Возвращает объект файла или папки по пути
     * @param path - путь к файлу
     * @param parentFolder - родительский каталог
     * @return - объект соответствующего файла
     */
    public static File getFileByPath(String path, File parentFolder) {
        java.io.File hFile = new java.io.File(path);
        if (hFile.exists()) { // Проверяем что такой файл есть в ФС
            if (hFile.isDirectory()) { // Если это директория, то рекурсивно пробегаемся по всем ее файлам и поддиректориям
                Folder sFile = new Folder(path, parentFolder);
                for (java.io.File childFile : hFile.listFiles()) { // рекурсивно пробегаемся по всем ее файлам и поддиректориям, добавляем в лист
                    sFile.getListChildFiles().add(getFileByPath(childFile.getAbsolutePath(), sFile));
                }
                return sFile;
            } else {
                File sFile = new File(path, parentFolder);
                return sFile;
            }
        }
        return null; // если файл не найден, возвращаем null - для случаев перемещения и копирования, валидация будет дальше
    }

    /**
     * Меняет пути к файлам для новых файлов по структуре старых
     * @param file - экземпляр объекта файла
     * @param pathFrom - путь файла, откуда копируется
     * @param pathTo - путь файла, куда копируется
     */
    public static void changeFilePathToByPathFrom(File file, String pathFrom, String pathTo, File parentFolder) {
        // Меняем путь к файлу для нового файла в соответствии с путем в старом файле
        file.setName(file.getName().replace(pathFrom, pathTo));
        file.setId(0);
        file.setParentFolder(parentFolder);

        // Если это директория, то пробегаемся рекурсивно по всем ее подпапкам и файлам
        if (file instanceof Folder) {
            for (File childFile : ((Folder) file).getListChildFiles()) {
                changeFilePathToByPathFrom(childFile, pathFrom, pathTo, file);
            }
        }
    }

    /**
     * Переопределенный метод клонирования
     * @return - склонированный объект
     */
    @Override
    public File clone() {
        return new File(name, parentFolder);
    }
}
