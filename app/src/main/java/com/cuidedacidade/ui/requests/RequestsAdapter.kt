package com.cuidedacidade.ui.requests

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cuidedacidade.R
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.image.ImageEngine
import com.cuidedacidade.utils.DateUtils
import kotlinx.android.synthetic.main.item_request.view.*
import java.text.SimpleDateFormat
import java.util.*

class RequestsAdapter(requests: List<Request>) :
    RecyclerView.Adapter<RequestsAdapter.ViewHolder>() {

    private val requests: List<Request> by lazy {
        requests.sortedByDescending { it.date }
    }

    private val simpleDateFormat = SimpleDateFormat("dd", Locale.getDefault())

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val from = LayoutInflater.from(parent.context)
        val view = from.inflate(R.layout.item_request, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.run {
            val request = requests[position]
            txt_category_request.text = request.categoryName
            txt_description_request.text = request.description
            txt_date_request.text = extractFormattedDate(context, request.date)

            val reference =
                ImageEngine.getCategoriesStorageReference(resources).child(request.image)
            Glide.with(this).load(reference).into(img_request)
        }
    }

    override fun getItemCount() = requests.size

    private fun extractFormattedDate(
        context: Context,
        date: Date
    ): String {
        return when {
            DateUtils.isToday(
                date
            ) -> context.getString(R.string.today)
            DateUtils.isYesterday(
                date
            ) -> context.getString(R.string.yesterday)
            else -> {
                val calendar = Calendar.getInstance().also { it.time = date }
                val formattedDate = simpleDateFormat.format(date)
                val monthIndex = calendar.get(Calendar.MONTH)
                val monthPrefix = getMonthPrefix(context, monthIndex)
                "$formattedDate de $monthPrefix"
            }
        }
    }

    private fun getMonthPrefix(context: Context, monthIndex: Int): String {
        context.run {
            val monthsPrefix = listOf(
                getString(R.string.jan),
                getString(R.string.feb),
                getString(R.string.mar),
                getString(R.string.apr),
                getString(R.string.may),
                getString(R.string.jun),
                getString(R.string.jul),
                getString(R.string.aug),
                getString(R.string.sep),
                getString(R.string.oct),
                getString(R.string.nov),
                getString(R.string.dec)
            )
            return monthsPrefix[monthIndex]
        }
    }
}