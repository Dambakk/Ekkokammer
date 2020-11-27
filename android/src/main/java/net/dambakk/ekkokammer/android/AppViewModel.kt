package net.dambakk.ekkokammer.android

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.prof.rssparser.Article
import com.prof.rssparser.Channel
import com.prof.rssparser.Parser
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.nio.charset.Charset

class AppViewModel(app: Application) : AndroidViewModel(app) {

    private val nrkRss = "https://www.nrk.no/toppsaker.rss"

    private val _nrkInfo: MutableLiveData<Channel> = MutableLiveData()
    val nrkInfo: LiveData<Channel> get() = _nrkInfo

    var articlesRead = MutableLiveData<MutableSet<String>>(mutableSetOf())

    val nrkArticlesLiveData: MutableLiveData<List<Article>> = MutableLiveData(emptyList())

    init {
        viewModelScope.launch {
            while (true) {
                val data = Parser().getChannel(nrkRss)
                _nrkInfo.postValue(data)
                nrkArticlesLiveData.postValue(data.articles)
                delay(5_000)
            }
        }
    }

    val nrkArticles = flow<List<Article>> {
        val data = Parser().getChannel(nrkRss)
        _nrkInfo.postValue(data)
        nrkArticlesLiveData.postValue(data.articles)
        emit(data.articles)
    }

    fun addArticleRead(url: String) {
        articlesRead.value?.add(url)
    }
}