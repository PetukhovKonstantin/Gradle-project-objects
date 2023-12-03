data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val text: String,
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val friendsOnly: Boolean = false,
    val markedIsAds: Boolean = false,
    var likes: Likes? = null
)