package com.komalmane.practicalTest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.grid_item_row.view.*

class HashMapAdapter(
    context: Context,
    val data: Map<String, Double>,
    private val resource: Int
) : ArrayAdapter<HashMapAdapter.ItemHolder>(context, resource) {
    private val mKeys: Array<String>

    init {
        var list = data.map { it.key }
        mKeys = list.toTypedArray()
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getView(pos: Int, view: View?, parent: ViewGroup): View {
        var convertView = view


        val holder: ItemHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null)
            holder = ItemHolder()
            holder.key = convertView.textview_key
            holder.value = convertView.textview_value
            convertView.tag = holder
        } else {
            holder = convertView.tag as ItemHolder
        }
        val key = mKeys[pos]
        val value = data[key]
        holder.key!!.text = key
        holder.value!!.text = value.toString()


        return convertView!!
    }

    class ItemHolder {
        lateinit var key: TextView
        lateinit var value: TextView

    }
}
