package com.nexis.seyahatlistem.Activitiler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nexis.seyahatlistem.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddCountryActivity extends AppCompatActivity {

    private EditText editTxtUlkeIsmi, editTxtUlkeBaskenti, editTxtUlkeHakkında;
    private CheckBox cBoxResimYukle;
    private ImageView imgUlkeAnaResmi, photo1, photo2, photo3, photo4, photo5;
    private Button btnUlkeKaydet;
    private String ulkeIsmi, ulkeBaskenti, ulkeHakkinda,tiklananResim;
    private int imgIzinAlmaKodu=0,imgIzinVerildiKodu=1,tiklanmaKodu=0;
    private Bitmap secilenResim,kucultulenResim,defaultResim;

    private void init() {
        editTxtUlkeIsmi = (EditText) findViewById(R.id.editTxtulkeIsmi);
        editTxtUlkeBaskenti = (EditText) findViewById(R.id.editTxtulkeBaskenti);
        editTxtUlkeHakkında = (EditText) findViewById(R.id.editTxtulkeHakkinda);
        cBoxResimYukle = (CheckBox) findViewById(R.id.cBoxResimYukle);
        imgUlkeAnaResmi = (ImageView) findViewById(R.id.ulkeAnaResmi);
        btnUlkeKaydet = (Button)findViewById(R.id.btnUlkeKaydet);
        photo1 = (ImageView) findViewById(R.id.photo1);
        photo2 = (ImageView) findViewById(R.id.photo2);
        photo3 = (ImageView) findViewById(R.id.photo3);
        photo4 = (ImageView) findViewById(R.id.photo4);
        photo5 = (ImageView) findViewById(R.id.photo5);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_country);

        init();


            photo1.setVisibility(View.INVISIBLE);
            photo2.setVisibility(View.INVISIBLE);
            photo3.setVisibility(View.INVISIBLE);
            photo4.setVisibility(View.INVISIBLE);
            photo5.setVisibility(View.INVISIBLE);

            cBoxResimYukle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(cBoxResimYukle.isChecked()){
                        photo1.setVisibility(View.VISIBLE);
                        photo2.setVisibility(View.VISIBLE);
                        photo3.setVisibility(View.VISIBLE);
                        photo4.setVisibility(View.VISIBLE);
                        photo5.setVisibility(View.VISIBLE);
                    }else{
                        photo1.setVisibility(View.INVISIBLE);
                        photo2.setVisibility(View.INVISIBLE);
                        photo3.setVisibility(View.INVISIBLE);
                        photo4.setVisibility(View.INVISIBLE);
                        photo5.setVisibility(View.INVISIBLE);
                    }
                }
            });

    }



    public void ulkeKaydet(View v) {
        ulkeIsmi = editTxtUlkeIsmi.getText().toString();
        ulkeBaskenti = editTxtUlkeBaskenti.getText().toString();
        ulkeHakkinda = editTxtUlkeHakkında.getText().toString();
        byte[] kayitEdilecekResim=null;
        byte[] resimler1 = null;
        byte[] resimler2 = null;
        byte[] resimler3 = null;
        byte[] resimler4 = null;
        byte[] resimler5 = null;

        if (!TextUtils.isEmpty(ulkeIsmi)) {
            if (!TextUtils.isEmpty(ulkeBaskenti)) {
                if (!TextUtils.isEmpty(ulkeHakkinda)) {

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    kucultulenResim = resimKucult(secilenResim);
                    kucultulenResim.compress(Bitmap.CompressFormat.PNG,75,outputStream);
                    if(tiklanmaKodu==0){
                        kayitEdilecekResim = outputStream.toByteArray();
                    }
                     if((tiklanmaKodu==1) && cBoxResimYukle.isChecked()){
                         resimler1 = outputStream.toByteArray();
                     }
                     if(tiklanmaKodu==2 && cBoxResimYukle.isChecked()){
                         resimler2 = outputStream.toByteArray();
                     }
                     if(tiklanmaKodu==3 && cBoxResimYukle.isChecked()){
                         resimler3 = outputStream.toByteArray();
                     }
                     if(tiklanmaKodu==4 && cBoxResimYukle.isChecked()){
                         resimler4 = outputStream.toByteArray();
                     }
                     if(tiklanmaKodu==5 && cBoxResimYukle.isChecked()){
                         resimler5 = outputStream.toByteArray();
                     }




                    try {
                        SQLiteDatabase database = this.openOrCreateDatabase("Ulkeler",MODE_PRIVATE,null);
                        database.execSQL("CREATE TABLE IF NOT EXISTS ulkeler (id INTEGER PRIMARY KEY, ulkeAdi VARCHAR, ulkeBaskent VARCHAR, ulkeHakkinda VARCHAR, ulkeAnaResim BLOB)");

                        String sqlQuery = "INSERT INTO ulkeler (ulkeAdi, ulkeBaskent, ulkeHakkinda, ulkeAnaResim) VALUES (?, ?, ?, ?)";
                        SQLiteStatement statement = database.compileStatement(sqlQuery);
                        statement.bindString(1,ulkeIsmi);
                        statement.bindString(2,ulkeBaskenti);
                        statement.bindString(3,ulkeHakkinda);
                        statement.bindBlob(4,kayitEdilecekResim);
                        statement.execute();

                        nesneleriTemizle();
                        showToast("Kayıt Başarıyla Eklendi");
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                } else {
                    showToast("Ülke Hakkındaki Bilgiler Boş Olamaz");
                }
            } else {
                showToast("Ülke Başkenti Boş Olamaz");
            }
        } else {
            showToast("Ülke İsmi Boş Olamaz");
        }
    }

    private Bitmap resimKucult(Bitmap resim){
        return Bitmap.createScaledBitmap(resim,100,110,true);
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void nesneleriTemizle(){
        editTxtUlkeIsmi.setText("");
        editTxtUlkeBaskenti.setText("");
        editTxtUlkeHakkında.setText("");
        defaultResim = BitmapFactory.decodeResource(this.getResources(),R.drawable.ic_photoadd);
        imgUlkeAnaResmi.setImageResource(R.drawable.ic_photoadd);
        photo2.setImageResource(R.drawable.ic_photoadd);
        photo3.setImageResource(R.drawable.ic_photoadd);
        photo4.setImageResource(R.drawable.ic_photoadd);
        photo5.setImageResource(R.drawable.ic_photoadd);
        //btnUlkeKaydet.setEnabled(false);
    }

    public void resimSec(View v){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},imgIzinAlmaKodu);
        }else{
            Intent resimAl = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(resimAl,imgIzinVerildiKodu);
        }

        switch (v.getId()){
            case R.id.ulkeAnaResmi:
                tiklanmaKodu=0;
                //resmiSetle(imgUlkeAnaResmi,secilenResim);
                break;
            case R.id.photo1:
                tiklanmaKodu=1;
                //resmiSetle(photo1,secilenResim);
                break;
            case R.id.photo2:
                tiklanmaKodu=2;
                //resmiSetle(photo2,secilenResim);
                break;

            case R.id.photo3:
                tiklanmaKodu=3;
                //resmiSetle(photo3,secilenResim);
                break;

            case R.id.photo4:
                tiklanmaKodu=4;
                //resmiSetle(photo4,secilenResim);
                break;

            case R.id.photo5:
                tiklanmaKodu=5;
                //resmiSetle(photo5,secilenResim);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == imgIzinAlmaKodu){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent resimAl = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(resimAl,imgIzinVerildiKodu);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == imgIzinVerildiKodu){
            if(resultCode == RESULT_OK && data != null){
                Uri resimUri = data.getData();

                try {
                    if(Build.VERSION.SDK_INT >= 28){
                        ImageDecoder.Source resimSource = ImageDecoder.createSource(this.getContentResolver(),resimUri);
                        secilenResim = ImageDecoder.decodeBitmap(resimSource);
                        resmiSetle(imgUlkeAnaResmi,secilenResim);

                    }else{
                        secilenResim = MediaStore.Images.Media.getBitmap(this.getContentResolver(),resimUri);
                        //resmiSetle(imgUlkeAnaResmi,secilenResim);
                        resmiSetle(imgUlkeAnaResmi,secilenResim);
                        /*if(tiklanmaKodu==0){

                            btnUlkeKaydet.setEnabled(true);
                        }else if(tiklanmaKodu==1){
                            resmiSetle(photo1,secilenResim);
                        }else if(tiklanmaKodu==2){
                            resmiSetle(photo2,secilenResim);
                        }else if(tiklanmaKodu==3){
                            resmiSetle(photo3,secilenResim);
                        }else if(tiklanmaKodu==4){
                            resmiSetle(photo4,secilenResim);
                        }else if(tiklanmaKodu==5){
                            resmiSetle(photo5,secilenResim);
                        }*/
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void resmiSetle(ImageView img,Bitmap resim){
        img.setImageBitmap(resim);
    }

    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent(this,MainActivity.class);
        finish();
        startActivity(backIntent);
    }
}