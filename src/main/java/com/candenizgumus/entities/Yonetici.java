package com.candenizgumus.entities;

public class Yonetici extends Personel
{

    private double saatlikUcret;
    private int calismaSaati;
    private double anaOdeme;
    private double bonus;
    private double toplamOdeme;

    public Yonetici(String name, String surname, double bonus)
    {

        super(name, surname);
        this.bonus = bonus;
    }

    @Override
    public double getSaatlikUcret()
    {

        return saatlikUcret;
    }

    /**
     * Yoneticinin aylik maasini hesaplar. Yonetici ucreti 500'den kucukse hata firlatir.
     * @return Aylik maasi dondurur.
     */
    @Override
    public double maasHesapla()
    {

        if (getSaatlikUcret() < 500)
        {
            throw new IllegalStateException("Yonetici saatlik ucreti 500 TL'den kucuk olamaz.");
        }
        anaOdeme = getSaatlikUcret() * calismaSaati;
        toplamOdeme = anaOdeme + bonus;
        return toplamOdeme;
    }

    @Override
    public String toString()
    {

        return super.toString() + " Yonetici{" + "saatlikUcret=" + saatlikUcret + ", calismaSaati=" + calismaSaati + ", anaOdeme=" + anaOdeme + ", bonus=" + bonus + ", toplamOdeme=" + toplamOdeme + '}';
    }

    public void setSaatlikUcret(double saatlikUcret)
    {

        this.saatlikUcret = saatlikUcret;
    }

    public int getCalismaSaati()
    {

        return calismaSaati;
    }

    public void setCalismaSaati(int calismaSaati)
    {

        this.calismaSaati = calismaSaati;
    }

    public double getBonus()
    {

        return bonus;
    }

    public void setBonus(double bonus)
    {

        this.bonus = bonus;
    }

    public double getAnaOdeme()
    {

        return anaOdeme;
    }

    public void setAnaOdeme(double anaOdeme)
    {

        this.anaOdeme = anaOdeme;
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

