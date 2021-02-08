package com.azhar.bacaansholat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.azhar.bacaansholat.R
import com.azhar.bacaansholat.model.ModelBacaan
import kotlinx.android.synthetic.main.list_bacaan_shalat.view.*

/**
 * Created by Azhar Rivaldi on 02-02-2021
 */

class BacaanShalatAdapter(private val modelBacaan: List<ModelBacaan>) : RecyclerView.Adapter<BacaanShalatAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_niat_shalat, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(listViewHolder: ListViewHolder, i: Int) {
        val dataModel = modelBacaan[i]
        listViewHolder.txtId.text = dataModel.id
        listViewHolder.txtName.text = dataModel.name
        listViewHolder.txtArabic.text = dataModel.arabic
        listViewHolder.txtLatin.text = dataModel.latin
        listViewHolder.txtTerjemahan.text = dataModel.terjemahan
    }

    override fun getItemCount(): Int {
        return modelBacaan.size
    }

    internal class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtId: TextView
        var txtName: TextView
        var txtArabic: TextView
        var txtLatin: TextView
        var txtTerjemahan: TextView

        init {
            txtId = itemView.txtId
            txtName = itemView.txtName
            txtArabic = itemView.txtArabic
            txtLatin = itemView.txtLatin
            txtTerjemahan = itemView.txtTerjemahan
        }
    }

}