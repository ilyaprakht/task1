package com.nc.task1.controller;

import com.nc.task1.controller.impl.*;
import com.nc.task1.model.DataBaseCommandException;
import com.nc.task1.model.FileSystemCommandException;
import com.nc.task1.view.View;

/**
 * Created by ilpr0816 on 15.08.2016.
 * Абстрактный класс контроллера
 */
public abstract class AbstractController {
    /**
     * Представление
     */
    protected View view;

    /**
     * Фабричный метод создания конкретных экземпляров представления
     */
    protected abstract void initView();

    /**
     * Получение новой команды из представления
     * @return - новая команда
     */
    protected abstract Command getNewCommand();

    /**
     * Основной метод работы контроллера
     */
    public void run() {
        // Инициализируем view
        initView();

        Command command = null;

        // Создаем буфер вывода
        OutDataBuffer.reset();

        do {
            try {
                // Получение новой команды из представления
                command = getNewCommand();

                CommandController controller = null;

                // Определение соответствующего контроллера для команды
                switch (command.getCommandType()) {
                    case Command.COMMAND_SCAN:
                        OutDataBuffer.outData.append("command scan action");
                        controller = new CommandControllerScan(command.getPath1());
                        break;
                    case Command.COMMAND_MOVE:
                        OutDataBuffer.outData.append("command move action");
                        controller = new CommandControllerMove(command.getPath1(), command.getPath2());
                        break;
                    case Command.COMMAND_COPY:
                        OutDataBuffer.outData.append("command copy action");
                        controller = new CommandControllerCopy(command.getPath1(), command.getPath2());
                        break;
                    case Command.COMMAND_REMOVE:
                        OutDataBuffer.outData.append("command remove action");
                        controller = new CommandControllerRemove(command.getPath1());
                        break;
                    case Command.COMMAND_PRINT:
                        OutDataBuffer.outData.append("command print action");
                        controller = new CommandControllerPrint();
                        break;
                    case Command.COMMAND_EXIT:
                        OutDataBuffer.outData.append("exit program");
                        break;
                    default:
                        OutDataBuffer.outData.append("unknown command. please, try again");
                        break;
                }

                // Если контроллер определен, выполняем команду
                if (controller != null) {
                    controller.executeCommand();
                    OutDataBuffer.outData.append("command done");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) { //Некорректный формат команды, указано неверное количество литералов
                OutDataBuffer.outData.append("incorrect command format. please, try again");
            }
            catch (FileSystemCommandException e) { //Ошибка файловой системы
                OutDataBuffer.outData.append("file system error: " + e.getMessage() + " " + e.getPath1() + (e.getPath2() == null ? "" : " " + e.getPath2()));
            }
            catch (DataBaseCommandException e) { // Ошибка БД
                OutDataBuffer.outData.append("database error: " + e.getMessage() + (e.getFile() == null ? "" : " " + e.getFile().getName()));
            }
            catch (Exception e) { // Не ожидаемая ошибка
                OutDataBuffer.outData.append("unknown error. exit program: " + e.getMessage());
                break;
            }

            // Выводим всю скопившуюся информацию в консоль
            view.write(OutDataBuffer.outData);
        } while (command != null && !command.getCommandType().equals(Command.COMMAND_EXIT)); // при вводе exit - выход из программы
    }
}
