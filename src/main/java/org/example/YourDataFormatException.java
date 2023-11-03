package org.example;
// для проверки ФИО
public class YourDataFormatException extends NumberFormatException{
YourDataFormatException(){
    super("Некорректный ввод данных.");
}
YourDataFormatException(String string){
    super("Вы некорректно ввели свои данные: " + string + " Повторите ввод.");
}
}
