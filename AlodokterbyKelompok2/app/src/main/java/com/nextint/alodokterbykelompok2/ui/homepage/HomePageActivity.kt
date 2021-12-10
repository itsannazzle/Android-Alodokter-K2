package com.nextint.alodokterbykelompok2.ui.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.nextint.alodokterbykelompok2.R

class HomePageActivity : AppCompatActivity() {
    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val mToolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        navController = findNavController(R.id.nav_host_fragment)
        val navView: NavigationView = findViewById(R.id.nav_view)

        //bottomnav
        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNavView.setupWithNavController(navController)

        //drawer
        drawerLayout = findViewById(R.id.drawer_layout)
        appBarConfig = AppBarConfiguration(setOf(
            R.id.nav_profile, R.id.nav_payment_method, R.id.nav_appointment), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfig)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_app_bar, menu)
        return true
    }

    //navigate item to destination
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }

    //open drawer when drawer icon clicked and back btn presse
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}