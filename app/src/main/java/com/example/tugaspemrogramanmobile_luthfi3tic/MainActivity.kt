package com.example.tugaspemrogramanmobile_luthfi3tic

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.tugaspemrogramanmobile_luthfi3tic.Home.HomeFragment
import com.example.tugaspemrogramanmobile_luthfi3tic.Jelajah.JelajahFragment
import com.example.tugaspemrogramanmobile_luthfi3tic.Aktivitas.AktivitasFragment
import com.example.tugaspemrogramanmobile_luthfi3tic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val sharedPreferences = getSharedPreferences("ecolapor_session", MODE_PRIVATE)
        if (!sharedPreferences.getBoolean("isLogin", false)) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
            return
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // BottomNavigationView menangani inset bawah, sesuai modul halaman 6.
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // Saat rotasi, Fragment yang aktif dipulihkan oleh Android.
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_jelajah -> {
                    replaceFragment(JelajahFragment())
                    true
                }
                R.id.nav_aktivitas -> {
                    replaceFragment(AktivitasFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}
