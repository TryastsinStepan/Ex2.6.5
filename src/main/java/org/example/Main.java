package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
            Lexer lexer = new Lexer();
            for (; ; ) {
                Token t = lexer.scan();
                switch (t.token) {
                    case Tag.NUM:
                        System.out.println("NUM -> " + ((Num) t).num);
                        break;
                    case Tag.ID:
                        System.out.println("ID -> " + ((Word) t).lexeme);
                        break;
                    case Tag.FOR:
                        System.out.println("FOR");
                        break;
                    case Tag.TRUE:
                        System.out.println("TRUE");
                        break;
                    case Tag.FALSE:
                        System.out.println("FALSE");
                        break;
                    case Tag.LESS:
                        System.out.println("OP: <");
                        break;
                    case Tag.LESS_EQUALS:
                        System.out.println("OP: <=");
                        break;
                    case Tag.EQUALS:
                        System.out.println("OP: ==");
                        break;
                    case Tag.NOT_EQUALS:
                        System.out.println("OP: !=");
                        break;
                    case Tag.BIGGER_EQUALS:
                        System.out.println("OP: >=");
                        break;
                    case Tag.BIGGER:
                        System.out.println("OP: >");
                        break;
                    case Tag.FLOAT:
                        System.out.println("REAL -> " + ((FloatNum) t).num);
                        break;
                    default:
                        System.out.println((char) t.token);
                        break;
                }
            }
        }
    }



