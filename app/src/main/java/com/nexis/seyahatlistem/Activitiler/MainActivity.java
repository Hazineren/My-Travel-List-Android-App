package com.nexis.seyahatlistem.Activitiler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nexis.seyahatlistem.R;
import com.nexis.seyahatlistem.Ulke;
import com.nexis.seyahatlistem.Adapterlar.UlkeAdapter;
import com.nexis.seyahatlistem.UlkeDetayi;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private UlkeAdapter adapter;
    static public UlkeDetayi ulkeDetayi;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_country){
            Intent addCountryIntent = new Intent(this,AddCountryActivity.class);
            finish();
            startActivity(addCountryIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        adapter = new UlkeAdapter(Ulke.getData(this),this);

        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(this,1);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new GridManagerDecoration());
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new UlkeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Ulke ulke) {
                ulkeDetayi = new UlkeDetayi(ulke.getUlkeAdi(),ulke.getUlkeBaskenti(),ulke.getUlkeHakkinda(),ulke.getUlkeResim());

                Intent detayIntent = new Intent(MainActivity.this,DetailsActivity.class);
                startActivity(detayIntent);
            }
        });
    }

    private class GridManagerDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.bottom = 20;
        }
    }


}