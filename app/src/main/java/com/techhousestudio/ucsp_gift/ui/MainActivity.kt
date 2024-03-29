package com.techhousestudio.ucsp_gift.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.techhousestudio.ucsp_gift.R
import com.techhousestudio.ucsp_gift.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        setSupportActionBar(binding.bar)

        val navController = findNavController(R.id.nav_host_fragment)






        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.favouriteFragment), binding.drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        val navGraphIds = listOf(R.navigation.navigation)


        var bottomNavigationView: BottomNavigationView
//        binding.n.setupWithNavController(
//            navGraphIds = navGraphIds,
//            fragmentManager = supportFragmentManager,
//            containerId = R.id.nav_host_fragment,
//            intent = intent
//        );





        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.giftDetailFragment) {
                binding.fab.visibility = View.GONE
                binding.bar.visibility = View.GONE
            } else {
                binding.fab.visibility = View.VISIBLE
                binding.bar.visibility = View.VISIBLE
            }
//            if (destination.id == R.id.favouriteFragment || destination.id == R.id.homeFragment) {
//                binding.fab.setImageResource(R.drawable.ic_fab_filter)
//                binding.fab.setOnClickListener {
//                    findNavController(R.id.nav_host_fragment).navigate(R.id.filterDialogFragment)
//
//                }
//            } else {
//                binding.fab.setImageResource(R.drawable.ic_fab_chat)
//                binding.fab.setOnClickListener {
//                    val chat = ChatBottomSheetDialogFragment()
//                    chat.show(supportFragmentManager, "chat")
//
//                }
//            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return item!!.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }


}
