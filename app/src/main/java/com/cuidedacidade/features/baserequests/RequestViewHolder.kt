package com.cuidedacidade.features.baserequests

import android.content.Context
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cuidedacidade.NavGraphDirections
import com.cuidedacidade.R
import com.cuidedacidade.core.image.ImageEngine
import com.cuidedacidade.core.utils.DateUtils
import com.cuidedacidade.core.view.OnClickDelayedListener
import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.features.request.RequestBundle
import kotlinx.android.synthetic.main.item_request.view.*
import java.text.SimpleDateFormat
import java.util.*

class RequestViewHolder(view: View, private val imageEngine: ImageEngine) :
    RecyclerView.ViewHolder(view) {
    private val simpleDateFormat = SimpleDateFormat("dd", Locale.getDefault())
    lateinit var request: Request

    init {
        itemView.setOnClickListener(object : OnClickDelayedListener() {
            override fun onClickDelayed(view: View) {
                navigateToRequest(request, view)
            }
        })
    }

    private fun navigateToRequest(
        request: Request,
        view: View
    ) {
        val requestBundle = RequestBundle(
            request.categoryName,
            request.description,
            request.date,
            request.completionDate,
            RequestBundle.Status.valueOf(request.status.value)
        )
        val action = NavGraphDirections.detailsRequestDetailsAction(requestBundle)
        view.findNavController().navigate(action)
    }

    fun bind(request: Request) {
        this.request = request
        itemView.apply {
            txt_request_category.text = request.categoryName
            txt_request_description.text = request.description
            txt_request_date.text = extractFormattedDate(context, request.date)

            img_status.visibility = when (request.status) {
                Request.Status.EXECUTED -> View.VISIBLE
                else -> View.GONE
            }

            imageEngine.getCategoryImage(request.image, img_request)
        }
    }

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