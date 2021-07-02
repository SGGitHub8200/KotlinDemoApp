package com.pratham.kotlindemo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.pratham.kotlindemo.sharedPreferences.FastSave
import com.pratham.kotlindemo.signIn.FragmentSignIn
import com.pratham.kotlindemo.signUp.FragmentSignUp
import kotlinx.android.synthetic.main.activity_sign_in_up.*

class SignInUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_up)

        FastSave.init(applicationContext)
        checkPermissions()

        btn_signIn.setOnClickListener(View.OnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.splash_frame, FragmentSignIn())
                .addToBackStack("signIn")
                .commit()
        })

        tv_signUp.setOnClickListener(View.OnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.splash_frame, FragmentSignUp())
                .addToBackStack("signUp")
                .commit()
        })
    }

    fun checkPermissions() {
        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
                    != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                ), 100
            )
        }
    }
}
