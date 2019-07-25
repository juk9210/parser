package com.brain.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Создаём класс для запуска нашей программы.
 *
 * @author Shakhov Yevhen.
 */

@SpringBootApplication
public class ParserApplication {

    public static void main(String[] args) {
        SpringApplication.run( ParserApplication.class, args );
        ExFsParser parser = new ExFsParser();    //создаём обьект нашего парсера.
        parser.run();    // вызываем метод запуска парсера
    }

}
