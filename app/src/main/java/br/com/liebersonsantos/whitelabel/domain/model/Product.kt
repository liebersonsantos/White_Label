package br.com.liebersonsantos.whitelabel.domain.model

import android.os.Parcelable
import com.google.firebase.firestore.PropertyName
import kotlinx.android.parcel.Parcelize

/**
 * Created by lieberson on 8/10/21.
 * @author lieberson.xsantos@gmail.com
 */
@Parcelize
data class Product(
    val id: String = "",
    val description: String = "",
    val price: Double = 0.0,
    @get:PropertyName("image_url")
    @set:PropertyName("image_url")
    var imageUrl: String = ""
): Parcelable