package com.example.rentalplaystationapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rentalplaystationapp.R
import com.example.rentalplaystationapp.application.GameApp
import com.example.rentalplaystationapp.databinding.FragmentSecondBinding
import com.example.rentalplaystationapp.model.Games
import com.example.rentalplaystationapp.repository.GamesRepository

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val gamesViewModel: GamesViewModel by  viewModels {
        GamesViewModelFactory((applicationContext as GameApp).repository)
    }
    private val args : SecondFragmentArgs by navArgs()
    private var games: Games? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        games = args.games
        // kita cek jika tire null maka tampilan default nambah rental ps
        // jika games tidak null tampilan sedikit berubah ada tombol hapus dan tambah
        if (games != null) {
            binding.deleteButton.visibility = View.VISIBLE
            binding.saveButton.text = "Ubah"
            binding.nameEditText.setText(games?.name)
            binding.addressEditText.setText(games?.address)
        }
        val name = binding.nameEditText.text
        val address = binding.addressEditText.text
        binding.saveButton.setOnClickListener {
            //kita kasih kondisi jika nama dan alamat kosong tidak bisa nyimpan
            if (name.isEmpty()) {
                Toast.makeText(context, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (address.isEmpty()) {
                Toast.makeText(context, "Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                if (games == null) {
                    val games = Games(0, name.toString(), address.toString())
                    gamesViewModel.insert(games)
                } else {
                    val games = Games(games?.id!!, name.toString(), address.toString())
                    gamesViewModel.update(games)
                }
                findNavController().popBackStack() // untuk dismiss halaman ini
            }
        }

        binding.deleteButton.setOnClickListener {
            games?.let { gamesViewModel.delete(it) }
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}