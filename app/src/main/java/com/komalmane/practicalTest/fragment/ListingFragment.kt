package com.komalmane.practicalTest.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.komalmane.practicalTest.MainActivity
import com.komalmane.practicalTest.R
import com.komalmane.practicalTest.adapter.HashMapAdapter
import com.komalmane.practicalTest.model.HistoricalDataResponse
import com.komalmane.practicalTest.retrofit.ApiClient
import kotlinx.android.synthetic.main.fragment_listing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import javax.security.auth.callback.Callback


class ListingFragment : Fragment(R.layout.fragment_listing) {

    private val APP_ID = "b8170f2960a546378a5ceb06a7bb6f59"
    private val API_DATE_FORMAT = "yyyy-MM-dd"
    private val DISPLAY_DATE_FORMAT = "dd/MMM/yyyy"

    private lateinit var date: String
    private lateinit var parent: MainActivity
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener

    var cal = Calendar.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        parent = (activity as MainActivity)

        if (!parent.isInternetAvailable()) {
            Toast.makeText(requireContext(), "Internet not available", Toast.LENGTH_LONG).show()
            return
        }

        dateSelection()
    }

    private fun dateSelection() {
        textViewDate.text = "Date : ${parent.changeFormat(cal.time, DISPLAY_DATE_FORMAT)}"
        date = parent.changeFormat(cal.time, API_DATE_FORMAT)

        dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                updateDateInView()
            }

        textViewDate.setOnClickListener {
            DatePickerDialog(
                requireContext(), dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

    }

    fun updateDateInView() {
        date = parent.changeFormat(cal.time, API_DATE_FORMAT)
        textViewDate.text = "Date : ${parent.changeFormat(cal.time, DISPLAY_DATE_FORMAT)}"
        apiCall()
    }

    private fun apiCall() {
        progressBarListing.visibility = View.VISIBLE
        var client = ApiClient.getClient
        val call =
            client.getHistoricalData("http://openexchangerates.org/api/historical/$date.json?app_id=$APP_ID")

        call.enqueue(object : retrofit2.Callback<HistoricalDataResponse> {
            override fun onResponse(call: Call<HistoricalDataResponse>, response: Response<HistoricalDataResponse>) {
                if (response.isSuccessful) {

                    val adapter = HashMapAdapter(
                        requireContext(),
                        response.body()!!.rates,
                        R.layout.grid_item_row
                    )
                    gridview.adapter = adapter
                    progressBarListing.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<HistoricalDataResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onResume() {
        super.onResume()
        apiCall()

    }
}