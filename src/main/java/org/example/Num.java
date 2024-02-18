package org.example;

public class Num extends Token{
    public  final int num;
    public Num(int n){
        super(Tag.NUM);
        this.num =  n;
    }

}
