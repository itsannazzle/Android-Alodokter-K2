package com.nextint.alodokterbykelompok2.ui.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.core.view.isVisible
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

        navController = findNavController(R.id.nav_host_fragment)
        val navView: NavigationView = findViewById(R.id.nav_view)

        //drawer
        drawerLayout = findViewById(R.id.drawer_layout)
        appBarConfig = AppBarConfiguration(setOf(R.id.nav_home,
            R.id.nav_service, R.id.nav_article), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfig)
        navView.setupWithNavController(navController)

        //bottomnav
        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ _, destination, arguments ->
            mToolbar.isVisible = arguments?. getBoolean("ShowAppBar", true) == true
            val drawerMenu = arrayOf(R.id.nav_profile)
            val bottomMenu = arrayOf(R.id.nav_home, R.id.nav_service, R.id.nav_article)
            val child = arrayOf(R.id.nav_detail_article, R.id.nav_search)
            when (destination.id) {
                in drawerMenu -> bottomNavView.visibility = View.GONE
                in child -> bottomNavView.visibility = View.GONE
                in bottomMenu -> bottomNavView.visibility = View.VISIBLE
            }
        }

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.imageprofile)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_app_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //navigate item to destination
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }

    //open drawer when drawer icon clicked and back btn press
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