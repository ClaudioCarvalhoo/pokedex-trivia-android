package com.example.pokequizz.Adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.pokequizz.ApiHelper.Entities.Room
import com.example.pokequizz.Fragments.RoomInfo.GeneralFragment
import com.example.pokequizz.R

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class RoomInfoAdapter(private val context: Context, fm: FragmentManager, room: Room?) :
    FragmentPagerAdapter(fm) {

    private val room = room

    override fun getItem(position: Int): Fragment {
        var instance = GeneralFragment

        when(position) {
            0 -> instance = GeneralFragment
        }

        return instance.newInstance(room)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}