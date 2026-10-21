package com.example.tugaspemrogramanmobile_luthfi3tic.Jelajah

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tugaspemrogramanmobile_luthfi3tic.R
import com.example.tugaspemrogramanmobile_luthfi3tic.databinding.FragmentJelajahBinding

import android.widget.SimpleAdapter
import android.widget.Toast

class JelajahFragment : Fragment() {
    private val dataListWithDesc = listOf(
        mapOf("title" to "Sampah menumpuk", "desc" to "Tumpukan sampah di jalan atau fasilitas umum."),
        mapOf("title" to "Drainase tersumbat", "desc" to "Saluran air tersumbat sampah atau endapan."),
        mapOf("title" to "Pencemaran air", "desc" to "Air sungai atau selokan tercemar limbah."),
        mapOf("title" to "Pohon tumbang", "desc" to "Pohon roboh yang menghalangi jalan warga."),
        mapOf("title" to "Polusi udara", "desc" to "Asap dan debu yang mengganggu lingkungan."),
        mapOf("title" to "Pembakaran sampah", "desc" to "Pembakaran terbuka yang menimbulkan asap."),
        mapOf("title" to "Limbah sembarangan", "desc" to "Pembuangan sisa usaha ke tanah atau sungai."),
        mapOf("title" to "Genangan air", "desc" to "Air menggenang di jalan dan sekitar rumah."),
        mapOf("title" to "Ruang hijau rusak", "desc" to "Tanaman dan taman umum yang rusak."),
        mapOf("title" to "Sampah di sungai", "desc" to "Sampah hanyut yang menghambat aliran sungai.")
    )

    private var _binding: FragmentJelajahBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJelajahBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setTitle(R.string.nav_jelajah)

        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        binding.listViewItems.adapter = adapter
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            Toast.makeText(requireContext(), "Kamu memilih: $title", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
