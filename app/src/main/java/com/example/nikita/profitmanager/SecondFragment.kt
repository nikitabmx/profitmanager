package com.example.nikita.profitmanager

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_profits.*

class SecondFragment : Fragment() {


    companion object
    {
        fun newInstance() =  SecondFragment()
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profits, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        kekbutton.setOnClickListener {


            Toast.makeText(activity, "КЕК БАТТОН ЧЕК", Toast.LENGTH_SHORT).show()
        }

        super.onViewCreated(view, savedInstanceState)
    }
}