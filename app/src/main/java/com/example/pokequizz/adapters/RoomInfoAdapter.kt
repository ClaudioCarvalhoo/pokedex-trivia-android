package com.example.pokequizz.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.pokequizz.apiHelper.entities.Room
import com.example.pokequizz.fragments.roomInfo.GeneralFragment
import com.example.pokequizz.R
import com.example.pokequizz.fragments.roomInfo.LeaderboardFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
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
        if (position == 1) {
            return LeaderboardFragment.newInstance(room)
        }

        return GeneralFragment.newInstance(room)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}
