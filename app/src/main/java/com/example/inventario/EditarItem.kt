package com.example.inventario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class EditarItem : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_item)

        if( RepositorioItem.posicaoSelecionada != null ){

            findViewById<EditText>(R.id.txteNome).setText(RepositorioItem.itemSelecionado().nome)
            findViewById<EditText>(R.id.txteQuantidade).setText(RepositorioItem.itemSelecionado().quantidade.toString())

        }

    }

    fun salvar( v:View ){

        var novoNome = findViewById<TextView>(R.id.txteNome).text.toString()
        var novaQuantidade = findViewById<TextView>(R.id.txteQuantidade).text.toString().toInt()

        if( RepositorioItem.posicaoSelecionada == null ){ // Novo Item

            RepositorioItem.itens.add(Item(novoNome, novaQuantidade))

        } else {

            RepositorioItem.itemSelecionado().apply { // Edição
                nome = novoNome
                quantidade = novaQuantidade
            }

        }

        setResult(1)

        finish()

    }

    fun cancelar( v:View ){
        RepositorioItem.posicaoSelecionada = null
        setResult(0)
        finish()
    }

    override fun onBackPressed() {
        cancelar(findViewById(R.id.btnCancel))
        super.onBackPressed()
    }

}