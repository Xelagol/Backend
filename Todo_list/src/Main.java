import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String commandTodo;
        String command;
        int num;
        int count = 0;
//        int word1;
        boolean word1Num = true;
        String todo = "";
        String text = "";
        int start = 0;
        boolean error;

        System.out.println("Введите, что необходимо сделать с списоком дел:\n" +
                "\tADD — добавляет дело в конец списка или дело на определённое место,\n" +
                "сдвигая остальные дела вперёд, если указать номер;\n" +
                "если указан несуществующий индекс - добавить в конец списка.\n " +
                "\tEDIT — заменяет дело с указанным номером;\n" +
                "если указан несуществующий индекс - ничего не делать.\n" +
                "\tLIST — выводит список дел с их порядковыми номерами;\n" +
                "\tDELETE — удаляет дело из списка.\n" +
                "Для завершения введите STOP.");
        TodoList todoList = new TodoList();
        todoList.generateTodoList();


        while (true) {
            error = false;
            commandTodo = new Scanner(System.in).nextLine();
            if (commandTodo.equals("STOP")) {
                break;
            }
            String regex = "[a-z]+";
            String[] words = commandTodo.split(" ");
            if (words.length <=1) {
                words = Arrays.copyOf(words, 2);
                words[1] = "";
            }

            switch (words[0]) {
                case "ADD": {
                    if (words.length <= 2 &&
                            (words[1].isEmpty() || words[1].matches("[0-9]+"))) {
                        System.out.println("Не корректная команда");
                        continue;
                    }
                    todoList.setCommand("ADD");
                    break;
                }
                case "EDIT":
                    if (words.length <= 2 && (words[1].isEmpty() ||
                            Integer.parseInt(words[1].trim()) > todoList.getSizeTodoList())) {
                        System.out.println("Не корректная команда");
                        continue;
                    }
                    todoList.setCommand("EDIT");
                    break;
                case "LIST":
                    todoList.setCommand("LIST");
                    break;
                case "DELETE":
                    if (words.length <= 2 && (words[1].isEmpty() ||
                            Integer.parseInt(words[1].trim()) > todoList.getSizeTodoList()) ) {
                        System.out.println("Не корректная команда");
                        continue;
                    }
                    todoList.setCommand("DELETE");
                    break;
                default:
                    System.out.println("Не корректная команда");
                    continue;
            }


            num = todoList.getSizeTodoList();

            try {
                num = Integer.parseInt(words[1].trim());
                if (num > todoList.getSizeTodoList()) {
                    num = todoList.getSizeTodoList();
                }
            } catch (NumberFormatException nfe) {
                word1Num = false;
            }

            if (word1Num) {
                todoList.setNum(num);
                for (int i = 2; i < words.length; i++) {
                    text = text + words[i] + " ";
                }
                todoList.setTodo(text.trim());
            } else {
                todoList.setNum(num);
                for (int i = 1; i < words.length; i++) {
                    text = text + words[i] + " ";
                }
                todoList.setTodo(text.trim());
            }
            todoList.changeTodoList();
//            Pattern pattern = Pattern.compile(regex);
//            Matcher matcher = pattern.matcher(commandTodo);
//            while (matcher.find()) {
//                start = matcher.start();
//                int end = matcher.end();
//            }


//            todoList.setNum(num);

//            ADD learn java
//            Добавлено дело "learn java"
//            LIST
//            0 - buy milk
//            1 - learn java
//            todoList.toString();
            text = "";
            count++;
            word1Num = true;

        }


    }
}
