package com.example.androidtodolist

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {


    private var lvItems: ListView? = null
    private var items: ArrayList<String>? = null
    private var adapter: ArrayAdapter<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvItems = findViewById(R.id.lvItems)
        items = arrayListOf()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items!!)
        lvItems!!.adapter = adapter
        items!!.add("First to do")
        items!!.add("Second to do")
        listViewListener()
    }

    fun onAddItem(v: View){
        val etNewItem = this.findViewById<EditText>(R.id.etNewItem)
        val itemText = etNewItem.text.toString()
        adapter!!.add(itemText)
        etNewItem.setText("")
    }

    private fun listViewListener(){
        lvItems!!.setOnItemLongClickListener { _, _, pos, _ ->
            showConfirmationDialog(pos)
            true
        }
    }

    private fun showConfirmationDialog(pos: Int){
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Deleting")
        alertDialogBuilder.setMessage("Are you sure?")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            removeItem(pos)
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun removeItem(pos: Int){
        items!!.removeAt(pos)
        this.adapter!!.notifyDataSetChanged()
    }
}