package com.pratham.kotlindemo.Quotes

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.pratham.kotlindemo.Model.Model_Quote
import java.lang.Exception
import java.nio.charset.Charset

class QuoteViewModel(val cotext : Context) : ViewModel(){

    private var quoteList : Array<Model_Quote> = emptyArray()
    private var index : Int = 0

    init{
        quoteList = loadQuoatesFromAsset()
    }

    private fun loadQuoatesFromAsset(): Array<Model_Quote> {
        val readFile = cotext.assets.open("quotes.json")
        val size: Int = readFile.available()
        val buffer = ByteArray(size)
        readFile.read(buffer)
        readFile.close()
        val quoteJson = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(quoteJson,Array<Model_Quote>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextQuote() = quoteList[++index]

    fun previousQuote() : Model_Quote{
        try {
            if (index<0) {
                Toast.makeText(cotext,"Click Next!",Toast.LENGTH_SHORT).show()
                return quoteList[0]
            }
            else
                return quoteList[--index]
        } catch (e: Exception){
            Toast.makeText(cotext,"Click Next!",Toast.LENGTH_SHORT).show()
            return quoteList[0]
        }
    }
}