package com.pratham.kotlindemo.signIn

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pratham.kotlindemo.MainActivity
import com.pratham.kotlindemo.Model.Model_User
import com.pratham.kotlindemo.R
import com.pratham.kotlindemo.dbClasses.AppDatabase
import com.pratham.kotlindemo.sharedPreferences.FastSave
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_in.view.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class FragmentSignIn : Fragment() {

    var appDB: AppDatabase?=null
    val studList = ArrayList<Model_User>()
    private var user: Model_User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        appDB = context?.let { AppDatabase.getAppDataBase(it) }
        var stud = appDB?.userDao()?.getAllUser()?.size
        if (stud != null) {
            Toast.makeText(activity, stud.toString(), Toast.LENGTH_LONG).show()
        }

        view.btn_signIn.setOnClickListener(View.OnClickListener {
            if (!TextUtils.isEmpty(tie_userMobile.text.toString()) && !TextUtils.isEmpty(
                    tie_userPassword.text.toString()
                )
            ) {
                user = appDB?.userDao()
                    ?.userLogin(tie_userMobile.text.toString(), tie_userPassword.text.toString())
                if (user != null) {
                    Toast.makeText(activity, "Successful Login!", Toast.LENGTH_LONG).show()
                    FastSave.instance?.saveString("userId",user?.userId)
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(activity, "Invalid Mobile or Password!", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                Toast.makeText(
                    activity,
                    "Mobile No. and Password are Mandatory.",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        return view
    }
}
