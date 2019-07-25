package com.brain.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Создаём класс для парсинга сайта.
 *
 * @author Shakhov Yevhen.
 */

class ExFsParser {
    /**
     * Создаём метод для запуска парсера.
     */
    void run() {
        System.out.println( "Executing run method" );  //выводим на экран сообщение выполнении запуска
        new File( "parsed" ).mkdir(); //создаем папку для хранения картинок
        List<String> urls = parse(); //вызываем метод парсинга URL картинок
        save( urls ); //сохраняем картинки из списка ссылок
    }

    /**
     * Создаём метод для сохранения распарсернных картинок сайта
     *
     * @param urls
     */
    private void save(List<String> urls) {
        System.out.println( "Try to save" );  // выводим на экран что пробуем сохранить
        for (int i = 0; i < urls.size(); i++) { //проходим по списку ссылок
            String imgUrl = urls.get( i ); //в переменную сохраняем полученную ссылку
            try {
                URL url = new URL( imgUrl ); //создаем обьект URL
                BufferedImage image = ImageIO.read( url ); //добавляем строчку для чтения картинки по URL
                String imgType = imgUrl.substring( imgUrl.lastIndexOf( '.' ) + 1 ); // возвращаем тип картинки
                ImageIO.write( image, imgType, new File( "parsed/img_" + i + '.' + imgType ) ); //сохраняем картинку на
                // диск с новым именем
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Создаём метод парсинга html страницы для получения нужных картинок.
     *
     * @return
     */
    private List<String> parse() {
        System.out.println( "Parsing page" ); //выводим на экран что парсим страницу
        String url = "http://ex-fs.com/page/1"; //создаём строку с URl нужной сраницы
        List<String> result = new ArrayList<>(); //создаем список для хранения распарсенных ссылок на картинки
        try {
            Document document = Jsoup.connect( url ).get();  //выполняем чтение HTML страницы с помощью бибилотеки JSOUP
            Elements links = document.select( ".custom-poster img[src]" );//создаем переменную с нужным css селектором
            for (Element link : links) { //проходим по по списку полученных ссылок
                String imgUrl = "http://ex-fs.com/" + link.attr( "src" ); //берем host сайта и добавляем к
                // нему значение источника картинки(в теге img у аттрибута src)
                result.add( imgUrl ); //добавляем URL картинки к результатам
                System.out.println( imgUrl ); //выводим на экран ссылки на картинки
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result; //возвращаем result
    }
}
