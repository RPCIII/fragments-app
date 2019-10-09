package com.example.fragmentsapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentsapplication.databinding.RecyclerFragmentModelBinding


class ListFragments : Fragment() {

    //sets data to be inittialized when a fragment is selected so data is not preloaded
    private lateinit var imageResIds: IntArray
    private lateinit var names: Array<String>
    private lateinit var descriptions: Array<String>
    private lateinit var urls: Array<String>
    private lateinit var listener: OnFragmentSelected

    companion object {      //instance for return

        fun newInstance(): ListFragments {
            return ListFragments()
        }
    }

    override fun onAttach(context: Context) {       //attached data
        super.onAttach(context)

        if (context is OnFragmentSelected) {        //error handling; ensures context is an actually selectable fragment
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnFragmentSelected.")
        }

        //sets all information on click via context listener
        val resources = context.resources
        names = resources.getStringArray(R.array.names)
        descriptions = resources.getStringArray(R.array.descriptions)
        urls = resources.getStringArray(R.array.urls)

        //repeats to create new fragments depending on amount of images in array
        val typedArray = resources.obtainTypedArray(R.array.images)
        val imageCount = names.size
        imageResIds = IntArray(imageCount)
        for (i in 0 until imageCount) {
            imageResIds[i] = typedArray.getResourceId(i, 0)
        }
        typedArray.recycle()        //clears onAttach method of data
    }


    //inflates list fragment to be seen
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_list, container,
            false)
        val activity = activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = FragmentListAdapter(activity)
        return view
    }

    //uses RecyclerView adapter to set data on inflate; above code
    //used to create individual fragments, this portion holds them on the screen
    //so all are displayed
    //portion uses generated java class from data binding
    internal inner class FragmentListAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {

        private val layoutInflater = LayoutInflater.from(context)

        //set to keep each fragment
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val recyclerFragmentModelBinding = RecyclerFragmentModelBinding.inflate(layoutInflater, viewGroup, false)
            return ViewHolder(recyclerFragmentModelBinding.root, recyclerFragmentModelBinding)
        }

        //sett to bind each fragment; binds with onClickListener
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val fragment = FragmentModel(imageResIds[position], names[position], descriptions[position], urls[position])
            viewHolder.setData(fragment)
            viewHolder.itemView.setOnClickListener { listener.onFragmentSelected(fragment) }
        }

        override fun getItemCount() = names.size
    }

    //constructor to finally set view with data after all created
    internal inner class ViewHolder constructor(itemView: View, private val recyclerItemListBinding: RecyclerFragmentModelBinding)
        : RecyclerView.ViewHolder(itemView) {

        fun setData(fragmentModel: FragmentModel) {
            recyclerItemListBinding.fragmentModel = fragmentModel
        }
    }

    //interrface call for fragment selected
    interface OnFragmentSelected {
        fun onFragmentSelected(fragmentModel: FragmentModel)
    }

}
