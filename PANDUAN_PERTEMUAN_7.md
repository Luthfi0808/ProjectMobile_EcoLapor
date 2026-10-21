# Pertemuan 7 — EcoLapor

Implementasi mengikuti Modul Praktikum Pertemuan 7 yang diberikan.
Nama dan isi menu disesuaikan dengan EcoLapor.

## Hubungan dengan modul

| Bagian modul | Implementasi EcoLapor |
| --- | --- |
| Hal. 5–7: FrameLayout, BottomNavigationView, replaceFragment | MainActivity.kt, activity_main.xml, bottom_nav_menu.xml |
| Hal. 8–10: Fragment dengan ViewBinding dan toolbar | HomeFragment, JelajahFragment, AktivitasFragment |
| Hal. 13–14: SimpleAdapter + simple_list_item_2 | JelajahFragment, 10 kategori lingkungan dengan deskripsi |
| Hal. 15–18: model, custom ArrayAdapter, getView, Glide | LaporanModel, LaporanAdapter, item_laporan.xml |
| Hal. 18: klik baris di adapter memakai Snackbar | LaporanAdapter.getView() |
| Hal. 19: sapaan username dari SharedPreferences | LoginActivity menyimpan username; HomeFragment membacanya |
| Hal. 19: minimal 8 data dengan deskripsi | Jelajah berisi 10 kategori |

MainActivity menjalankan peran BaseActivity pada modul. Nama MainActivity dipertahankan
agar intent dari Login dan Splash tetap menggunakan tujuan yang sama.
Isi beranda lama disalin ke fragment_home.xml dan aksi tombolnya dipindahkan ke HomeFragment.
Tidak perlu menghapus halaman Detail atau membuat BaseActivity kedua.

## Menu

- **Beranda:** tampilan lama, sapaan username, tombol Tentang EcoLapor, dan dialog LogOut.
- **Jelajah:** jenis masalah lingkungan. Setiap baris memakai layout bawaan Android
  `simple_list_item_2`: judul di `text1`, deskripsi di `text2`. Ketukan menampilkan Toast.
- **Aktivitas:** 10 contoh laporan dengan judul, lokasi, ilustrasi, dan status.
  Ketukan menampilkan Snackbar. Ini data contoh praktikum, bukan laporan dari server.

Modul memperbolehkan ArrayAdapter **atau** SimpleAdapter untuk daftar sederhana pada checklist.
Di sini dipilih SimpleAdapter supaya judul dan deskripsi tampil bersamaan.
Custom adapter laporan tetap merupakan turunan ArrayAdapter<LaporanModel> sesuai tahap 4.

## Penjelasan singkat

1. MainActivity memuat HomeFragment pertama kali. Menu bawah memanggil replaceFragment()
   sesuai ID menu. Transaksi memakai replace dan commit, tanpa addToBackStack.
2. ViewBinding Fragment dibuat di onCreateView, dipakai di onViewCreated, dan
   dikosongkan di onDestroyView agar tidak menyimpan tampilan yang sudah ditutup.
3. SimpleAdapter menghubungkan key title dan desc ke ID text1 dan text2.
4. LaporanModel menyimpan data satu laporan. LaporanAdapter.getView mengisi satu baris
   melalui ItemLaporanBinding, lalu Glide 4.16.0 memuat foto dari URL.
5. Glide membutuhkan izin INTERNET. Jika gambar belum termuat atau koneksi gagal,
   ilustrasi lokal tetap tampil. Foto dari Unsplash hanya ilustrasi lingkungan.
6. Login tetap memeriksa akun lokal langsung dengan if–else, lalu menyimpan isLogin
   dan username. Splash membaca isLogin. Logout mengubah isLogin menjadi false.

## Demo untuk UTS

1. Login dengan username `ecolapor` dan password `eco123`.
2. Tunjukkan sapaan “Halo, ecolapor” dan tiga menu bawah.
3. Buka Jelajah, scroll sampai kategori terakhir, lalu ketuk kategori.
4. Buka Aktivitas, scroll daftar, dan ketuk laporan untuk menampilkan Snackbar.
5. Kembali ke Beranda, buka Tentang EcoLapor, lalu kembali.
6. Pilih LogOut → Tidak; pastikan tetap di beranda. Pilih Ya untuk kembali ke Login.
7. Buka ulang aplikasi setelah login untuk menunjukkan SharedPreferences.
8. Putar layar pada tab Aktivitas; tab aktif tetap dipulihkan.

Komponen sebelumnya tetap digunakan: ViewBinding, Toolbar, CardView login, SplashScreen
dengan layout XML, intent, SharedPreferences, dan AlertDialog.
