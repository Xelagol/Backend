import java.util.ArrayList;
import java.util.Arrays;

public class TodoList {
    private String command;
    private int num;
    private String todo;
    private ArrayList<String> todoList;

    public TodoList() {

    }




    public void setCommand(String command) {
        this.command = command;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public void setTodo(String todo) {
        this.todo = todo;
    }

    public void generateTodoList() {
        this.todoList = new ArrayList<>();
    }
    public int getSizeTodoList() {
        return todoList.size();
    }

    public void changeTodoList() {


        switch (command) {

            case "EDIT" :
                todoList.set(num, todo);
                System.out.println(todo + " изменено");
                break;
            case "LIST" :
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println(i + " " + todoList.get(i));
                }
                break;
            case "DELETE" :
                System.out.println( num + " " + todoList.get(num) + " удалено");
                todoList.remove(num);
                break;
            case "ADD" :
                if (num == 0) {
                    todoList.add(todo);
                } else {
                    todoList.add(num, todo);
                }
                System.out.println(todo + " добавлено");
                break;

        }


    }
//    public String toString() {
//        generateTodoList();
//        return "";
//    }
}