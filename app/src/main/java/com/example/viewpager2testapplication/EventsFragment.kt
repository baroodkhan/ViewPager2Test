package com.example.viewpager2testapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber
import java.lang.reflect.Type

internal const val ARG_OBJECT = "object"

class EventsFragment : Fragment() {
    private lateinit var eventsData: List<DailyEvents>
    private lateinit var rvEvents: RecyclerView
    private lateinit var mActivity: FragmentActivity
//    private var lastFirstVisiblePosition: Int = 0


    private val eventsAdapter: EventsAdapter = EventsAdapter()
    var listType: Type = object : TypeToken<List<DailyEvents>>() {}.type

    companion object {
        /** Creates a Fragment for a given [EventsFragment]  */
        fun create(bundlesData: List<DailyEvents>): EventsFragment {
            val fragment = EventsFragment()
            fragment.arguments = Bundle().apply {
                putString(ARG_OBJECT, Gson().toJson(bundlesData))
            }
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        navController = Navigation.findNavController(view)
        Timber.d("OnCreateView")
        if (activity != null) {
            mActivity = activity as FragmentActivity
        }
        rvEvents = view.findViewById<RecyclerView>(R.id.recyclerViewForEvents)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.d("OnActivityCreated")
        initRecyclerVIew()
        initBundleData()
        view?.findViewById<ImageView>(R.id.ivFilter)?.setOnClickListener {

            eventsAdapter.submitList(eventsData.shuffled().take(8))
        }
    }

    private fun initBundleData() {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            Timber.d("""type in argument is ${getString(ARG_OBJECT)}""")
            eventsData = Gson().fromJson<List<DailyEvents>>(
                getString(ARG_OBJECT),
                listType
            )
            if (eventsData != null) {
                eventsAdapter.submitList(eventsData)
            }
        }
    }

    private fun initRecyclerVIew() {
        rvEvents.layoutManager = LinearLayoutManager(context)
        rvEvents.adapter = eventsAdapter
    }

    override fun onPause() {
        super.onPause() //
    }

    override fun onResume() {
        super.onResume()
    }
}
