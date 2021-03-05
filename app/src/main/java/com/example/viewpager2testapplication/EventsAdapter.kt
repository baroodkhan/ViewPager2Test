package com.example.viewpager2testapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.viewpager2testapplication.databinding.BundleItemFilterTitleBinding
import com.example.viewpager2testapplication.databinding.EventItemBinding
import timber.log.Timber


const val headerView = 0
const val contentView = 1


class EventsAdapter() :
    RecyclerView.Adapter<EventsAdapter.MyViewHolder>() {
    private var mContext: Context? = null
    lateinit var bundleItemBinding: EventItemBinding
    lateinit var bundleItemTitleBinding: BundleItemFilterTitleBinding

    val DIFF_CALLBACK =
        object : DiffUtil.ItemCallback<DailyEvents>() {
            override fun areItemsTheSame(
                oldItem: DailyEvents,
                newItem: DailyEvents
            ): Boolean {
                return oldItem.day == newItem.day
                        && oldItem.eventLocation == newItem.eventLocation
                        && oldItem.eventName == newItem.eventName
                        && oldItem.hasTickets == newItem.hasTickets
                        && oldItem.ticketPrice == newItem.ticketPrice

            }

            override fun areContentsTheSame(
                oldItem: DailyEvents,
                newItem: DailyEvents
            ): Boolean {
                return oldItem == newItem
                        && oldItem == newItem
            }

        }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    inner class MyViewHolder : RecyclerView.ViewHolder {
        constructor(binding: BundleItemFilterTitleBinding) : super(binding.root) {
            bundleItemTitleBinding = binding
        }

        constructor(binding: EventItemBinding) : super(binding.root) {
            bundleItemBinding = binding
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            headerView
        } else {
            contentView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding
        mContext = parent.context
        return when (viewType) {
            headerView -> {
                binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.bundle_item_filter_title,
                    parent,
                    false
                )
                MyViewHolder(binding as BundleItemFilterTitleBinding)
            }
            else -> {
                binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.event_item,
                    parent,
                    false
                )
                MyViewHolder(binding as EventItemBinding)
            }
        }
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        when (holder.itemViewType) {
            headerView -> {
                bundleItemTitleBinding.tvFilterTitle.text = differ.currentList[position].eventName
            }
            contentView -> {
                val items = differ.currentList[position] //
                bindPromData(items)
            }
        }
    }

    private fun bindPromData(item: DailyEvents?) {
        Timber.d(("item is $item"))
        bundleItemBinding.price.text = item?.ticketPrice
        bundleItemBinding.location.text = item?.eventLocation
        bundleItemBinding.name.text = item?.eventName
        bundleItemBinding.ticket.text = item?.hasTickets
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun submitList(list: List<DailyEvents>) {
        Timber.d("Adapter list Ids are  ${list.map { it.eventName }}}")
        differ.submitList(list)
        Timber.d("Differ list bundles Ids are ${differ.currentList.map { it.eventName }}")
    }
}
