package com.example.tugaspemrogramanmobile_luthfi3tic

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugaspemrogramanmobile_luthfi3tic.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tampilkan layout splash selama 2 detik, lalu cek status login.
        handler.postDelayed({
            val sharedPreferences = getSharedPreferences("ecolapor_session", MODE_PRIVATE)
            val isLogin = sharedPreferences.getBoolean("isLogin", false)

            if (isLogin) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Batalkan perpindahan jika splash sudah ditutup.
        handler.removeCallbacksAndMessages(null)
    }
}
