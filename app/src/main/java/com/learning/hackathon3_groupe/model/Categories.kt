package com.learning.hackathon3_groupe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Rahmat Hidayat on 20/09/2023.
 */
@Parcelize
data class Categories(
     val no:Int,
     val name:String,
     val imageCategories:String,
):Parcelable