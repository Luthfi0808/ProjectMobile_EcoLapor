package com.example.tugaspemrogramanmobile_luthfi3tic.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tugaspemrogramanmobile_luthfi3tic.R
import com.example.tugaspemrogramanmobile_luthfi3tic.databinding.FragmentHomeBinding

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import com.example.tugaspemrogramanmobile_luthfi3tic.DetailActivity
import com.example.tugaspemrogramanmobile_luthfi3tic.LoginActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setTitle(R.string.home_title)

        val sharedPreferences = requireContext().getSharedPreferences("ecolapor_session", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "Pengguna")
        binding.tvWelcome.text = getString(R.string.home_username, username)

        binding.btnDetail.setOnClickListener {
            startActivity(Intent(requireContext(), DetailActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle(R.string.logout)
                .setMessage(R.string.logout_confirmation)
                .setPositiveButton(R.string.yes) { _, _ ->
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("isLogin", false)
                    editor.remove("username")
                    editor.apply()
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton(R.string.no, null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
