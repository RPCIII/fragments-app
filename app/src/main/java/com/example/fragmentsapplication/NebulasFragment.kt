package com.example.fragmentsapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentsapplication.databinding.NebulasFragmentBinding


class NebulasFragment : Fragment() {

    companion object {

        private const val FRAGMENTMODEL = "model"

        fun newInstance(fragmentModel: FragmentModel): NebulasFragment {   //creates new instance of FragmentModel class
            val args = Bundle()
            args.putSerializable(FRAGMENTMODEL, fragmentModel)             //serializes bundle to return
            val fragment = NebulasFragment()
            fragment.arguments = args
            return fragment
        }

    }

    //gets data for opened fragment with generated java class from data binding
    //returns completed fragment to view
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val NebulasFragmentBinding = NebulasFragmentBinding.inflate(inflater, container, false)
        val model = arguments!!.getSerializable(FRAGMENTMODEL) as FragmentModel
        NebulasFragmentBinding.fragmentModel = model
        model.text = String.format(getString(R.string.description_format), model.description, model.url)        //formatter
        return NebulasFragmentBinding.root
    }


}