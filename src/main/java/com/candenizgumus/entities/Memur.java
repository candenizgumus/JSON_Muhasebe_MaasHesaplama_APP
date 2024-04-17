package com.candenizgumus.entities;

public class Memur extends Personel
{
    public enum Derece
    {
        JUNIOR, MID, SENIOR
    }
    private int calismaSaati;
    private double anaOdeme;
    private double bonus;
    private double toplamOdeme;
    private Derece degree;

    public Memur(String name, String surname, Derece memurDerecesi)
    {

        super(name, surname);
        this.degree = memurDerecesi;
    }

    /**
     * Memurun kıdemine göre saatlik ücretini belirler.
     * @return Saatlik ucret doner.
     */
    @Override
    public double getSaatlikUcret()
    {
        switch (degree)
        {
            case JUNIOR:
                return 500;
            case MID:
                return 600;
            case SENIOR:
                return 700;
            default:
                return 500;
        }
    }

    /**
     * Memurun aylık maasını hesaplar. Calisma saati 180'den fazlaysa bonus ucreti tanımlar.
     * @return Aylık toplam maasi dondurur.
     */
    @Override
    public double maasHesapla()
    {

        if (calismaSaati > 180)
        {
            bonus = (calismaSaati - 180) * (getSaatlikUcret() * 1.5);
        }
        anaOdeme = (getSaatlikUcret() * calismaSaati);
        toplamOdeme = (getSaatlikUcret() * calismaSaati) + bonus;
        return toplamOdeme;
    }

    @Override
    public String toString()
    {

        return super.toString() + " Memur{" + "calismaSaati=" + calismaSaati + ", anaOdeme=" + anaOdeme + ", bonus=" + bonus + ", toplamOdeme=" + toplamOdeme + ", degree=" + degree + '}';
    }

    public int getCalismaSaati()
    {

        return calismaSaati;
    }

    public void setCalismaSaati(int calismaSaati)
    {

        this.calismaSaati = calismaSaati;
    }

    public double getEkMesaiUcreti()
    {

        return bonus;
    }

    public void setEkMesaiUcreti(double ekMesaiUcreti)
    {

        this.bonus = ekMesaiUcreti;
    }

    public Derece getDegree()
    {

        return degree;
    }

    public void setDegree(Derece degree)
    {

        this.degree = degree;
    }

    public double getAnaOdeme()
    {

        return anaOdeme;
    }

    public void setAnaOdeme(double anaOdeme)
    {

        this.anaOdeme = anaOdeme;
    }

    public double getBonus()
    {

        return bonus;
    }

    public void setBonus(double bonus)
    {

        this.bonus = bonus;
    }

    public double getToplamOdeme()
    {

        return toplamOdeme;
    }

    public void setToplamOdeme(double toplamOdeme)
    {

        this.toplamOdeme = toplamOdeme;
    }
}
