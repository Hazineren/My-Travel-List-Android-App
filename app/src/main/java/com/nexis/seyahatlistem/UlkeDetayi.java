package com.nexis.seyahatlistem;

import android.graphics.Bitmap;

public class UlkeDetayi {
    private String ulkeAdi,ulkeBaskenti,ulkeHakkinda;
    private Bitmap ulkeResmi;

    public UlkeDetayi(String ulkeAdi, String ulkeBaskenti, String ulkeHakkinda, Bitmap ulkeResmi) {
        this.ulkeAdi = ulkeAdi;
        this.ulkeBaskenti = ulkeBaskenti;
        this.ulkeHakkinda = ulkeHakkinda;
        this.ulkeResmi = ulkeResmi;
    }

    public String getUlkeAdi() {
        return ulkeAdi;
    }

    public String getUlkeBaskenti() {
        return ulkeBaskenti;
    }

    public String getUlkeHakkinda() {
        return ulkeHakkinda;
    }

    public Bitmap getUlkeResmi() {
        return ulkeResmi;
    }
}
