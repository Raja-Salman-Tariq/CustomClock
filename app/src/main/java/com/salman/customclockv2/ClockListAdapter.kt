package com.salman.customclockv2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView


class ClockListAdapter(private val ctxt: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var imgIds = mutableListOf<Int>()
    var selection = -1
    private var selectedView : ClockListViewHolder? = null

    fun setData(data:List<Int>){
        imgIds.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = ClockListViewHolder(LayoutInflater.from(ctxt).inflate(R.layout.clock_list_item_layout, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, idx: Int) {
        (holder as ClockListViewHolder).apply {
            ivClock.setImageResource(imgIds[idx])
            itemView.setOnClickListener{
                updSelection(idx, this)
            }
        }
    }

    override fun getItemCount() = imgIds.size

    class ClockListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivClock: ImageView = itemView.findViewById(R.id.ivClock)
        val selection : MaterialCardView = itemView.findViewById(R.id.cvClock)

        init {
            selection.setCardForegroundColor(ContextCompat.getColorStateList(itemView.context, R.color.clock_cv_selected_foreground))
            selection.setStrokeColor(ContextCompat.getColorStateList(itemView.context, R.color.clock_cv_selected_border))
        }
    }

    private fun updSelection(idx:Int, newlySelected : ClockListViewHolder){
        selection = idx
        selectedView?.selection?.isSelected = false
        selectedView=newlySelected.also { it.selection.isSelected = true }
    }

}
