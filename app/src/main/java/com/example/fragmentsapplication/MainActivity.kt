package com.example.fragmentsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


//main calls for dynamic fragments; set this way for ease of use when creating new fragments,
//as information needs only to be added to arraya resource to create an entirely new fragment
class MainActivity : AppCompatActivity(), ListFragments.OnFragmentSelected {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {           //shows main screen fragment with list of fragments
            supportFragmentManager
                .beginTransaction()
                .add(R.id.main, ListFragments.newInstance(), "fragment list") //list fragment instance
                .commit()
        }
    }


    override fun onFragmentSelected(fragmentModel: FragmentModel) {     //returns selected fragment details to be dynamically set to fragment template

        val detailsFragment = NebulasFragment.newInstance(fragmentModel) //individual fragment instance
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main, detailsFragment, "fragment details")
            .addToBackStack(null)
            .commit()
    }


}
