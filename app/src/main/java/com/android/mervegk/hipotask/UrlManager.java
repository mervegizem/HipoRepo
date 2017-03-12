package com.android.mervegk.hipotask;

import android.net.Uri;

/**
 * Author : Merve Gizem KABAOĞLU
 */

//URL oluşturma sınıfı
public class UrlManager {

    private static final String TAG = UrlManager.class.getSimpleName();
    public static final String API_KEY = "b8cbad529685c20682f8c6d88ad200fd" ;
    public static final String PREF_SEARCH_QUERY = "searchQuery";
    private static final String ENDPOINT = "https://api.flickr.com/services/rest/" ;
    private static final String METHOD_GETRECENT = "flickr.photos.getRecent" ;
    private static final String METHOD_SEARCH = "flickr.photos.search" ;
    private static final String PARAM_EXTRAS = "extras" ;
    private static final String PARAM_TEXT = "query" ;
    private static final String PAGE = "page";
    private static final String EXTRA_SMALL_URL = "url_s" ;
    private static final String XML_PHOTO = "photo" ;
    private static volatile UrlManager instance = null;

    //kurucu metod.
    private UrlManager() {

    }

    //instance oluşturmak için metod
    public static UrlManager getInstance() {
        if (instance == null) { // eğer bir instance yoksa
            synchronized (UrlManager.class) {
                if (instance == null) {
                    instance = new UrlManager(); // yeni bir nesne oluşturup instance oluşturalım.
                }
            }
        }
        return instance; // instance değerini geriye döndürelim.
    }

    //şimdi url oluşturma fonksiyonumuzu gerçekleştirelim.Bu kısımda sayfa ve sorgu değişkenlerimizi almalıyız.
    public static String getItemUrl(String query, int page) {
        String url;
        if (query != null) { // eğer sorgu varsa aşağıdaki şekilde url'imizi oluştururuz.
            url = Uri.parse(ENDPOINT).buildUpon()
                    .appendQueryParameter("method", METHOD_SEARCH)
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("text", query)
                    .appendQueryParameter("page", String.valueOf(page))
                    .build().toString(); // sonucunda string'e çevirmeliyiz.
        } else { // eğer sorgu yoksa (null ise) bu şekilde oluşturulmalıdır.
            url = Uri.parse(ENDPOINT).buildUpon()
                    .appendQueryParameter("method", METHOD_GETRECENT)
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("page", String.valueOf(page))
                    .build().toString();
        }
        return url; // url'imizi geri gönderiyoruz.
    }

    private static final String FLICKR_URL = "http://flickr.com/photo.gne?id=%s";
    private static final String METHOD_GETINFO = "flickr.photos.getInfo";

    //flicker url get metodu.
    public static String getFlickrUrl(String id) {
        return String.format(FLICKR_URL, id);
    }

    //fotoğraf info url get metodu.
    public static String getPhotoInfoUrl(String id) {
        return Uri.parse(ENDPOINT).buildUpon()
                .appendQueryParameter("method", METHOD_GETINFO)
                .appendQueryParameter("api_key", API_KEY)
                .appendQueryParameter("format", "json")
                .appendQueryParameter("nojsoncallback", "1")
                .appendQueryParameter("photo_id", id)
                .build().toString();
    }
}
