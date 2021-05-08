package com.example.inventario

class RepositorioItem {

    companion object repo {

        var itens = ArrayList<Item>();
        var posicaoSelecionada: Int? = null

        fun geraItens(){

            for ( i in 1..10 )
                itens.add( Item( "Item ${i}", (Math.random() * i).toInt()) )

        }

        fun itemSelecionado():Item {

            return itens[posicaoSelecionada!!]

        }

        fun totalDeItens(): Int {

            var resultado = 0

            for ( i:Item in itens )
                resultado += i.quantidade

            return resultado

        }

    }

}