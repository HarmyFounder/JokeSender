package com.HarmyIndustries.jokeSender;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.mail.MessagingException;
import java.io.IOException;

public class JokeSender {
    public static void main(String[] args) throws IOException {

        MailSender mailSender = new MailSender();

        Document doc = (Document) Jsoup.connect("https://www.mk.ru/anekdoti").get();

        Elements jokes = doc.getElementsByClass("listing-preview__desc");

        jokes.forEach(joke -> {
            try {
                mailSender.send(joke.text());
            } catch (MessagingException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });





    }
}
