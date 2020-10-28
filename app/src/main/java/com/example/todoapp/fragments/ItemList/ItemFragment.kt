package com.example.todoapp.fragments.ItemList

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.R
import com.example.todoapp.model.Item


/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment() {

    private lateinit var viewModel: ItemViewModel

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        //val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        val view = inflater.inflate(R.layout.fragment_item,container,false)

        //val recyclerList = view.findViewById<RecyclerView>(R.id.list)

        viewModel = ViewModelProvider(requireActivity()).get(ItemViewModel::class.java)

        //TODO Observer
        /*
        viewModel.items.observe(viewLifecycleOwner,{

        })


         */
    }


}