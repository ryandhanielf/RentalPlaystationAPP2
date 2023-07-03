package com.example.rentalplaystationapp.ui

import android.provider.UserDictionary.Words
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rentalplaystationapp.R
import com.example.rentalplaystationapp.model.Games

class GameListAdapter(
    private val onItemClickListener: (Games) -> Unit
): ListAdapter<Games, GameListAdapter.GamesViewHolder>(WORDS_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
     return GamesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val games = getItem(position)
        holder.bind(games)
        holder.itemView.setOnClickListener {
            onItemClickListener(games)
        }
    }

    class GamesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private  val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private  val addressTextView: TextView = itemView.findViewById(R.id.addressTextView)

        fun bind(games: Games?) {
            nameTextView.text = games?.name
            addressTextView.text = games?.address

        }

        companion object {
            fun create(parent: ViewGroup): GameListAdapter.GamesViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_games, parent, false)
                return GamesViewHolder(view)

            }
        }

    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Games>() {
            override fun areItemsTheSame(oldItem: Games, newItem: Games): Boolean {
               return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Games, newItem: Games): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}