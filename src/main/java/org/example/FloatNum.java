package org.example;

public class FloatNum extends Token {
    public  final double num;
    public FloatNum(double n){
        super(Tag.FLOAT);
        this.num =  n;
    }
}
