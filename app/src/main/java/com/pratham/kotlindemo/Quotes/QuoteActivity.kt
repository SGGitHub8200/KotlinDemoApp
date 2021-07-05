package com.pratham.kotlindemo.Quotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pratham.kotlindemo.Model.Model_Quote
import com.pratham.kotlindemo.R
import kotlinx.android.synthetic.main.activity_quote.*

class QuoteActivity : AppCompatActivity() {

    lateinit var quoteViewModel: QuoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote)
        quoteViewModel = ViewModelProvider(this,QuoteViewModelFactory(application)).get(QuoteViewModel::class.java)
        setQuote(quoteViewModel.getQuote())
    }

    fun setQuote(quote: Model_Quote){
        quoteText.text=quote.text
        quoteAuthor.text=quote.author
    }

    fun onPrevious(view: View) {
        setQuote(quoteViewModel.previousQuote())
    }
    fun onNext(view: View) {
        setQuote(quoteViewModel.nextQuote())
    }
    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,quoteViewModel.getQuote().text)
        startActivity(intent)
    }
}