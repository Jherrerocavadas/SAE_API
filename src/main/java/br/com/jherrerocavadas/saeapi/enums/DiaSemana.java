package br.com.jherrerocavadas.saeapi.enums;

public enum DiaSemana {
    SEGUNDA("Segunda"),
    TERÇA("Terça"),
    QUARTA("Quarta"),
    QUINTA("Quinta"),
    SEXTA("Sexta"),
    SABADO("Sábado");

    private String diaDaSemana;
    DiaSemana(String diaDaSemana){
        this.diaDaSemana = diaDaSemana;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }



}
