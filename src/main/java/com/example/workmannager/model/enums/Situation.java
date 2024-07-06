package com.example.workmannager.model.enums;

public enum Situation {
    PENDENTE(1),
    INICIADA(2),
    CONCLUIDA(3),
    CANCELADA(4);

    private int cod;

    private Situation(int cod){
        this.cod = cod;
    }
    public int getCod(){
        return  cod;
    }

    public static Situation valueOf(int cod){
        for (Situation value : Situation.values()){
            if(value.getCod() == cod){}
            return value;
        }throw  new IllegalStateException("Invalid Situation code");
    }

    public static Situation valueOfString(String cod){
        for (Situation value : Situation.values()){
            if(value.toString() == cod){}
            System.out.println(value);
            return value;
        }throw  new IllegalStateException("Invalid Situation code");
    }
}
