package com.example.workmannager.model.enums;

public enum Priority {
    MUITO_BAIXA(1),
    BAIXA(2),
    MEDIA(3),
    ALTA(4),
    MUITO_ALTA(5);

    private int cod;

    private Priority(int cod){
        this.cod = cod;

    }



    public int getCod(){
        return cod;
    }

    public static Priority valueOf(int cod ) {
        for (Priority value : Priority.values()) {
            if (value.getCod() == cod) {
                return value;
            }
        }
        throw new IllegalStateException("Invalid OrderStatus Code");

    }
    public static Priority valueOfString(String cod){
        for (Priority value : Priority.values()){
            if(value.toString() == cod){}
            return value;
        }throw  new IllegalStateException("Invalid Situation code");
    }
}
