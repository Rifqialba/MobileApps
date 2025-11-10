package com.example.myprofileapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class KontakFragment extends Fragment {

    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private EditText etSearch;
    private List<Contact> contactList;

    // Warna untuk avatar
    private final int[] avatarColors = {
            0xFF2196F3, // Blue
            0xFF4CAF50, // Green
            0xFFF44336, // Red
            0xFFFF9800, // Orange
            0xFF9C27B0, // Purple
            0xFF795548, // Brown
            0xFF607D8B, // Blue Gray
            0xFF00BCD4, // Cyan
            0xFF8BC34A, // Light Green
            0xFFFFC107, // Amber
            0xFFE91E63, // Pink
            0xFF3F51B5, // Indigo
            0xFF009688, // Teal
            0xFF673AB7, // Deep Purple
            0xFFFF5722  // Deep Orange
    };

    public KontakFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kontak, container, false);

        initViews(view);
        setupContactData();
        setupRecyclerView();
        setupSearch();

        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView_kontak);
        etSearch = view.findViewById(R.id.et_search);
    }

    private void setupContactData() {
        contactList = new ArrayList<>();

        // Data 15 kontak statis
        contactList.add(new Contact("Ahmad Wijaya", "+62 812-3456-7890", "AW", avatarColors[0]));
        contactList.add(new Contact("Budi Santoso", "+62 813-4567-8901", "BS", avatarColors[1]));
        contactList.add(new Contact("Citra Lestari", "+62 814-5678-9012", "CL", avatarColors[2]));
        contactList.add(new Contact("Dian Permata", "+62 815-6789-0123", "DP", avatarColors[3]));
        contactList.add(new Contact("Eko Prasetyo", "+62 816-7890-1234", "EP", avatarColors[4]));
        contactList.add(new Contact("Fitri Anggraini", "+62 817-8901-2345", "FA", avatarColors[5]));
        contactList.add(new Contact("Guntur Wibowo", "+62 818-9012-3456", "GW", avatarColors[6]));
        contactList.add(new Contact("Hana Sari", "+62 819-0123-4567", "HS", avatarColors[7]));
        contactList.add(new Contact("Irfan Maulana", "+62 820-1234-5678", "IM", avatarColors[8]));
        contactList.add(new Contact("Jihan Putri", "+62 821-2345-6789", "JP", avatarColors[9]));
        contactList.add(new Contact("Khalid Rahman", "+62 822-3456-7890", "KR", avatarColors[10]));
        contactList.add(new Contact("Laras Sekar", "+62 823-4567-8901", "LS", avatarColors[11]));
        contactList.add(new Contact("Maya Sari", "+62 824-5678-9012", "MS", avatarColors[12]));
        contactList.add(new Contact("Nova Pratama", "+62 825-6789-0123", "NP", avatarColors[13]));
        contactList.add(new Contact("Oki Setiawan", "+62 826-7890-1234", "OS", avatarColors[14]));
    }

    private void setupRecyclerView() {
        adapter = new ContactAdapter(contactList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void setupSearch() {
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
}