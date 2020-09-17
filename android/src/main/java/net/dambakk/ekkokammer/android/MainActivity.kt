package net.dambakk.ekkokammer.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.ui.platform.setContent
import net.dambakk.ekkokammer.android.theme.EkkoTheme
import net.dambakk.ekkokammer.common.Article
import net.dambakk.ekkokammer.common.Greeting

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EkkoTheme {
                    Column {
                        Text(text = "Number of articles: ${allArticles.size}")
                        allArticles.forEach {
                            ArticleSmall(it)
                        }
                    }
            }
        }
    }
}

val article1 = Article(
    title = "Isolerte folk om bord - droppet å teste dem",
    subtitle = "Hurtigruten fant feil på feil hos seg selv",
    imageUrl = "https://gfx.nrk.no/0jEhewyi3PdxQ1BHgDVz0wPzb_ShZHbVq7JQ8Q7847wA",
    originalUrl = "https://www.nrk.no/tromsogfinnmark/_-karantenereglene-ble-vurdert-av-kokk-1.15164253",
    isBehindPayWall = false,
    provider = "nrk",
    classifications = emptyList(),
    isRead = false
)

val article2 = Article(
    title = "Her blir det 20 varmegrader",
    subtitle = null,
    imageUrl = "https://gfx.nrk.no/hZ6RjPYMfpLcBv3lTvlN2wU96nYaY6FvH4na3noC6RfA",
    originalUrl = "https://www.nrk.no/vestland/storm-og-regn-i-nord-_-sol-og-20-pluss-i-sor-1.15165082",
    isBehindPayWall = false,
    provider = "nrk",
    classifications = emptyList(),
    isRead = false
)


val article3 = Article(
    title = "Man skulle ikke reist 17.juli",
    subtitle = null,
    imageUrl = "https://akamai.vgc.no/drfront/images/2020/09/17/c=1280,69,2739,2422;w=527;h=466;555914.jpg",
    originalUrl = "https://direkte.vg.no/nyhetsdognet/news/-man-skulle-ikke-reist-den-17-juli.86Fzxv_fA?utm_source=vgfront&utm_content=row-1",
    isBehindPayWall = false,
    provider = "vg",
    classifications = emptyList(),
    isRead = false
)

val article4 = Article(
    title = "Ikke bestill billetter enda. Det er ikke sikkert han overlever natten",
    subtitle = null,
    imageUrl = "https://g.acdn.no/obscura/API/dynamic/r1/external/tr_1000_750_l_f/2020-09-16T23%3A02%3A07.000%2B0200/https%3A//smooth-storage.aptoma.no/users/drf-amedia/images/32663207.jpg%3FaccessToken%3D9aa2e026717915efa62636bd7c0f288486482f6d8183eb9d015a8f5375c939ff?chk=C07E03",
    originalUrl = "https://pluss.nettavisen.no/bosniaveteranen-kjartan-ble-skadet-for-livet-folk-vet-ikke-hva-jeg-har-sett-og-opplevd/s/5-8-1379432",
    isBehindPayWall = false,
    provider = "nettavisen",
    classifications = emptyList(),
    isRead = false
)

val article5 = Article(
    title = "Solskjærs fire dilemmaer",
    subtitle = null,
    imageUrl = "https://akamai.vgc.no/drfront/images/2020/09/17/c=446,109,694,533;w=527;h=404;555922.jpg",
    originalUrl = "https://www.vg.no/sport/fotball/i/6znW2O/solskjaers-fire-dilemmaer-har-komplisert-situasjonen",
    isBehindPayWall = false,
    provider = "vg",
    classifications = emptyList(),
    isRead = false
)


val allArticles = listOf(
    article1, article2, article3, article4, article5
)
