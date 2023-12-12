package com.example.navigationbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.navigationbar.databinding.ActivityNaviBinding

private const val TAG_SEARCH = "search_fragment"
private const val TAG_PIN = "pin_fragment"
private const val TAG_HOME = "home_fragment"
private const val TAG_MY_PAGE = "my_page_fragment"

class NaviActivity : AppCompatActivity() {

    val binding by lazy { ActivityNaviBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment(TAG_HOME, HomeFragment())

        binding.navigationView.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.homeFragment -> setFragment(TAG_HOME, HomeFragment())
                R.id.searchFragment -> setFragment(TAG_SEARCH, SearchFragment())
                R.id.pinFragment -> setFragment(TAG_PIN, PinFragment())
                R.id.myPageFragment -> setFragment(TAG_MY_PAGE, MyPageFragment())
            }
            true
        }
    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if(manager.findFragmentByTag(tag) == null) {
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }
        val search = manager.findFragmentByTag(TAG_SEARCH)
        val pin = manager.findFragmentByTag(TAG_PIN)
        val home = manager.findFragmentByTag(TAG_HOME)
        val myPage = manager.findFragmentByTag(TAG_MY_PAGE)

        if(pin != null) {
            fragTransaction.hide(pin)
        }

        if(home != null) {
            fragTransaction.hide(home)
        }

        if(myPage != null) {
            fragTransaction.hide(myPage)
        }

        if(search != null) {
            fragTransaction.hide(search)
        }

        if(tag == TAG_PIN) {
            if(pin != null) {
                fragTransaction.show(pin)
            }
        }

        if(tag == TAG_HOME) {
            if(home != null) {
                fragTransaction.show(home)
            }
        }

        if(tag == TAG_MY_PAGE) {
            if(myPage != null) {
                fragTransaction.show(myPage)
            }
        }

        if(tag == TAG_SEARCH) {
            if(search != null) {
                fragTransaction.show(search)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }
}