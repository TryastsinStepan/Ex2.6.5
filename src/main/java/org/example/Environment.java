package org.example;

import java.util.HashMap;

public class Environment {
    protected Environment prev;
    protected HashMap table;
    public Environment(Environment prev){
        table = new HashMap();
        this.prev = prev;
    }
    public void put(String word,Symbol symbol){
        table.put(word,symbol);
    }
    public Symbol get(String s){
        for(Environment env = this;env!=null; env =env.prev){
            Symbol symbol = (Symbol)table.get(s);
            if(symbol!=null) return symbol;
        }
        return null;
    }
}
