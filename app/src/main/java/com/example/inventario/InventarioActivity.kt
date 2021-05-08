package com.example.inventario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InventarioActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario)

        RepositorioItem.geraItens()

        val fab = findViewById<FloatingActionButton>(R.id.fabNewRoom)
        fab.setOnClickListener {
            novoItem()
        }

        val listener = this

        findViewById<RecyclerView>(R.id.rclItemList).apply {

            setHasFixedSize(true)
            adapter = ItemAdapter(listener)
            layoutManager = LinearLayoutManager(this.context)

        }

        atualizarTotal()

    }

    private fun atualizarTotal() {
        findViewById<TextView>(R.id.lblTotalItems).text = RepositorioItem.totalDeItens().toString()
    }

    fun novoItem(){

        startActivityForResult( Intent(this, EditarItem::class.java), 1 )

    }

    override fun onClick(v: View?) { // Editar

        RepositorioItem.posicaoSelecionada = v!!.id

        startActivityForResult( Intent(this, EditarItem::class.java), 2 )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when( requestCode ){
            1 -> { // Novo
                when( resultCode ){
                    1 -> {
                        findViewById<RecyclerView>(R.id.rclItemList).adapter?.notifyDataSetChanged()
                        atualizarTotal()
                        Toast.makeText( this, "Salvo com Sucesso!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            2 -> { // Edição
                when( resultCode ){
                    0 -> {
                        Toast.makeText( this, "Cancelado.", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        findViewById<RecyclerView>(R.id.rclItemList).adapter?.notifyDataSetChanged()
                        atualizarTotal()
                        Toast.makeText( this, "Alterado com sucesso!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }


}