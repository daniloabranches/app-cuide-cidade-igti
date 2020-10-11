package com.cuidedacidade.ui.categories.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryModel(val id: String, val title: String, val image: String) : Parcelable