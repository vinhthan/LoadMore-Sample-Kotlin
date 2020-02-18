package com.example.loadmoresamplekotlinll1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ItemOnClick {
    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: NameAdapter
    lateinit var mList: ArrayList<String?>
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mList = ArrayList()
        recyclerView = findViewById(R.id.recyclerView)
        populateData()
        initAdapter()
        initScrollListener()

    }

    private fun populateData() {
        var i = 0 //item 1
        while (i < 10) {
            mList.add("Item $i")
            i++
        }
    }

    private fun initAdapter() {
        //mList = ArrayList()
        mAdapter = NameAdapter(mList, this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = mAdapter
    }

    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val manager = recyclerView.layoutManager as LinearLayoutManager
                if (!isLoading){
                    if (manager != null && manager.findLastCompletelyVisibleItemPosition() == mList.size - 1){
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        mList.add(null)
        mAdapter.notifyItemInserted(mList.size - 1)
        Handler().postDelayed({
            mList.removeAt(mList.size - 1)
            val scrollPosition = mList.size
            mAdapter.notifyItemRemoved(scrollPosition)
            var currentSize = scrollPosition
            val nextLimit = currentSize + 10
            while (currentSize - 1 < nextLimit){
                mList.add("Item $currentSize")
                currentSize++
            }
            mAdapter.notifyDataSetChanged()
            isLoading = false
        }, 1600)
    }

    override fun OnItemClick(position: Int) {
        Toast.makeText(this, "Position $position", Toast.LENGTH_SHORT).show()    }
}







