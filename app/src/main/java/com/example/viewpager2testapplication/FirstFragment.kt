package com.example.viewpager2testapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var mActivity: FragmentActivity
    private val tabList =
        listOf<String>("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager(getData(), tabList)
        initTab()
    }


    private fun initTab() {
        val tabs = view?.findViewById<TabLayout>(R.id.tablsMain)
        if (tabs != null) {
            TabLayoutMediator(tabs, viewPager2) { tab, position ->
                val tabText = tabList[position]
                Timber.d("Setting tab text $tabText")
                tab.text = tabText
            }.attach()
        }

    }

    private fun initViewPager(result: List<DailyEvents>, tabs: List<String>) {
        Timber.d("Initializing view pager adapter...")
        val bundleCollectionAdapter = BundleCollectionAdapter(this, tabs.size, result, tabs)
        viewPager2 = view?.findViewById<ViewPager2>(R.id.viewPager2)!!
        viewPager2.adapter = bundleCollectionAdapter
        viewPager2.offscreenPageLimit = 1

    }

    class BundleCollectionAdapter(
        fragment: Fragment,
        count: Int,
        data: List<DailyEvents>,
        tabs: List<String>
    ) :
        FragmentStateAdapter(fragment) {
        private val tabsCount = count
        private val eventsData = data
        private val tabsData = tabs
        override fun getItemCount(): Int = tabsCount

        override fun createFragment(position: Int): Fragment {
            val bundleType = tabsData[position]
            Timber.d("Initializing fragment for type: $bundleType")
            val data = eventsData.filter { it.day == position.toString() }
            return EventsFragment.create(data)
        }
    }

    private fun getData(): MutableList<DailyEvents> {
        val data = mutableListOf<DailyEvents>()
        data.add(DailyEvents("Sunday`s Events", "Sunday Park", "Free", "0", "0"))
        data.add(DailyEvents("Sunday Event1", "Sunday Park", "Free", "0", "0"))
        data.add(DailyEvents("Sunday Event2", "Sunday Park", "Free", "0", "0"))
        data.add(DailyEvents("Sunday Event3", "Sunday Park", "Free", "0", "0"))
        data.add(DailyEvents("Sunday Event4", "Sunday Park", "Free", "0", "0"))
        data.add(DailyEvents("Sunday Event5", "Sunday Park", "Free", "0", "0"))
        data.add(DailyEvents("Sunday Event6", "Sunday Park", "Free", "0", "0"))
        data.add(DailyEvents("Sunday Event7", "Sunday Park", "Free", "0", "0"))
        data.add(DailyEvents("Sunday Event8", "Sunday Park", "Free", "0", "0"))
        data.add(DailyEvents("Sunday Event9", "Sunday Park", "Free", "0", "0"))
        data.add(DailyEvents("Sunday Event10", "Sunday Park", "Free", "0", "0"))
        data.add(DailyEvents("Sunday Event11", "Sunday Park", "Free", "0", "0"))
        data.add(DailyEvents("Sunday Event12", "Sunday Park", "Free", "0", "0"))
        data.add(DailyEvents("Sunday Event13", "Sunday Park", "Free", "0", "0"))


        data.add(DailyEvents("Monday`s Events", "On premises", "Free", "0", "1"))
        data.add(DailyEvents("Monday Event1", "On premises", "Free", "0", "1"))
        data.add(DailyEvents("Monday Event2", "On premises", "Free", "0", "1"))
        data.add(DailyEvents("Monday Event3", "On premises", "Free", "0", "1"))
        data.add(DailyEvents("Monday Event4", "On premises", "Free", "0", "1"))
        data.add(DailyEvents("Monday Event5", "On premises", "Free", "0", "1"))
        data.add(DailyEvents("Monday Event6", "On premises", "Free", "0", "1"))
        data.add(DailyEvents("Monday Event7", "On premises", "Free", "0", "1"))
        data.add(DailyEvents("Monday Event8", "On premises", "Free", "0", "1"))
        data.add(DailyEvents("Monday Event9", "On premises", "Free", "0", "1"))
        data.add(DailyEvents("Monday Event10", "On premises", "Free", "0", "1"))
        data.add(DailyEvents("Monday Event11", "On premises", "Free", "0", "1"))
        data.add(DailyEvents("Monday Event12", "On premises ", "Free", "0", "1"))
        data.add(DailyEvents("Monday Event13", "On premises ", "Free", "0", "1"))

        data.add(DailyEvents("Tuesday`s Events", "On premises", "Yes", "100$", "2"))
        data.add(DailyEvents("Tuesday Event1", "On premises", "Yes", "100$", "2"))
        data.add(DailyEvents("Tuesday Event2", "On premises", "Yes", "100$", "2"))
        data.add(DailyEvents("Tuesday Event3", "On premises", "Yes", "100$", "2"))
        data.add(DailyEvents("Tuesday Event4", "On premises", "Yes", "100$", "2"))
        data.add(DailyEvents("Tuesday Event5", "On premises", "Yes", "100$", "2"))
        data.add(DailyEvents("Tuesday Event6", "On premises", "Yes", "100$", "2"))
        data.add(DailyEvents("Tuesday Event7", "On premises", "Yes", "100$", "2"))
        data.add(DailyEvents("Tuesday Event8", "On premises", "Yes", "100$", "2"))
        data.add(DailyEvents("Tuesday Event9", "On premises", "Yes", "100$", "2"))
        data.add(DailyEvents("Tuesday Event10", "On premises", "Yes", "100$", "2"))
        data.add(DailyEvents("Tuesday Event11", "On premises", "Yes", "100$", "2"))
        data.add(DailyEvents("Tuesday Event12", "On premises ", "Yes", "100$", "2"))
        data.add(DailyEvents("Tuesday Event13", "On premises ", "Yes", "100$", "2"))

        data.add(DailyEvents("Wednesday`s Events", "On premises", "Yes", "100$", "3"))
        data.add(DailyEvents("Wednesday Event1", "On premises", "Yes", "100$", "3"))
        data.add(DailyEvents("Wednesday Event2", "On premises", "Yes", "100$", "3"))
        data.add(DailyEvents("Wednesday Event3", "On premises", "Yes", "100$", "3"))
        data.add(DailyEvents("Wednesday Event4", "On premises", "Yes", "100$", "3"))
        data.add(DailyEvents("Wednesday Event5", "On premises", "Yes", "100$", "3"))
        data.add(DailyEvents("Wednesday Event6", "On premises", "Yes", "100$", "3"))
        data.add(DailyEvents("Wednesday Event7", "On premises", "Yes", "100$", "3"))
        data.add(DailyEvents("Wednesday Event8", "On premises", "Yes", "100$", "3"))
        data.add(DailyEvents("Wednesday Event9", "On premises", "Yes", "100$", "3"))
        data.add(DailyEvents("Wednesday Event10", "On premises", "Yes", "100$", "3"))
        data.add(DailyEvents("Wednesday Event11", "On premises", "Yes", "100$", "3"))
        data.add(DailyEvents("Wednesday Event12", "On premises ", "Yes", "100$", "3"))
        data.add(DailyEvents("Wednesday Event13", "On premises ", "Yes", "100$", "3"))


        data.add(DailyEvents("Thursday`s Events", "On premises", "Yes", "100$", "4"))
        data.add(DailyEvents("Thursday Event1", "On premises", "Yes", "100$", "4"))
        data.add(DailyEvents("Thursday Event2", "On premises", "Yes", "100$", "4"))
        data.add(DailyEvents("Thursday Event3", "On premises", "Yes", "100$", "4"))
        data.add(DailyEvents("Thursday Event4", "On premises", "Yes", "100$", "4"))
        data.add(DailyEvents("Thursday Event5", "On premises", "Yes", "100$", "4"))
        data.add(DailyEvents("Thursday Event6", "On premises", "Yes", "100$", "4"))
        data.add(DailyEvents("Thursday Event7", "On premises", "Yes", "100$", "4"))
        data.add(DailyEvents("Thursday Event8", "On premises", "Yes", "100$", "4"))
        data.add(DailyEvents("Thursday Event9", "On premises", "Yes", "100$", "4"))
        data.add(DailyEvents("Thursday Event10", "On premises", "Yes", "100$", "4"))
        data.add(DailyEvents("Thursday Event11", "On premises", "Yes", "100$", "4"))
        data.add(DailyEvents("Thursday Event12", "On premises ", "Yes", "100$", "4"))
        data.add(DailyEvents("Thursday Event13", "On premises ", "Yes", "100$", "4"))


        data.add(DailyEvents("Friday`s Events", "On premises", "Yes", "100$", "5"))
        data.add(DailyEvents("Friday Event1", "On premises", "Yes", "100$", "5"))
        data.add(DailyEvents("Friday Event2", "On premises", "Yes", "100$", "5"))
        data.add(DailyEvents("Friday Event3", "On premises", "Yes", "100$", "5"))
        data.add(DailyEvents("Friday Event4", "On premises", "Yes", "100$", "5"))
        data.add(DailyEvents("Friday Event5", "On premises", "Yes", "100$", "5"))
        data.add(DailyEvents("Friday Event6", "On premises", "Yes", "100$", "5"))
        data.add(DailyEvents("Friday Event7", "On premises", "Yes", "100$", "5"))
        data.add(DailyEvents("Friday Event8", "On premises", "Yes", "100$", "5"))
        data.add(DailyEvents("Friday Event9", "On premises", "Yes", "100$", "5"))
        data.add(DailyEvents("Friday Event10", "On premises", "Yes", "100$", "5"))
        data.add(DailyEvents("Friday Event11", "On premises", "Yes", "100$", "5"))
        data.add(DailyEvents("Friday Event12", "On premises ", "Yes", "100$", "5"))
        data.add(DailyEvents("Friday Event13", "On premises ", "Yes", "100$", "6"))

        data.add(DailyEvents("Saturday`s Events", "On premises", "Yes", "100$", "6"))
        data.add(DailyEvents("Saturday Event1", "On premises", "Yes", "100$", "6"))
        data.add(DailyEvents("Saturday Event2", "On premises", "Yes", "100$", "6"))
        data.add(DailyEvents("Saturday Event3", "On premises", "Yes", "100$", "6"))
        data.add(DailyEvents("Saturday Event4", "On premises", "Yes", "100$", "6"))
        data.add(DailyEvents("Saturday Event5", "On premises", "Yes", "100$", "6"))
        data.add(DailyEvents("Saturday Event6", "On premises", "Yes", "100$", "6"))
        data.add(DailyEvents("Saturday Event7", "On premises", "Yes", "100$", "6"))
        data.add(DailyEvents("Saturday Event8", "On premises", "Yes", "100$", "6"))
        data.add(DailyEvents("Saturday Event9", "On premises", "Yes", "100$", "6"))
        data.add(DailyEvents("Saturday Event10", "On premises", "Yes", "100$", "6"))
        data.add(DailyEvents("Saturday Event11", "On premises", "Yes", "100$", "6"))
        data.add(DailyEvents("Saturday Event12", "On premises ", "Yes", "100$", "6"))
        data.add(DailyEvents("Saturday Event13", "On premises ", "Yes", "100$", "6"))
        return data
    }
}


data class DailyEvents(
    val eventName: String,
    val eventLocation: String,
    val hasTickets: String,
    val ticketPrice: String,
    val day: String
)