package com.example.nikita.profitmanager

import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.nikita.profitmanager.adapters.CoinAdapter
import com.example.nikita.profitmanager.api.ServiceGenerator
import kotlinx.android.synthetic.main.fragment_market_cap.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.util.*


class FirstFragment : android.support.v4.app.Fragment() {
    private lateinit var mRandom:Random
    private lateinit var mHandler: Handler
    private lateinit var mRunnable:Runnable
    private lateinit var adapter:CoinAdapter
    private val TAG = "Fragment02"
    private var mSwipeLayout: SwipeRefreshLayout? = null

    companion object
    {
        fun newInstance() = FirstFragment()
    }





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "@onCreate view before")
        return inflater.inflate(R.layout.fragment_market_cap, container, false)
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mRandom = Random()

        // Initialize the handler instance
        mHandler = Handler()
        loadcoins()


        swipe.setOnRefreshListener {
            mRunnable = Runnable {


                Log.i(TAG, "color")
                   swipe.setColorSchemeResources(
                            R.color.blue_swipe, R.color.green_swipe,
                            R.color.orange_swipe, R.color.red_swipe)



                    Log.i(TAG, "refresh on click in swipe")
                    loadcoins()


                Log.i(TAG, "refresh on click after fun call")


                    swipe.isRefreshing = false

            }
            mHandler.postDelayed(

                    mRunnable,1000)

            (activity as AppCompatActivity).supportActionBar!!.title = "Updating..."
        }




        super.onViewCreated(view, savedInstanceState)

    }
















    private fun loadcoins()
    {

        RecyclerView.layoutManager= LinearLayoutManager(activity)
        Log.i(TAG, "layout manager")
        adapter = CoinAdapter()
        Log.i(TAG, "adapter")
        RecyclerView.adapter = adapter
        Log.i(TAG, "RecyclerView.adapter = adapter")

        launch(UI) {


            try {
                (activity as AppCompatActivity).supportActionBar!!.title = "Profit manager"
                Log.i(TAG, "try")
                Toast.makeText(activity, "Обновлено", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Toast")
                adapter.mData= ServiceGenerator.serverApi.loadData(25).await()
                //берем готовый список с адаптером,присоединяем
                if (swipe.isRefreshing ) mSwipeLayout!!.isRefreshing = false
            }
            catch (ex:Exception)
            {
                Toast.makeText(activity,R.string.no_connection, Toast.LENGTH_LONG).show()

            }
        }
    }











}

