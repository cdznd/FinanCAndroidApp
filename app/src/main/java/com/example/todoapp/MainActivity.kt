package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
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

        var navController = findNavController(R.id.fragment)

        nav_View.setNavigationItemSelectedListener{ item ->

            when (item.itemId){

                R.id.menuItem_Start ->
                    navController.navigate(R.id.initialScrollingFragment)
                R.id.menuItem_Conta ->
                    navController.navigate(R.id.contaFragment)
                R.id.menuItem_Categoria ->
                    navController.navigate(R.id.categoriaFragment)
                R.id.menuItem_Lancamento ->
                    navController.navigate(R.id.lancamentoFragment)

            }

            drawer_layout.closeDrawer(GravityCompat.START)

            true

        }

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
    private fun showActivityAbout() : Boolean{

        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)

        return true

    }

}