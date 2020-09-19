package net.dambakk.ekkokammer.android

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class AppViewModel(app: Application) : AndroidViewModel(app) {


    var articlesRead = MutableLiveData<MutableList<String>>(mutableListOf())

    fun addArticleRead(url: String) {
        articlesRead.value?.add(url)
    }

//    val filters = remember {
//
//    }
}