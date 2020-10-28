package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)

        //Set toolbar from activity_main
        setSupportActionBar(toolbar)

        //Set button to open navMenu
        var btnToggle = ActionBarDrawerToggle(

            this,findViewById(R.id.drawer_layout),toolbar,R.string.OpenMenu,R.string.CloseMenu

        )

        drawer_layout.addDrawerListener(btnToggle)
        btnToggle.syncState()

        //TODO define fragments and fragments changes

    }


    //Create 3dots menu in toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.upside_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    //Event to option selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){

            R.id.menu_UpsideAbout -> showActivityAbout()
            else -> super.onOptionsItemSelected(item)

        }

        return super.onOptionsItemSelected(item)

    }

    //Others Activitys

    fun showActivityAbout() : Boolean{

        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)

        return true

    }

}