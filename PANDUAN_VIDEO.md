# Panduan video praktikum EcoLapor

## Akun demo

- Username: `ecolapor`
- Password: `eco123`
- Pengecekan akun dilakukan secara lokal untuk praktikum.

## Penjelasan kode

### LoginActivity.kt

Input username dan password diambil menggunakan ViewBinding saat tombol Login ditekan.
Jika kosong, kolom menampilkan pesan wajib diisi.

Kondisi `username == "ecolapor" && password == "eco123"` mengecek kedua input.
Operator `&&` berarti username DAN password harus benar. Perbandingan membedakan huruf besar dan kecil.
`trim()` membuang spasi di awal/akhir username; password diperiksa persis sesuai input.

Jika benar, buka SharedPreferences bernama `ecolapor_session` dengan `MODE_PRIVATE`.
Gunakan editor untuk menyimpan `isLogin = true`, lalu `apply()` untuk menerapkan perubahan.
Intent membuka MainActivity dan `finish()` menutup LoginActivity.
Jika salah, tetap di Login dan tampilkan pesan kesalahan. Password tidak disimpan di SharedPreferences.

### SplashActivity.kt dan activity_splash.xml

Tampilan splash dibuat di `app/src/main/res/layout/activity_splash.xml`.
Gambar diatur melalui `android:src` pada ImageView `imgLogo`.
Ukuran gambar diatur melalui `layout_width` dan `layout_height`.
Saat ini gambarnya memakai `@drawable/ic_ecolapor`; bisa diganti melalui editor layout Android Studio.

SplashActivity menampilkan layout menggunakan ActivitySplashBinding.
`Handler.postDelayed` menjalankan perpindahan setelah 2000 milidetik (2 detik).
SharedPreferences dibaca langsung di activity dengan `getBoolean("isLogin", false)`.
Jika true, Intent membuka MainActivity. Jika false, Intent membuka LoginActivity.
`finish()` menutup SplashActivity supaya tombol Back tidak kembali ke splash.
`removeCallbacksAndMessages(null)` di onDestroy membatalkan timer jika splash sudah ditutup.

### MainActivity.kt dan HomeFragment.kt

MainActivity menampung tiga Fragment dengan bottom navigation. Toolbar dan tombol beranda kini berada di HomeFragment (lihat PANDUAN_PERTEMUAN_7.md).

Toolbar dipasang menggunakan `setSupportActionBar(binding.toolbar)`.
Tombol detail membuka DetailActivity.
Saat LogOut dipilih, AlertDialog menampilkan Ya dan Tidak.
Ya menyimpan `isLogin = false`, lalu membuka Login dengan task baru yang membersihkan halaman sebelumnya.
Tidak hanya menutup dialog sehingga pengguna tetap di beranda.

## Urutan demo video

1. Dalam kondisi sudah logout, buka aplikasi: splash 2 detik, lalu Login.
2. Tunjukkan toolbar, warna, font, logo, dan CardView login.
3. Tekan Login saat input kosong, lalu coba password yang salah. Tetap di Login.
4. Isi akun demo yang benar: masuk ke beranda.
5. Buka detail dan kembali melalui tombol atau panah toolbar.
6. Tutup aplikasi dari recent apps dan buka melalui ikon: splash menuju beranda.
7. Pilih LogOut lalu Tidak: tetap di beranda.
8. Pilih LogOut lalu Ya: kembali ke Login.
9. Buka ulang aplikasi: splash menuju Login.

## Desain

Warna: `app/src/main/res/values/colors.xml`.
Font dan toolbar: `app/src/main/res/values/themes.xml`.
Gambar splash: `app/src/main/res/layout/activity_splash.xml`.

Palet sementara memakai hijau EcoLapor, font sans-serif, dan logo daun.
Grand design terpisah belum diberikan untuk dicocokkan.
