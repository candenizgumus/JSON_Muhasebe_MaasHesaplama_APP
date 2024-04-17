package com.candenizgumus.services;

import com.candenizgumus.database.DataBase;
import com.candenizgumus.entities.Personel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class MaasBordro
{

    /*
     DataBase içindeki personelArrayList içerisindeki personel bilgilerinden maas_bordrolari dosyası oluşturur.
     Her bir personelin bilgilerini dosyanın içerisine ayri ayri kaydeder.
     */
    public void kaydet()
    {

        Gson gson = new Gson();
        for (Personel personel : DataBase.personelArrayList)
        {
            personel.maasHesapla(); //Her bir personelin maaşı hesaplanıyor.
            // JSON oluşturma
            JsonObject jsonBordro = new JsonObject();
            jsonBordro.addProperty("bordro", LocalDate.now().getMonth() + " " + LocalDate.now().getYear());

            JsonObject jsonPersonel = new JsonObject();
            jsonPersonel.addProperty("ismi", personel.getName());
            jsonPersonel.addProperty("soyismi", personel.getSurname());
            jsonPersonel.addProperty("calismaSaati", personel.getCalismaSaati());
            jsonBordro.add("personel", jsonPersonel);

            JsonObject jsonOdemeDetaylari = new JsonObject();
            jsonOdemeDetaylari.addProperty("anaOdeme", personel.getAnaOdeme());
            jsonOdemeDetaylari.addProperty("mesai", personel.getBonus());
            jsonOdemeDetaylari.addProperty("toplamOdeme", personel.getToplamOdeme());
            jsonBordro.add("odemeDetaylari", jsonOdemeDetaylari);

            String jsonContent = gson.toJson(jsonBordro);

            //Yazdırma islemleri
            jsonDosyasiYazma(personel, jsonContent);
            onSattenAzCalisanPersonelYazdirma(personel, gson);
            yuzElliSaattenAzCalisanPersonellerYazdirme(personel,gson);


        }
        butunPersonelBilgileriniYazdir();
    }

    /**
     * DataBase'deki bütün personellerin bilgilerini yazdirir.
     */
    private void butunPersonelBilgileriniYazdir()
    {
        DataBase.personelArrayList.forEach(System.out::println);

    }

    /**
     * Personellerin jsonContent'ini dosyaya yazar.
     * @param personel Bilgileri yazdirilacak olan personel.
     * @param jsonContent Personelin bilgileri.
     */
    private static void jsonDosyasiYazma(Personel personel, String jsonContent)
    {
        // Klasör oluşturma
        File klasor = new File("maas_bordrolari/" + personel.getName() + "_" + personel.getSurname());
        klasor.mkdirs();
        // JSON dosyasını yazma
        try (FileWriter fileWriter = new FileWriter(klasor.getPath() + "/maas_bordrosu.json"))
        {
            fileWriter.write(jsonContent);

        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Maaş bilgileri kaydedilemedi.");
        }
    }

    /**
     * On saatten az calisan personellerin özet bilgilerini ayri bir dosyaya yazdirir.
     * @param personel Bilgileri yazdirilacak olan personel.
     * @param gson Json a çevirme yapabilmek için kullanılacak olan gson nesnesi.
     */
    private void onSattenAzCalisanPersonelYazdirma(Personel personel, Gson gson)
    {

        if (personel.getCalismaSaati() < 10) {
            // Eğer personel 10 saatten az çalıştıysa ayrı bir yere kaydediliyor.
            File klasor2 = new File("maas_bordrolari/_on_saatten_az_calisanlar");
            klasor2.mkdirs();

            // JSON dosyasını yazma
            try (FileWriter fileWriter = new FileWriter(klasor2.getPath() + "/" + personel.getName() + "_" + personel.getSurname() + ".json")) {
                JsonObject onSaattenAzCalisanOzet = new JsonObject();
                onSaattenAzCalisanOzet.addProperty("bordro", LocalDate.now().getMonth() + " " + LocalDate.now().getYear());
                onSaattenAzCalisanOzet.addProperty("ad", personel.getName());
                onSaattenAzCalisanOzet.addProperty("soyad", personel.getSurname());
                onSaattenAzCalisanOzet.addProperty("calismaSaati", personel.getCalismaSaati());
                String jsonContent2 = gson.toJson(onSaattenAzCalisanOzet);
                fileWriter.write(jsonContent2);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Maaş bilgileri (10 saatten az çalışanlar) kaydedilemedi.");
            }
        }
    }

    /**
     * Yüz elli saatten az calisan personellerin özet bilgilerini ayri bir dosyaya yazdirir.
     * @param personel Bilgileri yazdirilacak olan personel.
     * @param gson Json a çevirme yapabilmek için kullanılacak olan gson nesnesi.
     */
    private void yuzElliSaattenAzCalisanPersonellerYazdirme(Personel personel, Gson gson)
    {

        if (personel.getCalismaSaati() < 150 && personel.getCalismaSaati() >= 10) {
            // Eğer personel 150 saatten az çalıştıysa ayrı bir yere kaydediliyor.
            File klasor2 = new File("maas_bordrolari/_yuz_elli_saatten_az_calisanlar");
            klasor2.mkdirs();

            // JSON dosyasını yazma
            try (FileWriter fileWriter = new FileWriter(klasor2.getPath() + "/" + personel.getName() + "_" + personel.getSurname() + ".json")) {
                JsonObject onSaattenAzCalisanOzet = new JsonObject();
                onSaattenAzCalisanOzet.addProperty("bordro", LocalDate.now().getMonth() + " " + LocalDate.now().getYear());
                onSaattenAzCalisanOzet.addProperty("ad", personel.getName());
                onSaattenAzCalisanOzet.addProperty("soyad", personel.getSurname());
                onSaattenAzCalisanOzet.addProperty("calismaSaati", personel.getCalismaSaati());
                String jsonContent2 = gson.toJson(onSaattenAzCalisanOzet);
                fileWriter.write(jsonContent2);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Maaş bilgileri (150 saatten az çalışanlar) kaydedilemedi.");
            }
        }
    }
}
