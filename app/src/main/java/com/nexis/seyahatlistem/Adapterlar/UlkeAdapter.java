package com.nexis.seyahatlistem.Adapterlar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nexis.seyahatlistem.R;
import com.nexis.seyahatlistem.Ulke;

import java.util.ArrayList;

public class UlkeAdapter extends RecyclerView.Adapter<UlkeAdapter.UlkeHolder> {
    private ArrayList<Ulke> ulkeList;
    private Context context;
    private OnItemClickListener listener;

    public UlkeAdapter(ArrayList<Ulke> ulkeList, Context context) {
        this.ulkeList = ulkeList;
        this.context = context;
    }

    @NonNull
    @Override
    public UlkeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.ulke_item,parent,false);
        return new UlkeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UlkeHolder holder, int position) {
        Ulke ulke = ulkeList.get(position);
        holder.setData(ulke);
    }

    @Override
    public int getItemCount() {
        return ulkeList.size();
    }

    class UlkeHolder extends RecyclerView.ViewHolder {
        TextView txtUlkeAdi,txtUlkeBaskenti,txtUlkeHakkinda;
        ImageView imgUlkeAnaResmi;

        public UlkeHolder(@NonNull View itemView) {
            super(itemView);

            txtUlkeAdi = (TextView)itemView.findViewById(R.id.ulke_item_txtUlkeAdi);
            txtUlkeBaskenti = (TextView)itemView.findViewById(R.id.ulke_item_txtUlkeBaskenti);
            txtUlkeHakkinda = (TextView)itemView.findViewById(R.id.ulke_item_txtUlkeHakkinda);
            imgUlkeAnaResmi = (ImageView)itemView.findViewById(R.id.ulke_item_UlkeAnaResmi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(ulkeList.get(position));
                    }
                }
            });

        }

        public void setData(Ulke ulke){
            this.txtUlkeAdi.setText(ulke.getUlkeAdi());
            this.txtUlkeBaskenti.setText(ulke.getUlkeBaskenti());
            this.txtUlkeHakkinda.setText(ulke.getUlkeHakkinda());
            this.imgUlkeAnaResmi.setImageBitmap(ulke.getUlkeResim());
        }

    }

    public interface OnItemClickListener{
        void onItemClick(Ulke ulke);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
