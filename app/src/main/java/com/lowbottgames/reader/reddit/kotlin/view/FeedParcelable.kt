package com.lowbottgames.reader.reddit.kotlin.view

import android.os.Parcel
import android.os.Parcelable

data class FeedParcelable(
    val title: String?,
    val author: String?,
    val thumbnail: String?,
    val text: String?,
    val url: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(thumbnail)
        parcel.writeString(text)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FeedParcelable> {
        override fun createFromParcel(parcel: Parcel): FeedParcelable {
            return FeedParcelable(parcel)
        }

        override fun newArray(size: Int): Array<FeedParcelable?> {
            return arrayOfNulls(size)
        }
    }
}