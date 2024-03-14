package com.example.leaguesyync

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LigaAdapter(private val ligas: List<Liga>) : RecyclerView.Adapter<LigaAdapter.ViewHolder>() {

    private var listener: OnItemClickListener? = null

    // Interfaz para manejar los clics en los elementos del RecyclerView
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    // Método para establecer el listener del clic
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_liga, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val liga = ligas[position]
        holder.bind(liga)
        // Configurar el clic en el elemento del RecyclerView
        holder.itemView.setOnClickListener {
            listener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return ligas.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreLigaTextView: TextView = itemView.findViewById(R.id.textViewNombreEquipo)

        fun bind(liga: Liga) {
            nombreLigaTextView.text = liga.nombre
        }
    }
}
