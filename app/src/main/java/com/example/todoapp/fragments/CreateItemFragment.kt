package com.example.todoapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.model.Item
import kotlinx.android.synthetic.main.fragment_create_item.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel : ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_create_item,container,false)

        viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        viewModel.items.value = Item();

        viewModel.items.observe(viewLifecycleOwner, Observer { t ->

            edit_FormName.setText(t.Name)
            edit_FormDescricao.setText(t.Description)

            view.findViewById<Button>(R.id.btn_submit).setOnClickListener{

                val nome = edit_FormName.text.toString()
                val descricao = edit_FormDescricao.toString()

                val  newItem : Item = Item(id = t.id, Name = nome, Description = descricao)

                viewModel.salvarItem(newItem)
                findNavController().navigateUp()

            }

        })

        // Inflate the layout for this fragment
        return view

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateItemFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}