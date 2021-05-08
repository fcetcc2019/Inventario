package com.example.inventario

import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter ( val editListener:View.OnClickListener ):RecyclerView.Adapter<ItemAdapter.PAVH>() {

    var itens:ArrayList<Item> = RepositorioItem.itens

    class PAVH:RecyclerView.ViewHolder {

        var txtvQuantidade:TextView
        var txtvNome:TextView
        var btnEdit:ImageButton
        var btnDelete:ImageButton

        constructor( v:View ):super(v) {

            txtvNome = v.findViewById(R.id.lblItemName)
            txtvQuantidade = v.findViewById(R.id.lblItemAmount)
            btnEdit = v.findViewById(R.id.btnEdit)
            btnDelete = v.findViewById(R.id.btnDelete)

        }

    }

    override fun onCreateViewHolder( parent:ViewGroup, viewType:Int ):PAVH {

        val view = LayoutInflater.from(parent.context).inflate( R.layout.room_items_list_item, parent, false )
        return PAVH(view)

    }

    override fun onBindViewHolder( holder:PAVH, position:Int) {

        holder.txtvNome.text = itens[position].nome
        holder.txtvQuantidade.text = itens[position].quantidade.toString()
        holder.btnEdit.id = position
        holder.btnDelete.id = position
        holder.btnEdit.setOnClickListener(editListener)
        holder.btnDelete.setOnClickListener {
            itens.remove(RepositorioItem.itemSelecionado())
        }

    }

    override fun getItemCount(): Int = itens.size

}