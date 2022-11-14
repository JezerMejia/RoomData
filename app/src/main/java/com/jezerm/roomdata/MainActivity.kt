package com.jezerm.roomdata

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.jezerm.roomdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        with (binding.included) {
            this.btnRequest.setOnClickListener {
                openRequestData()
            }
            this.btnAdd.setOnClickListener {
                openAddData()
            }
            this.btnEdit.setOnClickListener {
                openEditData()
            }
            this.btnDelete.setOnClickListener {
                openDeleteData()
            }
        }
    }

    private fun openRequestData() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun openAddData() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun openEditData() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun openDeleteData() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}