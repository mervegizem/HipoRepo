package com.android.mervegk.hipotask;

import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 Author : Merve Gizem Kabaoğlu
 Bu sınıf flicker apiden resim fetch etme sınıfıdır.
 */
public class FlickrFetchr {

    //PUBLIC DEGISKENLER:
    public static final String API_KEY = "b8cbad529685c20682f8c6d88ad200fd" ; // uygulama API KEYI
    public static final String PREF_SEARCH_QUERY = "searchQuery";
    //*****************

    //PRIVATE DEGISKENLER:
    private static final String TAG = FlickrFetchr.class.getSimpleName();
    private static final String ENDPOINT = "https://api.flickr.com/services/rest/" ; // uygulama için istek yapacağımız rest servis
    private static final String METHOD_GET_RECENT = "flickr.photos.getRecent" ; // get isteğimiz
    private static final String METHOD_SEARCH = "flickr.photos.search" ; // search isteğimiz
    private static final String PARAM_EXTRAS = "extras" ; // extra isteğimiz
    private static final String PARAM_TEXT = "query" ; // sorgu isteğimiz
    private static final String PAGE = "page"; // sayfa isteğimiz.
    private static final String EXTRA_SMALL_URL = "url_s" ;
    private static final String XML_PHOTO = "photo" ;
    //*******************

    private static volatile FlickrFetchr instance = null; // başlangıçta instance null.

    //KURUCU METOD
    public void FlickrFetcher() {

    }
    //***********

    public static FlickrFetchr getInstance() { // instance oluşturmak için fonksiyonumuz.
        if (instance == null) { // eğer instance null ise (ilk durumda)
            synchronized (FlickrFetchr.class) {
                if (instance == null ) {
                    instance = new FlickrFetchr(); // yeni bir nesne oluşturarak instance oluşturuyoruz.
                }
            }
        }
        return instance; // instance'ı geriye döndürüyoruz.
    }

    //şimdi url oluşturma fonksiyonumza gelelim.Burada sorgu ve sayfa bilgilerini almalıyız.
    public static String getItemUrl(String query, int page) {
        String url;
        if (query !=null) { //eğer sorgu null değil ise
            //url'imizi aşağıdaki şekilde oluşturuyoruz.ve istek yapabilmek için sonunda string'e çeviriyoruz.
            url = Uri.parse(ENDPOINT).buildUpon()
                    .appendQueryParameter("method", METHOD_SEARCH)
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("text", query)
                    .appendQueryParameter("page", String.valueOf(page))
                    .build().toString();
        } else { // eğer sorgu null ise
            //url aşağıdaki şekilde oluşturulmalıdır.
            url = Uri.parse(ENDPOINT).buildUpon()
                    .appendQueryParameter("method", METHOD_GET_RECENT)
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .build().toString();
        }
        return url; // url geri gönderiliyor.
    }

    //şimdi isteği atma zamanı.
    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec); //öncelikle url nesnesi oluşturmalıyız.

        //http bağlantımızı kuralım bunun için connection nesnesi kullancağız.
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //bağlantı sağlıklı olabilmesi için try-catch mekanizması kullanmalıyız.
        try {
            //bağlantıyı sağlamak için out ve in streamlerini oluşturmalıyız.
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            //eğer bağlantı sağlıklı değilse geriye null döndürürüz.
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK ) {
                return null ;
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024]; // okunacak verileri tutacak buffer

            //okuma işlemi beklenmeli sonuna kadar okunmalıdır.
            while ( (bytesRead = in.read(buffer)) > 0 ) {
                out.write(buffer, 0, bytesRead);
            }
            out.close(); // out stream sonlandırılır.
            return out.toByteArray(); //geriye gelen byteArray döndürülür.

        } finally {
            connection.disconnect(); // her halükarda bağlantıyı sonlandırmalıyız.
        }
    }

    public String getUrl(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }



}
