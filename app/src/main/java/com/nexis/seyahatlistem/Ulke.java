package com.nexis.seyahatlistem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class Ulke {

    private String ulkeAdi,ulkeBaskenti,ulkeHakkinda;
    private Bitmap ulkeResim; //,resim1,resim2,resim3,resim4,resim5
    static Bitmap gelenResim,gelenResim1,gelenResim2,gelenResim3,gelenResim4,gelenResim5;
    static byte[] gelenResimByte,gelenResimByte1,gelenResimByte2,gelenResimByte3,gelenResimByte4,gelenResimByte5;


    public Ulke(){}

    public Ulke(String ulkeAdi, String ulkeBaskenti, String ulkeHakkinda, Bitmap ulkeResim, Bitmap resim1, Bitmap resim2, Bitmap resim3, Bitmap resim4, Bitmap resim5) {
        this.ulkeAdi = ulkeAdi;
        this.ulkeBaskenti = ulkeBaskenti;
        this.ulkeHakkinda = ulkeHakkinda;
        this.ulkeResim = ulkeResim;
        /*this.resim1 = resim1;
        this.resim2 = resim2;
        this.resim3 = resim3;
        this.resim4 = resim4;
        this.resim5 = resim5;*/
    }

    public String getUlkeAdi() {
        return ulkeAdi;
    }

    public void setUlkeAdi(String ulkeAdi) {
        this.ulkeAdi = ulkeAdi;
    }

    public String getUlkeBaskenti() {
        return ulkeBaskenti;
    }

    public void setUlkeBaskenti(String ulkeBaskenti) {
        this.ulkeBaskenti = ulkeBaskenti;
    }

    public String getUlkeHakkinda() {
        return ulkeHakkinda;
    }

    public void setUlkeHakkinda(String ulkeHakkinda) {
        this.ulkeHakkinda = ulkeHakkinda;
    }

    public Bitmap getUlkeResim() {
        return ulkeResim;
    }

    public void setUlkeResim(Bitmap ulkeResim) {
        this.ulkeResim = ulkeResim;
    }

    /*public Bitmap getResim1() {
        return resim1;
    }

    public void setResim1(Bitmap resim1) {
        this.resim1 = resim1;
    }

    public Bitmap getResim2() {
        return resim2;
    }

    public void setResim2(Bitmap resim2) {
        this.resim2 = resim2;
    }

    public Bitmap getResim3() {
        return resim3;
    }

    public void setResim3(Bitmap resim3) {
        this.resim3 = resim3;
    }

    public Bitmap getResim4() {
        return resim4;
    }

    public void setResim4(Bitmap resim4) {
        this.resim4 = resim4;
    }

    public Bitmap getResim5() {
        return resim5;
    }

    public void setResim5(Bitmap resim5) {
        this.resim5 = resim5;
    }*/

    static public ArrayList<Ulke> getData(Context context){
        ArrayList<Ulke> ulkeList = new ArrayList<>();

        ArrayList<String> ulkeAdiList = new ArrayList<>();
        ArrayList<String> ulkeBaskentiList = new ArrayList<>();
        ArrayList<String> ulkeHakkindaList = new ArrayList<>();
        ArrayList<Bitmap> ulkeResimList = new ArrayList<>();
        /*ArrayList<Bitmap> resim1List = new ArrayList<>();
        ArrayList<Bitmap> resim2List = new ArrayList<>();
        ArrayList<Bitmap> resim3List = new ArrayList<>();
        ArrayList<Bitmap> resim4List = new ArrayList<>();
        ArrayList<Bitmap> resim5List = new ArrayList<>();*/

        try {
            SQLiteDatabase database = context.openOrCreateDatabase("Ulkeler",Context.MODE_PRIVATE,null);
            Cursor cursor = database.rawQuery("SELECT * FROM ulkeler",null);

            int ulkeAdiIndex = cursor.getColumnIndex("ulkeAdi");
            int ulkeBaskentiIndex = cursor.getColumnIndex("ulkeBaskent");
            int ulkeHakkindaIndex = cursor.getColumnIndex("ulkeHakkinda");
            int ulkeResimIndex = cursor.getColumnIndex("ulkeAnaResim");
            /*int resim1Index = cursor.getColumnIndex("ulkeResim1");
            int resim2Index = cursor.getColumnIndex("ulkeResim2");
            int resim3Index = cursor.getColumnIndex("ulkeResim3");
            int resim4Index = cursor.getColumnIndex("ulkeResim4");
            int resim5Index = cursor.getColumnIndex("ulkeResim5");*/

            while (cursor.moveToNext()){
                ulkeAdiList.add(cursor.getString(ulkeAdiIndex));
                ulkeBaskentiList.add(cursor.getString(ulkeBaskentiIndex));
                ulkeHakkindaList.add(cursor.getString(ulkeHakkindaIndex));

                gelenResimByte = cursor.getBlob(ulkeResimIndex);
                gelenResim = BitmapFactory.decodeByteArray(gelenResimByte,0,gelenResimByte.length);
                ulkeResimList.add(gelenResim);

                /*gelenResimByte1 = cursor.getBlob(resim1Index);
                gelenResim1 = BitmapFactory.decodeByteArray(gelenResimByte1,0,gelenResimByte.length);
                resim1List.add(gelenResim1);

                gelenResimByte2 = cursor.getBlob(resim2Index);
                gelenResim2 = BitmapFactory.decodeByteArray(gelenResimByte2,0,gelenResimByte.length);
                resim2List.add(gelenResim2);

                gelenResimByte3 = cursor.getBlob(resim3Index);
                gelenResim3 = BitmapFactory.decodeByteArray(gelenResimByte3,0,gelenResimByte.length);
                resim3List.add(gelenResim3);

                gelenResimByte4 = cursor.getBlob(resim4Index);
                gelenResim4 = BitmapFactory.decodeByteArray(gelenResimByte4,0,gelenResimByte.length);
                resim4List.add(gelenResim4);

                gelenResimByte5 = cursor.getBlob(resim5Index);
                gelenResim5 = BitmapFactory.decodeByteArray(gelenResimByte5,0,gelenResimByte.length);
                resim5List.add(gelenResim5);*/

            }
            cursor.close();

            for(int i = 0; i<ulkeAdiList.size();i++){
                Ulke ulke = new Ulke();
                ulke.setUlkeAdi(ulkeAdiList.get(i));
                ulke.setUlkeBaskenti(ulkeBaskentiList.get(i));
                ulke.setUlkeHakkinda(ulkeHakkindaList.get(i));
                ulke.setUlkeResim(ulkeResimList.get(i));
                /*ulke.setResim1(resim1List.get(i));
                ulke.setResim2(resim2List.get(i));
                ulke.setResim3(resim3List.get(i));
                ulke.setResim4(resim4List.get(i));
                ulke.setResim5(resim5List.get(i));*/

                ulkeList.add(ulke);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return ulkeList;


    }

}
