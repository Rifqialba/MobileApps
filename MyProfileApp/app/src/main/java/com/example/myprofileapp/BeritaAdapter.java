package com.example.myprofileapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> implements Filterable {

    private List<Berita> beritaList;
    private List<Berita> beritaListFull;

    public BeritaAdapter(List<Berita> beritaList) {
        this.beritaList = beritaList;
        this.beritaListFull = new ArrayList<>(beritaList);
    }

    @NonNull
    @Override
    public BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berita, parent, false);
        return new BeritaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaViewHolder holder, int position) {
        Berita berita = beritaList.get(position);

        holder.tvJudul.setText(berita.getJudul());
        holder.tvRingkasan.setText(berita.getRingkasan());
        holder.tvKategori.setText(berita.getKategori());
        holder.tvPenulis.setText(berita.getPenulis());
        holder.tvTanggal.setText(berita.getTanggal());
        holder.tvWaktuBaca.setText(berita.getWaktuBaca());

        // Set gambar (dalam implementasi nyata, gunakan library seperti Glide/Picasso)
        holder.ivGambar.setImageResource(berita.getGambar());

        // Set background color berdasarkan kategori
        setCategoryColor(holder.tvKategori, berita.getKategori());
    }

    private void setCategoryColor(TextView tvKategori, String kategori) {
        int color;
        switch (kategori.toLowerCase()) {
            case "teknologi":
                color = 0xFF2196F3;
                break;
            case "olahraga":
                color = 0xFF4CAF50;
                break;
            case "politik":
                color = 0xFFF44336;
                break;
            case "ekonomi":
                color = 0xFFFF9800;
                break;
            case "hiburan":
                color = 0xFF9C27B0;
                break;
            case "kesehatan":
                color = 0xFF00BCD4;
                break;
            default:
                color = 0xFF607D8B;
        }
        tvKategori.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return beritaList.size();
    }

    @Override
    public Filter getFilter() {
        return beritaFilter;
    }

    private Filter beritaFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Berita> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(beritaListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Berita berita : beritaListFull) {
                    if (berita.getJudul().toLowerCase().contains(filterPattern) ||
                            berita.getRingkasan().toLowerCase().contains(filterPattern) ||
                            berita.getKategori().toLowerCase().contains(filterPattern)) {
                        filteredList.add(berita);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            beritaList.clear();
            beritaList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public void filterByCategory(String category) {
        List<Berita> filteredList = new ArrayList<>();

        if (category.equals("Semua")) {
            filteredList.addAll(beritaListFull);
        } else {
            for (Berita berita : beritaListFull) {
                if (berita.getKategori().equals(category)) {
                    filteredList.add(berita);
                }
            }
        }

        beritaList.clear();
        beritaList.addAll(filteredList);
        notifyDataSetChanged();
    }

    public static class BeritaViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGambar;
        TextView tvKategori, tvJudul, tvRingkasan, tvPenulis, tvTanggal, tvWaktuBaca;

        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);
            ivGambar = itemView.findViewById(R.id.iv_news_image);
            tvKategori = itemView.findViewById(R.id.tv_category);
            tvJudul = itemView.findViewById(R.id.tv_title);
            tvRingkasan = itemView.findViewById(R.id.tv_summary);
            tvPenulis = itemView.findViewById(R.id.tv_author);
            tvTanggal = itemView.findViewById(R.id.tv_date);
            tvWaktuBaca = itemView.findViewById(R.id.tv_read_time);
        }
    }
}