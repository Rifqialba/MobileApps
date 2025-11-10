package com.example.myprofileapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BeritaFragment extends Fragment {

    private RecyclerView recyclerView;
    private BeritaAdapter adapter;
    private EditText etSearch;
    private Button btnSearch;
    private LinearLayout layoutCategories;

    private List<Berita> beritaList;
    private String[] categories = {"Semua", "Teknologi", "Olahraga", "Politik", "Ekonomi", "Hiburan", "Kesehatan"};

    public BeritaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_berita, container, false);

        initViews(view);
        setupBeritaData();
        setupRecyclerView();
        setupSearch();
        setupCategories();

        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView_berita);
        etSearch = view.findViewById(R.id.et_search_news);
        btnSearch = view.findViewById(R.id.btn_search);
        layoutCategories = view.findViewById(R.id.layout_categories);
    }

    private void setupBeritaData() {
        beritaList = new ArrayList<>();

        // Data berita statis (15 berita)
        beritaList.add(new Berita("1",
                "Teknologi 5G Resmi Diluncurkan di Indonesia",
                "Teknologi 5G telah resmi diluncurkan di beberapa kota besar Indonesia. Kecepatan internet akan meningkat signifikan.",
                "Teknologi",
                "Ahmad Tech",
                "25 Des 2023",
                "3 min",
                R.drawable.news_placeholder));

        beritaList.add(new Berita("2",
                "Timnas Indonesia Juara Piala AFF 2023",
                "Timnas Indonesia berhasil menjadi juara Piala AFF 2023 setelah mengalahkan Thailand di final.",
                "Olahraga",
                "Budi Sport",
                "24 Des 2023",
                "5 min",
                R.drawable.news_placeholder));

        beritaList.add(new Berita("3",
                "Pemerintah Umumkan Bantuan Sosial Tahap Baru",
                "Pemerintah mengumumkan bantuan sosial tahap baru untuk masyarakat yang membutuhkan di seluruh Indonesia.",
                "Politik",
                "Citra News",
                "23 Des 2023",
                "4 min",
                R.drawable.news_placeholder));

        beritaList.add(new Berita("4",
                "Harga Bahan Pokok Stabil di Akhir Tahun",
                "Kementerian Perdagangan memastikan harga bahan pokok tetap stabil selama liburan akhir tahun.",
                "Ekonomi",
                "Dian Ekonom",
                "22 Des 2023",
                "3 min",
                R.drawable.news_placeholder));

        beritaList.add(new Berita("5",
                "Konser Musik Terbesar Akan Digunakan di Jakarta",
                "Konser musik terbesar tahun ini akan digelar di Jakarta dengan menampilkan artis-artis ternama.",
                "Hiburan",
                "Eko Entertainment",
                "21 Des 2023",
                "2 min",
                R.drawable.news_placeholder));

        beritaList.add(new Berita("6",
                "Vaksin Booster COVID-19 Tersedia untuk Umum",
                "Vaksin booster COVID-19 kini tersedia untuk umum di seluruh puskesmas dan rumah sakit.",
                "Kesehatan",
                "Fitri Health",
                "20 Des 2023",
                "4 min",
                R.drawable.news_placeholder));

        beritaList.add(new Berita("7",
                "Startup Lokal Raih Pendanaan Rp 500 Miliar",
                "Startup teknologi lokal berhasil meraih pendanaan seri B senilai Rp 500 miliar dari investor asing.",
                "Teknologi",
                "Guntur Startup",
                "19 Des 2023",
                "3 min",
                R.drawable.news_placeholder));

        beritaList.add(new Berita("8",
                "Pembangunan Stadion Baru Capai 80%",
                "Pembangunan stadion baru untuk Piala Dunia U-20 telah mencapai 80% dan akan selesai tepat waktu.",
                "Olahraga",
                "Hana Sport",
                "18 Des 2023",
                "2 min",
                R.drawable.news_placeholder));

        beritaList.add(new Berita("9",
                "Reformasi Birokrasi Dipercepat",
                "Pemerintah mempercepat reformasi birokrasi untuk meningkatkan pelayanan publik.",
                "Politik",
                "Irfan Politik",
                "17 Des 2023",
                "5 min",
                R.drawable.news_placeholder));

        beritaList.add(new Berita("10",
                "Ekspor Komoditas Naik 15%",
                "Nilai ekspor komoditas Indonesia naik 15% dibandingkan tahun sebelumnya.",
                "Ekonomi",
                "Jihan Trade",
                "16 Des 2023",
                "3 min",
                R.drawable.news_placeholder));

        // Tambahkan 5 berita lagi...
        beritaList.add(new Berita("11",
                "Film Indonesia Raih Penghargaan Internasional",
                "Film produksi Indonesia berhasil meraih penghargaan di festival film internasional.",
                "Hiburan",
                "Khalid Film",
                "15 Des 2023",
                "4 min",
                R.drawable.news_placeholder));

        beritaList.add(new Berita("12",
                "Penemuan Baru dalam Pengobatan Kanker",
                "Peneliti Indonesia berhasil menemukan metode baru dalam pengobatan kanker.",
                "Kesehatan",
                "Laras Medis",
                "14 Des 2023",
                "5 min",
                R.drawable.news_placeholder));

        beritaList.add(new Berita("13",
                "AI Revolution Mengubah Industri",
                "Revolusi kecerdasan buatan mengubah berbagai industri di Indonesia.",
                "Teknologi",
                "Maya Tech",
                "13 Des 2023",
                "4 min",
                R.drawable.news_placeholder));

        beritaList.add(new Berita("14",
                "Atlet Indonesia Raih Medali Emas",
                "Atlet Indonesia berhasil meraih medali emas dalam kejuaraan dunia.",
                "Olahraga",
                "Nova Sport",
                "12 Des 2023",
                "3 min",
                R.drawable.news_placeholder));

        beritaList.add(new Berita("15",
                "Kebijakan Baru untuk UMKM",
                "Pemerintah mengeluarkan kebijakan baru untuk mendukung pertumbuhan UMKM.",
                "Ekonomi",
                "Oki Business",
                "11 Des 2023",
                "4 min",
                R.drawable.news_placeholder));
    }

    private void setupRecyclerView() {
        adapter = new BeritaAdapter(beritaList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void setupSearch() {
        btnSearch.setOnClickListener(v -> {
            String query = etSearch.getText().toString().trim();
            adapter.getFilter().filter(query);
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupCategories() {
        for (String category : categories) {
            TextView categoryView = new TextView(getContext());
            categoryView.setText(category);
            categoryView.setPadding(16, 8, 16, 8);
            categoryView.setTextSize(14);
            categoryView.setBackgroundResource(R.drawable.category_item_background);
            categoryView.setTextColor(getResources().getColor(android.R.color.black));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 8, 0);
            categoryView.setLayoutParams(params);

            categoryView.setOnClickListener(v -> {
                // Reset all categories
                for (int i = 0; i < layoutCategories.getChildCount(); i++) {
                    View child = layoutCategories.getChildAt(i);
                    if (child instanceof TextView) {
                        child.setBackgroundResource(R.drawable.category_item_background);
                        ((TextView) child).setTextColor(getResources().getColor(android.R.color.black));
                    }
                }

                // Set selected category
                categoryView.setBackgroundResource(R.drawable.category_item_selected);
                categoryView.setTextColor(getResources().getColor(android.R.color.white));

                // Filter news by category
                adapter.filterByCategory(category);
            });

            layoutCategories.addView(categoryView);
        }
    }
}