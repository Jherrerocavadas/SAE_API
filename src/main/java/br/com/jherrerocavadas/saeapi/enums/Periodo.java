package br.com.jherrerocavadas.saeapi.enums;


public enum Periodo {
    MANHA("Manhã"),
    TARDE("Tarde"),
    NOITE("Noite");

    private String nomePeriodo;
    Periodo(String nomePeriodo){
        this.nomePeriodo = nomePeriodo;
    }

    public String getNomePeriodo() {
        return nomePeriodo;
    }
}
