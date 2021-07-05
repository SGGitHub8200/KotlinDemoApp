package com.pratham.kotlindemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pratham.kotlindemo.Quotes.QuoteActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_click.setOnClickListener {
            val intent = Intent(this, QuoteActivity::class.java)
            startActivity(intent)
        }

        btn_share.setOnClickListener {
            val message = et_user.text.toString()
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra("message",message)
            intent.type = "text/plain"

            startActivity(Intent.createChooser(intent,"Share To."))
        }

        btn_recyclerView.setOnClickListener {
            val intent = Intent(this, RecyclerActivity::class.java)
            startActivity(intent)
        }

        btn_calculator.setOnClickListener {
            val intent = Intent(this, Activity_Calculator::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this)
            .setTitle("EXIT")
            .setMessage("Are you sure you want to Exit?")
            .setNegativeButton("NO"){dialog, which->
                dialog.dismiss()
            }
            .setPositiveButton("EXIT"){dialog, which->
                dialog.dismiss()
                finishAffinity()
            }
            .show()
    }
}
