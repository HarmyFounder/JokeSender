package com.HarmyIndustries.jokeSender;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class JokeSender {
    public static void main(String[] args) throws IOException {

        boolean hasAnyRoles = true;

        SpecialFeature specialFeature = new SpecialFeature();

        List<String> urls = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        System.out.println("Введите почту получателя");
        String recipientGmail = sc.next();


        while (hasAnyRoles) {
            System.out.println("У вас есть ссылка на страницу с анекдотами?(yes/no)");
            String urlsStatus = sc.next();
            if (urlsStatus.equals("yes")) {
                System.out.println("Введите адрес");
                String url = sc.next();
                urls.add(url);
            } else if (urlsStatus.equals("no")) {
                hasAnyRoles = false;
            }

        }

        System.out.println("Введите отличительную часть для парсинга");
        String specialFeatureString = sc.next();
        specialFeature.setSpecialFeature(specialFeatureString);

        System.out.println("Введите тип отличительной черты");
        String specialFeatureType = sc.next();

        if (specialFeatureType.equals("className")) {
            specialFeature.setClass(true);
        } else if (specialFeatureType.equals("attributeName")) {
            specialFeature.setAttribute(true);
        } else {
            System.out.println("Ошибка ввода! Перезапустите программу!");
        }

        sendJokes(urls, specialFeature, recipientGmail);

    }

    public static void sendJokes(List<String> urls, SpecialFeature specialFeature, String recipientGmail) throws IOException {


        MailSender mailSender = new MailSender();

        List<Document> docs = new ArrayList<>();
        List<Elements> elementsList = new ArrayList<>();

        for (String url : urls) {
            Document doc = Jsoup.connect(url).get();
            docs.add(doc);
        }

        for (Document docInList : docs) {
            if (specialFeature.isClass()) {
                Elements jokes = docInList.getElementsByClass(specialFeature.getSpecialFeature());
                elementsList.add(jokes);

            } else if (specialFeature.isAttribute()) {
                Elements jokes = docInList.getElementsByAttribute(specialFeature.getSpecialFeature());
                elementsList.add(jokes);
            }
        }

        for (Elements elements : elementsList) {
            elements.forEach(joke -> {
                try {
                    mailSender.send(joke.text(), recipientGmail);
                } catch (MessagingException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }


}
