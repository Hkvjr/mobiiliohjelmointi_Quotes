package com.example.quotes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotes.model.Quote
import com.example.quotes.model.QuotesApi
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {
    private val _quote = MutableLiveData<Quote>()
    val quote: LiveData<Quote> get() = _quote

    init {
        fetchQuote()
    }
//funktio lainauksen hakemiseen
    fun fetchQuote() {
        viewModelScope.launch {
            val quotesApi = QuotesApi.getInstance()
            try {
                val apiKey = "API-KEY" //APIKEYn paikka
                val fetchedQuote = quotesApi.getQuote(apiKey).first() // Hakee ensimmäisen lainauksen
                _quote.value = fetchedQuote // Asettaa haetun lainauksen
            } catch (e: Exception) {
                Log.d("QUOTEVIEWMODEL", "Error fetching quote: ${e.message}")
            }
        }
    }
}