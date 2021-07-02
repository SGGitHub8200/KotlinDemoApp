package com.pratham.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratham.kotlindemo.Model.Model_Hobby
import com.pratham.kotlindemo.Model.Model_User
import com.pratham.kotlindemo.dbClasses.AppDatabase
import com.pratham.kotlindemo.sharedPreferences.FastSave
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {

    val hobbiesList = ArrayList<Model_Hobby>()
    lateinit var adapter : HobbiesAdapter
    var appDB: AppDatabase?=null
    val userList = ArrayList<Model_User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        var id = FastSave.instance?.getString("userId","defUser")

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_recycler.layoutManager = layoutManager

/*        appDB = this.let { AppDatabase.getAppDataBase(it) }
        appDB?.userDao()?.getAllUser()?.forEach {
            Log.e("demo", it.userName.toString())
            var hobby = Model_Hobby(it.userName.toString())
            hobbiesList.add(hobby)
        }*/
        adapter = HobbiesAdapter(this, hobbiesList)
        rv_recycler.adapter=adapter

        prepareHobbyData()
    }

    private fun prepareHobbyData(){
        var hobby = Model_Hobby("Swimming")
        hobbiesList.add(hobby)
        hobby = Model_Hobby("Playing")
        hobbiesList.add(hobby)
        hobby = Model_Hobby("Walking")
        hobbiesList.add(hobby)
        hobby = Model_Hobby("Reading")
        hobbiesList.add(hobby)
        hobby = Model_Hobby("Trekking")
        hobbiesList.add(hobby)
        hobby = Model_Hobby("Running")
        hobbiesList.add(hobby)
        hobby = Model_Hobby("Eating")
        hobbiesList.add(hobby)
        hobby = Model_Hobby("Watching")
        hobbiesList.add(hobby)
        hobby = Model_Hobby("Sleeping")
        hobbiesList.add(hobby)
        hobby = Model_Hobby("Gaming")
        hobbiesList.add(hobby)
        adapter.notifyDataSetChanged()
    }
}

private fun <E> ArrayList<E>.add(element: String?) {

}
