package com.nc.task1.model;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Интерфейс выполнения действий на стороне файловой системы
 */
public interface FileSystemCommand {
    /**
     * Валидация команды на стороне файловой системы
     */
    public void validate() throws FileSystemCommandException;

    public void validateChild(String path) throws FileSystemCommandException;

    /**
     * Выполнение команды на стороне файловой системы
     */
    public void execute() throws FileSystemCommandException;
}
