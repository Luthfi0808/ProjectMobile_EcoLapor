package com.example.tugaspemrogramanmobile_luthfi3tic

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugaspemrogramanmobile_luthfi3tic.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setTitle(R.string.login_title)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val safeArea = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime()
            )
            view.setPadding(safeArea.left, safeArea.top, safeArea.right, safeArea.bottom)
            insets
        }

        val sharedPreferences = getSharedPreferences("ecolapor_session", MODE_PRIVATE)

        binding.button.setOnClickListener {
            val username = binding.username.text.toString().trim()
            val password = binding.password.text.toString()

            binding.username.error = null
            binding.password.error = null

            if (username.isEmpty()) {
                binding.username.error = getString(R.string.username_required)
                binding.username.requestFocus()
            } else if (password.isEmpty()) {
                binding.password.error = getString(R.string.password_required)
                binding.password.requestFocus()
            } else if (username == "ecolapor" && password == "eco123") {
                // Akun demo: username DAN password harus benar.
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                binding.password.error = getString(R.string.invalid_credentials)
                binding.password.requestFocus()
            }
        }
    }
}
