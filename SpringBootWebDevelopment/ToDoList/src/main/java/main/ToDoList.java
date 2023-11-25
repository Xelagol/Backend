package main;


import main.model.ToDo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToDoList {
    private static Map<Integer, ToDo> toDos = new HashMap<>();
    private static int idToDo = 1;

    public synchronized static int addToDo(ToDo toDo) {
        int idToDo = ToDoList.idToDo++;

        toDos.put(idToDo, toDo);
        return idToDo;
    }

    public static Map<Integer, ToDo> getToDoList() {
        List<ToDo> toDosList = new ArrayList<>();
        toDosList.addAll(toDos.values());
        return toDos;
    }
    public static ToDo getTodo(int id) {
        if (toDos.containsKey(id)) {
            return toDos.get(id);
        }
        return null;
    }
    public static String deleteTodo(int id) {
        if (toDos.containsKey(id)) {
            toDos.remove(id);
            return "delete successfully";
        }
        return null;
    }
    public static String changeToDo(int id, int newId, ToDo toDo) {
        if (toDos.containsKey(id)) {
            toDos.put(newId, toDo);
            toDos.remove(id);
            return "change successfully";
        }

        return "change unsuccessfully";
    }

    public static String deleteAllTodo() {
        if (!toDos.isEmpty()) {
            toDos.clear();
            idToDo = 1;
            return "DB cleaned successfully";
        }
        return null;
    }

    public static String changeAllToDo() {
        if (!toDos.isEmpty()) {
            toDos.putAll(toDos);
            return "DB resave successfully";
        }
        return "DB is empty";
    }
}
