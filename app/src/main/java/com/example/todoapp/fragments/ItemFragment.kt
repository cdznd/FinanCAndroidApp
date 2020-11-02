package com.example.todoapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.fragments.ItemViewModel
import com.example.todoapp.model.Item
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.list)

        var viewModel = ViewModelProvider(requireActivity()).get(ItemViewModel::class.java)
        //var viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        viewModel.itemList.observe(requireActivity(), Observer { t ->

            with(recycler){

                adapter = ItemAdapter(t)

            }

        })

        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener{

            findNavController().navigate(R.id.action_itemFragment_to_createItemFragment3)

        }

        return view

    }

}

