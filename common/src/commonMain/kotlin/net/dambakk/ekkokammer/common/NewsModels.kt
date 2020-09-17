package net.dambakk.ekkokammer.common

data class Article(
    val title: String,
    val subtitle: String?,
    val imageUrl: String,
    val originalUrl: String,
    val isBehindPayWall: Boolean,
    val provider: String,
    val classifications: List<Classification>,
    val isRead: Boolean
)

data class Classification(
    val id: String,
    val title: String,
    val votes: Int
)

