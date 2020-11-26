package net.dambakk.ekkokammer.common


val article1 = Article(
    title = "Nye smitteverntiltak vurderes i Oslo",
    subtitle = null,
    imageUrl = "https://gfx.nrk.no/EvXQ8B9sVlcvB68hq3hbnwOAMbVlyScm9gU_avTzahVQ.jpg",
    originalUrl = "https://www.nrk.no/norge/nye-smitteverntiltak-vurderes-i-oslo-1.15166715",
    isBehindPayWall = false,
    provider = "nrk",
    classifications = listOf(
        Classification("id-1", "Hurtigruten", 69),
        Classification("id-2", "Norge", 15)
    ),
    isRead = false
)

val article2 = Article(
    title = "Her blir det 20 varmegrader",
    subtitle = null,
    imageUrl = "https://gfx.nrk.no/hZ6RjPYMfpLcBv3lTvlN2wU96nYaY6FvH4na3noC6RfA",
    originalUrl = "https://www.nrk.no/vestland/storm-og-regn-i-nord-_-sol-og-20-pluss-i-sor-1.15165082",
    isBehindPayWall = false,
    provider = "nrk",
    classifications = listOf(
        Classification("id-3", "Vær", 1),
        Classification("id-2", "Norge", 17),
        Classification("id-3", "Uinteressant", 1)
    ),
    isRead = false
)


val article3 = Article(
    title = "Til frontalangrep på Rema 1000: –\u2060 Det er direkte løgn",
    subtitle = null,
    imageUrl = "https://g.acdn.no/obscura/API/dynamic/r1/external/tr_1280_5000_s_f/2001-01-01T01%3A01%3A01.000%2B0200/https%3A//smooth-storage.aptoma.no/users/drf-amedia/images/32877904.jpg%3FaccessToken%3D39377d973c5a7bb30e38bdbdee5d4968a5df881f5065f8d64ac20946126ee4b3?chk=737C28",
    originalUrl = "https://www.nettavisen.no/okonomi/raser-mot-rema-produkter---direkte-logn/3424018983.html",
    isBehindPayWall = false,
    provider = "nettavisen",
    classifications = listOf(
        Classification("id-4", "Meningsbasert", 26),
        Classification("id-4", "Rema 1000", 12)
    ),
    isRead = false
)

val article4 = Article(
    title = "Ikke bestill billetter enda. Det er ikke sikkert han overlever natten",
    subtitle = null,
    imageUrl = "https://g.acdn.no/obscura/API/dynamic/r1/external/tr_1000_750_l_f/2020-09-16T23%3A02%3A07.000%2B0200/https%3A//smooth-storage.aptoma.no/users/drf-amedia/images/32663207.jpg%3FaccessToken%3D9aa2e026717915efa62636bd7c0f288486482f6d8183eb9d015a8f5375c939ff?chk=C07E03",
    originalUrl = "https://pluss.nettavisen.no/bosniaveteranen-kjartan-ble-skadet-for-livet-folk-vet-ikke-hva-jeg-har-sett-og-opplevd/s/5-8-1379432",
    isBehindPayWall = true,
    provider = "nettavisen",
    classifications = listOf(
        Classification("id-1", "Søppel", 10000)
    ),
    isRead = false
)

val article5 = Article(
    title = "Solskjærs fire dilemmaer",
    subtitle = null,
    imageUrl = "https://akamai.vgc.no/drfront/images/2020/09/17/c=446,109,694,533;w=527;h=404;555922.jpg",
    originalUrl = "https://www.vg.no/sport/fotball/i/6znW2O/solskjaers-fire-dilemmaer-har-komplisert-situasjonen",
    isBehindPayWall = false,
    provider = "vg",
    classifications = listOf(
        Classification("id-1", "Solskjær", 100),
        Classification("id-2", "Fotball", 81)
    ),
    isRead = false
)


val article6 = Article(
    title = "Shoppingtips\n" +
            "De 69 råeste gavene du kan kjøpe",
    subtitle = null,
    imageUrl = "https://g.acdn.no/obscura/API/dynamic/r1/external/tr_1000_750_l_f/2020-09-19T00%3A20%3A18.000%2B0200/https%3A//smooth-storage.aptoma.no/users/drf-amedia/images/32887831.jpg%3FaccessToken%3Dee0451ccd2a68129c61937a03bc1259512caf1e6bef7cbabcaaecf8d5298a255?chk=49C434",
    originalUrl = "https://www.nettavisen.no/livsstil/shopping/de-69-raeste-gavene-du-kan-kjope/3424021000.html",
    isBehindPayWall = false,
    provider = "nettavisen",
    classifications = listOf(
        Classification("id-4", "Søppel", 69),
        Classification("id-4", "Clickbait", 69)
    ),
    isRead = false
)

val article7 = Article(
    title = "Trine: - Slik er underlivet mitt",
    subtitle = null,
    imageUrl = "https://dbstatic.no/72858309.jpg?imageId=72858309&x=0.000000&y=18.888889&cropw=99.469496&croph=52.647975&width=320&height=237",
    originalUrl = "https://www.dagbladet.no/tema/norske-kvinner-forteller-slik-er-underlivet-vart/72583089",
    isBehindPayWall = true,
    provider = "dagbladet",
    classifications = listOf(
        Classification("id-4", "Sex", 69),
        Classification("id-4", "Clickbait", 10000)
    ),
    isRead = false
)

val allArticles = listOf(
//    article1, article2, article3, article4, article5, article6, article7
    article1
)


val sampleTopics = listOf(
    TopicModel("Politikk", true),
    TopicModel("Sport", false),
    TopicModel("Trump", false),
    TopicModel("MeToo", true),
    TopicModel("Klima", true),
    TopicModel("Hurtigruta", false),
    TopicModel("Norge", true),
    TopicModel("Sex", true),
    TopicModel("Clickbait", true),
)

