package net.dambakk.ekkokammer.android

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.util.*
import kotlinx.serialization.Serializable

@KtorExperimentalAPI
private val client = HttpClient(CIO) {

}

@KtorExperimentalAPI
suspend fun getNrkFrontpage(): String {
    val content = client.get<String>("https://www.nrk.no/toppsaker.rss")
    Log.d("NRK", "NRK DATA: $content")
    return content
}

@Serializable
data class Rss(
    val channel: Channel
)

@Serializable
data class Channel(
    val title: String,
    val link: String,
    val description: String,
    val language: String,
    val lastBuildDate: String,
    val image: String, //TODO
    val items: List<String>
)