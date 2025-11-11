# My Profile App 📱

Aplikasi Android lengkap yang dibangun dengan Java dan Android Studio, menampilkan berbagai fitur dalam satu aplikasi yang terintegrasi.

## 🎯 Fitur Utama

- **✨ Splash Screen** - Intro elegan selama 5 detik dengan profil developer
- **👤 Biodata Diri** - Formulir informasi pribadi lengkap dengan validasi
- **📞 Daftar Kontak** - 15 kontak statis dengan fitur pencarian dan telepon
- **🧮 Kalkulator** - Kalkulator lengkap dengan operasi dasar dan lanjutan
- **🌤️ Info Cuaca** - Dashboard cuaca modern dengan animasi dan prakiraan
- **📰 Berita Terkini** - Artikel berita dengan filter kategori dan pencarian

## 📸 Screenshot Aplikasi

| Splash Screen | Form Biodata | Daftar Kontak |
|---------------|--------------|---------------|
| <img src="MyProfilApp/screenshots/Splash.png" width="200" alt="Splash Screen"> | <img src="MyProfilAppscreenshots/Biodata.png" width="200" alt="Form Biodata"> | <img src="MyProfilAppscreenshots/Kontak.png" width="200" alt="Daftar Kontak"> |

| Kalkulator | Info Cuaca | Berita |
|------------|------------|--------|
| <img src="MyProfilApp/screenshots/Kalkulator.png" width="200" alt="Kalkulator"> | <img src="MyProfilApp/screenshots/Cuaca.png" width="200" alt="Info Cuaca"> | <img src="MyProfilApp/screenshots/Berita.png" width="200" alt="Berita"> |

## 🛠️ Teknologi yang Digunakan

- **Bahasa Pemrograman**: Java
- **Platform**: Android
- **Minimum SDK**: API 21 (Android 5.0 Lollipop)
- **Arsitektur**: Fragment-based dengan Bottom Navigation
- **UI/UX**: Material Design 3
- **Layout**: ConstraintLayout, LinearLayout, CardView
- **Build Tool**: Gradle

## 🚀 Instalasi dan Menjalankan

### Prasyarat
- Android Studio Arctic Fox atau versi lebih baru
- Android SDK API 21+
- Java JDK 8+
- Device/Emulator dengan minimum 2GB RAM

### Langkah-langkah Instalasi

1. **Clone Repository**
   ```bash
   git clone https://github.com/Rifqialba/MobileApps.git
   
2. **Buka Project di Android Studio**

   File → Open → Pilih folder project

3. **Build Project**

   Build → Make Project (Ctrl + F9)

4. **Jalankan di Emulator/Device**

   Run → Run 'app' (Shift + F10)

### Atau Build APK Manual
  ```bash
  ./gradlew assembleDebug
  # APK akan tersedia di app/build/outputs/apk/debug/
  ```
## 📁 Struktur Project

MobileApps/
├── app/
│   └── src/main/
│       ├── java/com/example/myprofileapp/
│       │   ├── MainActivity.java          # Splash Screen Activity
│       │   ├── DashboardActivity.java     # Main Navigation Activity
│       │   ├── BiodataFragment.java       # Form Biodata & Validasi
│       │   ├── KontakFragment.java        # Manajemen Kontak
│       │   ├── KalkulatorFragment.java    # Logika Kalkulator
│       │   ├── CuacaFragment.java         # Dashboard Cuaca
│       │   ├── BeritaFragment.java        # News Feed & Filter
│       │   ├── Contact.java               # Model Data Kontak
│       │   ├── ContactAdapter.java        # Adapter RecyclerView Kontak
│       │   ├── Berita.java                # Model Data Berita
│       │   └── BeritaAdapter.java         # Adapter RecyclerView Berita
│       └── res/
│           ├── layout/                    # XML Layout Files
│           ├── drawable/                  # Icons, Shapes, Backgrounds
│           ├── menu/                      # Bottom Navigation Menu
│           ├── values/                    # Colors, Strings, Styles
│           └── mipmap/                    # App Icons
├── screenshots/                           # Application Screenshots
├── build.gradle                           # App Level Gradle Config
├── README.md                              # Documentation
└── settings.gradle                        # Project Settings

## ✨ Detail Fitur Lengkap

### 🧍‍♂️ Biodata Fragment
- **Form Input** dengan validasi real-time
- **Jenis Kelamin** - Radio button selection
- **Program Studi** - Dropdown spinner
- **Tanggal Lahir** - Date picker dialog
- **Preview Data** - Tampilan hasil input
- **Validasi** - Email, required fields, format checking

### 📞 Kontak Fragment
- **RecyclerView** dengan 15 data kontak statis
- **Search Functionality** - Pencarian nama dan nomor
- **Call Intent** - Integrasi dengan dialer
- **Circular Avatar** - Design modern dengan inisial
- **Card Layout** - Tampilan yang terorganisir

### 🧮 Kalkulator Fragment
- **Operasi Dasar** - Penambahan, pengurangan, perkalian, pembagian
- **Fungsi Lanjutan** - Kuadrat (x²), Akar kuadrat (√)
- **Error Handling** - Pembagian dengan nol, input invalid
- **Dark Theme** - Tampilan modern dengan color scheme
- **History Display** - Riwayat perhitungan

### 🌤️ Cuaca Fragment
- **Weather Dashboard** - Informasi suhu, kelembapan, angin
- **5-Day Forecast** - Prakiraan cuaca 5 hari ke depan
- **Animated Icons** - Icon cuaca yang dinamis
- **Refresh Mechanism** - Update data dengan animasi
- **Responsive Design** - Layout adaptif berbagai screen size

### 📰 Berita Fragment
- **Category Filter** - Filter berdasarkan kategori
- **Search Feature** - Pencarian judul dan konten berita
- **Card Design** - Tampilan berita modern
- **Article Metadata** - Penulis, tanggal, waktu baca
- **15 Sample News** - Data berita berbagai kategori

## 🎨 Design & User Experience

### Material Design 3
- ✅ **Color Scheme** - Konsisten dengan primary color #2196F3
- ✅ **Typography** - Readable fonts dengan hierarchy yang jelas
- ✅ **Elevation** - Shadow effects untuk depth
- ✅ **Rounded Corners** - Modern look dengan border radius
- ✅ **Consistent Spacing** - Padding dan margin yang teratur

### Responsive Design
- 📱 **Multi-Screen Support** - Berbagai ukuran layar
- 🔄 **Orientation** - Portrait dan landscape ready
- ♿ **Accessibility** - Contrast ratio yang baik
- ⚡ **Performance** - Optimized layout loading

## 🔧 Konfigurasi Gradle

### Dependencies Utama:
```gradle
dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.9.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.recyclerview:recyclerview:1.3.1'
    implementation 'androidx.cardview:cardview:1.0.0'
    implementation 'androidx.fragment:fragment:1.6.1'
}
```

<div align="center">
⭐ Jika project ini membantu, jangan lupa beri star di repository!

Dibuat dengan ❤️ menggunakan Android Studio dan Java

</div> ```




