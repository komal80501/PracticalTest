package com.komalmane.practicalTest.fragment

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.komalmane.practicalTest.MainActivity
import com.komalmane.practicalTest.R
import com.komalmane.practicalTest.adapter.HashMapAdapter
import com.komalmane.practicalTest.retrofit.ApiClient
import kotlinx.android.synthetic.main.fragment_listing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListingFragment : Fragment(R.layout.fragment_listing) {

    private val ACCESS_KEY = "dead50f92ddfb0a6e69d4e443544be53"
    private val SYMBOLS = "BGN,CAD,BRL,HUF,DKK,USD,AUD,CAD,PLN,MXN"
    private val FORMAT = "1"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var parent = (activity as MainActivity)
        val gson = Gson()

        if (!parent.isInternetAvailable()) {
            Toast.makeText(requireContext(), "Internet not available", Toast.LENGTH_LONG).show()
            return
        }

        progressBarListing.visibility = View.VISIBLE
        lifecycleScope.launch {
            val data = ApiClient.getClient.getLatest(ACCESS_KEY, SYMBOLS, FORMAT)

            try {
                val jstr = gson.toJson(data.rates)
                val mapper = ObjectMapper()
                val map: HashMap<String, String> =
                    mapper.readValue(jstr, object : TypeReference<Map<String, String>>() {})

                withContext(Dispatchers.Main) {
                    textViewDate.text = "Date : ${parent.changeFormat(data.date)}"
                    val adapter = HashMapAdapter(requireContext(), map, R.layout.grid_item_row)
                    gridview.adapter = adapter
                    progressBarListing.visibility = View.GONE
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}