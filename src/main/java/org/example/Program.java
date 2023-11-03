package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите Ваши данные (ФИО, дата рождения, пол (м/ж), номер мобильного телефона): ");
        String data = in.nextLine();
        String[] yourData = data.split(" ");
        countOfDataComment(yourData);
        checkFullName(yourData);
        if (!checkSex(yourData[4])) throw new SexFormatException(yourData[4]);
        if (!validDate(yourData[3])) try {
            throw new IOException("Неверный формат даты.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        checkPhone(yourData[5]);

        try {
            File file = new File(yourData[0].toLowerCase());
            if (file.createNewFile()) {
               // System.out.println("Файл создан");
                FileWriter writer = new FileWriter(yourData[0].toLowerCase());
                writer.write(String.join(" ", yourData));
                writer.append('\n');
                writer.close();
            } else {
                //System.out.println("Файл уже существует");
                FileWriter writer = new FileWriter(yourData[0].toLowerCase(), true);
                writer.append(String.join(" ", yourData));
                writer.append('\n');
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании или записи файла");
            e.printStackTrace();
        }
    }


    // проверка количества введённых данных
    public static int countOfData(String[] array) {
        if (array == null) {
            return -1;
        }
        if (array.length < 6) {
            return -2;
        }
        if (array.length > 6) {
            return -3;
        }
        return 1;
    }

    public static void countOfDataComment(String[] array) {
        int result = countOfData(array);
        switch (result) {
            case -1:
                throw new IndexOutOfBoundsException("Вы не ввели данные.");
            case -2:
                throw new IndexOutOfBoundsException("Вы ввели недостаточно данных");
            case -3:
                throw new IndexOutOfBoundsException("Вы ввели больше данных, чем нужно.");
            default:
                System.out.println("Данные введены.");
        }
    }


    // проверка ФИО
    public static void checkFullName(String[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            if (!hasNotDigits(array[i])) throw new YourDataFormatException(array[i]);
        }
    }

    // проверка строки (без цифр - true, c цифрами - false)
    public static boolean hasNotDigits(String string) {
        boolean hasDigit = true;
        for (int i = 0; i < string.length() && hasDigit; i++) {
            if (Character.isDigit(string.charAt(i))) {
                hasDigit = false;
            }
        }
        // System.out.println(hasDigit);
        return hasDigit;
    }

    // проверка номера телефона
    public static void checkPhone(String string) {
        if (string.length() != 11 || !isDigit(string)) throw new PhoneFormatException(string);
    }

    // метод, который проверяет является ли строка числом
    public static boolean isDigit(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // проверка пола

    public static boolean checkSex(String string) throws SexFormatException {
        if (string.length() != 1) return false;
        if (string.equals("м") || string.equals("ж")) return true;
        return false;
    }

    // проверка даты
    public static boolean validDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            dateFormat.setLenient(false);
            Date parseDate = dateFormat.parse(dateString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

