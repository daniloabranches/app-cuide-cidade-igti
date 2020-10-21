package com.cuidedacidade.feature.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryBundle(val title: String, val image: String) : Parcelable