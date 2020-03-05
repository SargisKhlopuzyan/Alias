package app.sargis.khlopuzyan.alias.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import app.sargis.khlopuzyan.alias.ui.common.OnBackPressed
import app.sargis.khlopuzyan.alias.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(
                    android.R.id.content,
                    MainFragment.newInstance(),
                    "fragment_main"
                )
            }
        }
    }


    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(android.R.id.content)

        if (fragment is OnBackPressed) {
            fragment.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }
}
