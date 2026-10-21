package com.example.tugaspemrogramanmobile_luthfi3tic.Aktivitas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.example.tugaspemrogramanmobile_luthfi3tic.R
import com.example.tugaspemrogramanmobile_luthfi3tic.databinding.ItemLaporanBinding

class LaporanAdapter(
    context: Context,
    private val laporan: List<LaporanModel>
) : ArrayAdapter<LaporanModel>(context, 0, laporan) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemLaporanBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root
        val data = laporan[position]

        Glide.with(context)
            .load(data.fotoUrl)
            .placeholder(R.drawable.ecolapor_environment)
            .error(R.drawable.ecolapor_environment)
            .centerCrop()
            .into(binding.imgLaporan)

        binding.textJudul.text = data.judul
        binding.textKeterangan.text = data.keterangan
        binding.textStatus.text = data.status
        binding.imgLaporan.contentDescription = context.getString(R.string.laporan_photo, data.judul)

        // Listener pada adapter, mengikuti modul halaman 18.
        view.setOnClickListener {
            Snackbar.make(parent, "Laporan: ${data.judul}", Snackbar.LENGTH_SHORT).show()
        }
        return view
    }
}
