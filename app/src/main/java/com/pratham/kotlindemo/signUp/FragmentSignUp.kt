package com.pratham.kotlindemo.signUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pratham.kotlindemo.Model.Model_User

import com.pratham.kotlindemo.R
import com.pratham.kotlindemo.dbClasses.AppDatabase
import com.pratham.kotlindemo.dbClasses.BackupDatabase
import com.pratham.kotlindemo.dbClasses.dao.userDao
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*
import java.util.*

class FragmentSignUp : Fragment() {

    var appDB: AppDatabase? = null
    private var user: Model_User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        appDB = context?.let { AppDatabase.getAppDataBase(it) }
        view.btn_SignUp.setOnClickListener(View.OnClickListener {
            if(et_Name.text.toString().isEmpty() || et_Mobile.text.toString().isEmpty() || et_Password.text.toString().isEmpty())
                Toast.makeText(activity,"Please Fill All Details!",Toast.LENGTH_LONG).show()
            else {
                user = Model_User(
                    getUUID().toString(),
                    et_Name.text.toString(),
                    et_Mobile.text.toString(),
                    et_Password.text.toString()
                )
                appDB?.userDao()?.insertUser(user!!)
                BackupDatabase.backup(activity)
                Toast.makeText(activity, "User Saved.", Toast.LENGTH_LONG).show()
                activity?.onBackPressed()
            }
        })

        return view
    }

    fun getUUID(): UUID? {
        return UUID.randomUUID()
    }

}
