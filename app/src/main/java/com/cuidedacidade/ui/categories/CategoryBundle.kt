package com.cuidedacidade.ui.categories

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryBundle(val id: String, val title: String, val image: String) : Parcelable