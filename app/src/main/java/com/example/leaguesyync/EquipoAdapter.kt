package com.example.leaguesyync

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EquipoAdapter(private val equipos: List<Equipo>, listener: (Equipo) -> Unit) :
    RecyclerView.Adapter<EquipoAdapter.EquipoViewHolder>() {

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_equipo, parent, false)
        return EquipoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EquipoViewHolder, position: Int) {
        val equipo = equipos[position]
        holder.bind(equipo)
    }

    override fun getItemCount(): Int {
        return equipos.size
    }

    inner class EquipoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreEquipoTextView: TextView = itemView.findViewById(R.id.nombreEquipoTextView)

        fun bind(equipo: Equipo) {
            nombreEquipoTextView.text = equipo.nombre
            itemView.setOnClickListener {
                listener?.onItemClick(equipo)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(equipo: Equipo)
    }
}
