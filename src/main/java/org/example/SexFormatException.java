package org.example;

import java.io.IOException;

public class SexFormatException extends RuntimeException {
    SexFormatException(){
        super("Неверный формат данных.");
    }
    SexFormatException(String string){
        super("Вы неверно указали пол: " + string);
    }
}
