package com.candenizgumus.services;

import com.candenizgumus.database.DataBase;
import com.candenizgumus.entities.Memur;
import com.candenizgumus.entities.Personel;
import com.candenizgumus.entities.Yonetici;
import com.google.gson.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DosyaOku
{

    /**
     * PersonelDatas.json dosyasını okur ve okuduğu dosyadan Yonetici veya Memur nesnesi uretir.
     * @return Okuma basarili ise true, basarisiz ise false doner.
     */
    public boolean dosyadanOku()
    {

        try
        {
            //Dizindeki dosyayı okur ve JsonArray'ine parse eder.
            FileReader fileReader = new FileReader("C:\\JAVA14\\CSProjeDemo2\\PersonelDatas.json");
            JsonParser jsonParser = new JsonParser();
            JsonArray jsonArray = jsonParser.parse(fileReader).getAsJsonArray();
            Gson gson = new Gson();

            //Oluşan JsonArray'in içindeki her bir elementi tek tek gezer.
            for (JsonElement element : jsonArray)
            {
                JsonObject personelJson = element.getAsJsonObject();
                Personel personel;

                //Eger role -> Yonetici ise Yonetici olusturur aksi halde memur olusturur ve listeye atar.
                if (personelJson.get("role").getAsString().equals("Yonetici"))

                {
                    personel = gson.fromJson(personelJson, Yonetici.class);
                    DataBase.personelArrayList.add(personel);
                }
                else
                {
                    personel = gson.fromJson(personelJson, Memur.class);
                    DataBase.personelArrayList.add(personel);
                }

            }
            return true;

        } catch (Exception e)
        {
            e.printStackTrace();
            return false;

        }
    }

}
