package com.example.tugaspemrogramanmobile_luthfi3tic.Aktivitas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tugaspemrogramanmobile_luthfi3tic.R
import com.example.tugaspemrogramanmobile_luthfi3tic.databinding.FragmentAktivitasBinding

class AktivitasFragment : Fragment() {
    // Data contoh praktikum; foto hanya ilustrasi.
    private val laporanList = listOf(
        LaporanModel("Sampah menumpuk", "Jl. Melati • Sukajadi", "https://images.unsplash.com/photo-1532996122724-e3c354a0b15b?w=240&h=240&fit=crop", "Diproses"),
        LaporanModel("Drainase tersumbat", "Jl. Kenanga • Sukajadi", "https://images.unsplash.com/photo-1437482078695-73f5ca6c96e2?w=240&h=240&fit=crop", "Selesai"),
        LaporanModel("Pohon tumbang", "Jl. Anggrek • Tampan", "https://images.unsplash.com/photo-1441974231531-c6227db76b6e?w=240&h=240&fit=crop", "Selesai"),
        LaporanModel("Air sungai keruh", "Sungai Sail • Sail", "https://images.unsplash.com/photo-1437482078695-73f5ca6c96e2?w=240&h=240&fit=crop", "Diproses"),
        LaporanModel("Sampah di taman", "Taman Kota • Senapelan", "https://images.unsplash.com/photo-1532996122724-e3c354a0b15b?w=240&h=240&fit=crop", "Diterima"),
        LaporanModel("Pembakaran sampah", "Jl. Mawar • Rumbai", "https://images.unsplash.com/photo-1532996122724-e3c354a0b15b?w=240&h=240&fit=crop", "Diproses"),
        LaporanModel("Genangan air", "Jl. Cempaka • Marpoyan", "https://images.unsplash.com/photo-1437482078695-73f5ca6c96e2?w=240&h=240&fit=crop", "Diterima"),
        LaporanModel("Limbah di saluran air", "Jl. Dahlia • Sukajadi", "https://images.unsplash.com/photo-1437482078695-73f5ca6c96e2?w=240&h=240&fit=crop", "Diproses"),
        LaporanModel("Ruang hijau rusak", "Taman Warga • Tampan", "https://images.unsplash.com/photo-1441974231531-c6227db76b6e?w=240&h=240&fit=crop", "Selesai"),
        LaporanModel("Sampah di sungai", "Bantaran Siak • Rumbai", "https://images.unsplash.com/photo-1532996122724-e3c354a0b15b?w=240&h=240&fit=crop", "Diterima")
    )

    private var _binding: FragmentAktivitasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAktivitasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setTitle(R.string.nav_aktivitas)
        binding.listLaporanItems.adapter = LaporanAdapter(requireContext(), laporanList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
