package com.candenizgumus.entities;

public abstract class Personel
{
    private String name;
    private String surname;

    public Personel(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public  abstract double maasHesapla();
    public abstract double getSaatlikUcret();
    public abstract int getCalismaSaati();
    public abstract double getAnaOdeme();
    public abstract double getBonus();
    public abstract double getToplamOdeme();

    @Override
    public String toString()
    {

        return "Personel{" + "name='" + name + '\'' + ", surname='" + surname + '\'' ;
    }

    public String getName()
    {

        return name;
    }

    public void setName(String name)
    {

        this.name = name;
    }

    public String getSurname()
    {

        return surname;
    }

    public void setSurname(String surname)
    {

        this.surname = surname;
    }
}
