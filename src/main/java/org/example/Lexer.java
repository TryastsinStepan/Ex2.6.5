package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

public class Lexer {
    private char peek = ' ';
    private char buf = ' '; // Инициализируем buf пустым символом

    private int line = 1;

    private HashMap<String, Word> table = new HashMap<>(); // Используем HashMap вместо Hashtable

    public void reserve(Word word) {
        table.put(word.lexeme, word);
    }

    void read() throws IOException {
        buf = peek;
        peek = (char) System.in.read();
    }

    public Lexer() {
        reserve(new Word(Tag.TRUE, "true"));
        reserve(new Word(Tag.FALSE, "false"));
        reserve(new Word(Tag.LESS, "<"));
        reserve(new Word(Tag.FOR, "for"));
        reserve(new Word(Tag.LESS_EQUALS, "<="));
        reserve(new Word(Tag.EQUALS, "=="));
        reserve(new Word(Tag.NOT_EQUALS, "!="));
        reserve(new Word(Tag.BIGGER_EQUALS, ">="));
        reserve(new Word(Tag.BIGGER, ">"));
    }

    public Token scan() throws IOException {
        while (true) {
            // Пропускаем пробельные символы
            while (peek == ' ' || peek == '\t') {
                read();
            }

            // Обновляем счетчик строк
            if (peek == '\n') {
                line++;
                read();
            }

            // Обработка комментариев
            if (peek == '/') {
                read();
                if (peek == '/') {
                    while (peek != '\n') {
                        read();
                    }
                    line++;
                } else if (peek == '*') {
                    char prev = ' ';
                    while (true) {
                        prev = peek;
                        read();
                        if (prev == '*' && peek == '/') {
                            read();
                            break;
                        }
                        if (peek == '\n') {
                            line++;
                        }
                    }
                } else {
                    return new Token('/');
                }
            } else {
                break;
            }
        }


        // Обработка ключевых слов или идентификаторов
        if (Character.isLetter(peek)) {
            StringBuilder stringBuffer = new StringBuilder();
            do {
                stringBuffer.append(peek);
                read();
            } while (Character.isLetterOrDigit(peek));
            String s = stringBuffer.toString();
            Word w = table.get(s);
            if (w != null) {
                return w;
            } else {
                w = new Word(Tag.ID, s);
                table.put(s, w);
                return w;
            }
        }

        // Обработка операторов
        if ("!<>=".contains(String.valueOf(peek))) {
            read();
           String save =  buf+ ""+peek;
           Word w = (Word) table.get(save);
           if(w!=null) {
               read();
               return w;
           }
            w = (Word) table.get(buf + "");
            if(w!=null) {
                return w;
            }
            return new Token(peek);
        }
        //Плавающая точка
        if(Character.isDigit(peek) || peek == '.'){
            Boolean isDotExist = false;
            StringBuffer b = new StringBuffer();
            do{
                if(peek == '.'){
                    isDotExist = true;
                }
                b.append(peek);
            read();
            }while(isDotExist == true ? Character.isDigit(peek) : Character.isDigit(peek) || peek == '.');
                 if(isDotExist)   {
                               return new FloatNum( Double.parseDouble(b.toString()));
                 }
                 return new Num(Integer.parseInt(b.toString()));
        }
        Token t = new Token(peek);
        peek = ' ';
        return t;
    }
}