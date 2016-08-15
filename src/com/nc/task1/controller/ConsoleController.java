package com.nc.task1.controller;

import com.nc.task1.controller.impl.*;
import com.nc.task1.model.DataBaseCommandException;
import com.nc.task1.model.FileSystemCommandException;
import com.nc.task1.view.impl.ConsoleView;

import java.util.Scanner;

/**
 * Created by ilpr0816 on 09.08.2016.
 * Основной контроллер всех действий с консолью
 */
public class ConsoleController extends AbstractController {
    /**
     * Фабричный метод создания конкретных экземпляров представления
     */
    @Override
    public void initView() {
        // Инициализация представления для работы с консолью
        view = new ConsoleView();
    }

    /**
     * Основной метод работы контроллера
     */
    @Override
    public void run() {
        ConsoleCommand command = null;
        OutData outData = new OutData();

        do {
            try {
                // Считывание ввода с консоли, распарсивание входной строки
                command = (ConsoleCommand) view.read();
                command.parseCommandLine();

                CommandController controller = null;

                // Определение соответствующего контроллера для команды
                switch (command.getCommandType()) {
                    case Command.COMMAND_SCAN:
                        outData.append("command scan action");
                        controller = new CommandControllerScan(command.getPath1());
                        break;
                    case Command.COMMAND_MOVE:
                        outData.append("command move action");
                        controller = new CommandControllerMove(command.getPath1(), command.getPath2());
                        break;
                    case Command.COMMAND_COPY:
                        outData.append("command copy action");
                        controller = new CommandControllerCopy(command.getPath1(), command.getPath2());
                        break;
                    case Command.COMMAND_REMOVE:
                        outData.append("command remove action");
                        controller = new CommandControllerRemove(command.getPath1());
                        break;
                    case Command.COMMAND_PRINT:
                        outData.append("command print action");
                        break;
                    case Command.COMMAND_EXIT:
                        outData.append("exit program");
                        break;
                    default:
                        outData.append("unknown command. please, try again");
                        break;
                }

                // Если контроллер определен, выполняем команду
                if (controller != null) {
                    controller.executeCommand();
                    outData.append("command done");
                }

                // Выводим всю скопившуюся информацию в консоль
                view.write(outData);
            }
            catch (ArrayIndexOutOfBoundsException e) { //Некорректный формат команды, указано неверное количество литералов
                outData.append("incorrect command format. please, try again");
            }
            catch (FileSystemCommandException e) { //Ошибка файловой системы
                outData.append("file system error: " + e.getMessage() + " " + e.getPath1() + (e.getPath2() == null ? "" : " " + e.getPath2()));
            }
            catch (DataBaseCommandException e) { // Ошибка БД
                outData.append("database error: " + e.getMessage() + (e.getFile() == null ? "" : " " + e.getFile().getName()));
            }
            catch (Exception e) { // Не ожидаемая ошибка
                outData.append("unknown error. exit program: " + e.getMessage());
                break;
            }
        } while (!command.getCommandType().equals(Command.COMMAND_EXIT)); // при вводе exit - выход из программы
    }
}
