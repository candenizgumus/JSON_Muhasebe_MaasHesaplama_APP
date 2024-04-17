package com.candenizgumus;

import com.candenizgumus.database.DataBase;
import com.candenizgumus.services.DosyaOku;
import com.candenizgumus.services.MaasBordro;

import javax.xml.crypto.Data;

public class Program
{

    public static void main(String[] args)
    {

        if (dosyaOkuVeBordroOlustur())
        {
            System.out.println("Maaş bordrosu başarıyla oluşturuldu.");
        }
        else
        {
            System.out.println("Dosya okuma başarısız. Maaş bordrosu oluşturulamadı.");
        }

        System.out.println("---------150 saatten az çalışanlar----------");
        DataBase.personelArrayList.stream().filter(personel -> personel.getCalismaSaati()<150).forEach(System.out::println);


    }

    public static boolean dosyaOkuVeBordroOlustur()
    {
        // JSON dosyasından personel bilgilerini okuma
        DosyaOku dosyaOku = new DosyaOku();
        if (dosyaOku.dosyadanOku()) //Dosya okuma başarılıysa bordroları oluşturur.
        {
            MaasBordro maasBordro = new MaasBordro();
            maasBordro.kaydet();
            return true;
        }
        return false;
    }
}
