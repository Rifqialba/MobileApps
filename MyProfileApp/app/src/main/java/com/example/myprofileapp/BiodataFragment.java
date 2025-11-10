package com.example.myprofileapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BiodataFragment extends Fragment {

    private EditText etNama, etNim, etTanggalLahir, etAlamat, etEmail;
    private RadioGroup rgGender;
    private Spinner spinnerProdi;
    private Button btnSimpan;
    private LinearLayout layoutPreview;
    private TextView tvPreview;

    private Calendar calendar;
    private SimpleDateFormat dateFormatter;

    public BiodataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_biodata, container, false);

        // Initialize
        initViews(view);
        setupSpinner();
        setupDatePicker();
        setupButton();

        return view;
    }

    private void initViews(View view) {
        etNama = view.findViewById(R.id.et_nama);
        etNim = view.findViewById(R.id.et_nim);
        etTanggalLahir = view.findViewById(R.id.et_tanggal_lahir);
        etAlamat = view.findViewById(R.id.et_alamat);
        etEmail = view.findViewById(R.id.et_email);
        rgGender = view.findViewById(R.id.rg_gender);
        spinnerProdi = view.findViewById(R.id.spinner_prodi);
        btnSimpan = view.findViewById(R.id.btn_simpan);
        layoutPreview = view.findViewById(R.id.layout_preview);
        tvPreview = view.findViewById(R.id.tv_preview);

        calendar = Calendar.getInstance();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    }

    private void setupSpinner() {
        // Data program studi
        String[] prodiList = {
                "Pilih Program Studi",
                "Teknik Informatika",
                "Sistem Informasi",
                "Teknik Komputer",
                "Manajemen Informatika",
                "Ilmu Komputer",
                "Teknologi Informasi"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                prodiList
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProdi.setAdapter(adapter);
    }

    private void setupDatePicker() {
        etTanggalLahir.setOnClickListener(v -> showDatePicker());
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    etTanggalLahir.setText(dateFormatter.format(calendar.getTime()));
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        // Set max date to today
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void setupButton() {
        btnSimpan.setOnClickListener(v -> validateAndSave());
    }

    private void validateAndSave() {
        String nama = etNama.getText().toString().trim();
        String nim = etNim.getText().toString().trim();
        String tanggalLahir = etTanggalLahir.getText().toString().trim();
        String alamat = etAlamat.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        // Validasi
        if (nama.isEmpty()) {
            showToast("Nama lengkap harus diisi");
            return;
        }

        if (nim.isEmpty()) {
            showToast("NIM harus diisi");
            return;
        }

        if (rgGender.getCheckedRadioButtonId() == -1) {
            showToast("Pilih jenis kelamin");
            return;
        }

        if (spinnerProdi.getSelectedItemPosition() == 0) {
            showToast("Pilih program studi");
            return;
        }

        if (tanggalLahir.isEmpty()) {
            showToast("Pilih tanggal lahir");
            return;
        }

        if (alamat.isEmpty()) {
            showToast("Alamat harus diisi");
            return;
        }

        if (email.isEmpty()) {
            showToast("Email harus diisi");
            return;
        }

        // Get selected gender
        RadioButton selectedGender = requireView().findViewById(rgGender.getCheckedRadioButtonId());
        String gender = selectedGender.getText().toString();

        // Get selected prodi
        String prodi = spinnerProdi.getSelectedItem().toString();

        // Show preview
        showPreview(nama, nim, gender, prodi, tanggalLahir, alamat, email);

        showToast("Biodata berhasil disimpan!");
    }

    private void showPreview(String nama, String nim, String gender, String prodi,
                             String tanggalLahir, String alamat, String email) {
        String previewText = "Nama: " + nama + "\n" +
                "NIM: " + nim + "\n" +
                "Jenis Kelamin: " + gender + "\n" +
                "Program Studi: " + prodi + "\n" +
                "Tanggal Lahir: " + tanggalLahir + "\n" +
                "Alamat: " + alamat + "\n" +
                "Email: " + email;

        tvPreview.setText(previewText);
        layoutPreview.setVisibility(View.VISIBLE);
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}