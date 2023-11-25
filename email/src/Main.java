import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String address;
        String command;
        boolean commandValid;
        String regex = ".+@.+\\..+";
        String reg = "[ADD] | [LIST]";


        EmailList adressList = new EmailList();

        System.out.println("Введите e-mail адрес");
        while (true) {
            address = "";
            String inputText = new Scanner(System.in).nextLine();
            if (inputText.isEmpty()) {
                System.out.println("Не верная команда");
                continue;
            }
            String[] words = inputText.split(" ");
            command = words[0];

            try {
                address = String.valueOf(words[1]);
            } catch (ArrayIndexOutOfBoundsException aiob) {
            }

            commandValid = command.matches("ADD|LIST" );

            if (!commandValid) {
                System.out.println("Не верная команда");
                continue;
            }

            if (command.contains("ADD")) {
                if (address.matches(regex)) {
                    System.out.println("Адрес " + address + " добавлен");
                    adressList.addEmail(address);
                    continue;
                } else {
                    System.out.println("Не верный e-mail");
                    continue;
                }
            }
            if (command.contains("LIST")) {
                TreeSet list = adressList.getEmailList();
                System.out.println("Список уникальных e-mail адресов:");
                Iterator iterator = list.iterator();
                int i = 0;
                while (iterator.hasNext()) {
                    System.out.println(i + " " + iterator.next());
                    i++;
                }
            }


//                    continue;
//
//            switch (command) {
//                case "ADD":
//                    if (word1IsNotEmpty) {
////                        System.out.println("Адрес " + address + " добавлен");
////                        adressList.addEmail(address);
//                    }
//                    break;
//
//                case "LIST": {
////                    TreeSet list = adressList.getEmailList();
////                    System.out.println("Список уникальных e-mail адресов:");
////                    Iterator iterator = list.iterator();
////                    int i = 0;
////                    while (iterator.hasNext()) {
////                        System.out.println(i + " " + iterator.next());
////                        i++;
//                    }
//                }
//                break;
//                default:
//                    System.out.println("Не верная команда");
//            }


        }
    }
}

