package com.example.fragmentsapplication

import java.io.Serializable


//constructor for FragmentModel data class; required information to pass into new fragment
data class FragmentModel(val imageResId: Int,
                    val name: String,
                    val description: String,
                    val url: String,
                    var text: String = "") : Serializable  //serializable for interface usage
