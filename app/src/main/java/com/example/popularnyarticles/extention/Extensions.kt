package com.example.popularnyarticles.extention

import android.view.View
import com.example.popularnyarticles.network.model.PopularArticle

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}
fun PopularArticle.getPhotoUrl():String{
    for(mediaItem in this.media){
        if(mediaItem.type == "image"){
            for (mediaMetaDataItem in mediaItem.mediaMetadata)
                return mediaMetaDataItem.url
        }
    }
    return ""
}