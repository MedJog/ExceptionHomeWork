package org.example;

public class PhoneFormatException extends NumberFormatException{
    PhoneFormatException(){
        super("Вы некорректно ввели номер телефона.");
    }
    PhoneFormatException(String string){
        super("Вы некорректно ввели номер телефона: " + string );
    }
}
