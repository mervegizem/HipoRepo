package com.android.mervegk.hipotask;

import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
Author : Merve Gizem Kabaoğlu
*/
public class Contact {

    //değişkenler
    private String name = "default" ;
    public ImageView image = null ; // başlangıçta imageview null olsun.
    private static final String TAG = "Contact" ;
    private static Contact instance = getInstance() ;
    //************

    //GET METODLARI :
    //instance bilgisini geri döndürmek için kullanacağımız fonksiyonumuzdur.
    public static Contact getInstance() {
        if (instance == null) { // eğer instance değeri hala null ise
            instance = new Contact(); // yeni bir contact nesnesi oluşturalım.dolayısıyla instance değeride oluşmuş olacak.
        }

        return instance; // instance değerimizi geri döndürelim.
    }
    public String getName() {
        return name;
    } // name değerini geri gönderen fonksiyonumuz.
    public ImageView getImage() {
        return image;
    } // imageView'i geri döndüren değerimiz .

    //SET METODLARI :
    public void setName(String name) {
        this.name = name;
    } // name değerini setlemek için set


    //örnek listesi oluşturmak için fonksiyonumuz.
    //geriye bir list döndürecektir.
    public static List<Contact> generateSampleList(int samples){
        List<Contact> list = new ArrayList<>(); // öncelikle boş bir liste oluşturalım.

        //fonksiyona girilen örnek sayısı kadar for döngüsü ile instance oluşturalım.
        for(int i= 0 ; i < samples; i++){
            Contact contact = getInstance();
            contact.setName("Name - " + i);

            list.add(contact); // oluşan contanctları listemize ekleyelim.
        }
        return list; // listemizin ilk adresini geriye döndürmüş olacağız.
    }
}
