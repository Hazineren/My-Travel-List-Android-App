package com.nexis.seyahatlistem.Activitiler;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexis.seyahatlistem.R;

public class DetailsActivity extends AppCompatActivity {
    private ImageView imgUlkeResim;
    private TextView txtUlkeAdi,txtUlkeBaskenti,txtUlkeHakkinda;
    private String ulkeAdi,ulkeBaskenti,ulkeHakkinda;
    private Bitmap ulkeResmi;

    private void init(){
        imgUlkeResim = (ImageView)findViewById(R.id.detailsActivity_ImageView);
        txtUlkeAdi = (TextView)findViewById(R.id.detailsActivity_txtUlkeAdi);
        txtUlkeBaskenti = (TextView)findViewById(R.id.detailsActivity_txtUlkeBaskenti);
        txtUlkeHakkinda = (TextView)findViewById(R.id.detailsActivity_txtUlkeHakkinda);

        ulkeAdi = MainActivity.ulkeDetayi.getUlkeAdi();
        ulkeBaskenti = MainActivity.ulkeDetayi.getUlkeBaskenti();
        ulkeHakkinda = MainActivity.ulkeDetayi.getUlkeHakkinda();
        ulkeResmi = MainActivity.ulkeDetayi.getUlkeResmi();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        init();

        if(!TextUtils.isEmpty(ulkeAdi) && !TextUtils.isEmpty(ulkeBaskenti) && !TextUtils.isEmpty(ulkeHakkinda)){
           txtUlkeAdi.setText(ulkeAdi);
           txtUlkeBaskenti.setText(ulkeBaskenti);
           txtUlkeHakkinda.setText(ulkeHakkinda);
           imgUlkeResim.setImageBitmap(ulkeResmi);
        }
    }
}