package com.example.leaguesyync

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class LigaAdapter(private val ligas: List<Lliga>) : RecyclerView.Adapter<LigaAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_liga, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val liga = ligas[position]
        holder.bind(liga)

        holder.itemView.setOnClickListener {
            if (context is BuscarLigaActivity) {
                (context as BuscarLigaActivity).mostrarDialogoUnirseALiga(liga)
            } else {
                val intent = Intent(it.context, OpcionesActivity::class.java)
                intent.putExtra("liga_nombre", liga.nom) // Pasar nombre de la liga u otros datos necesarios
                it.context.startActivity(intent)
            }
        }
    }
    override fun getItemCount(): Int {
        return ligas.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val nombreLigaTextView: TextView = itemView.findViewById(R.id.textViewNombreEquipo)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(liga: Lliga) {
            nombreLigaTextView.text = liga.nom
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val liga = ligas[position]
                listener?.onItemClick(liga)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(liga: Lliga)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }}