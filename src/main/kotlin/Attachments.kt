abstract class Attachment(val type: String)

data class AudioAttachment(val audio: Audio): Attachment("Аудио")

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String,
    val lyricsId: Int = 0,
    val albumId: Int = 0,
    val genreId: Int = 0
)

data class VideoAttachment(val video: Video): Attachment("Видео")

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val duration: Int,
    val player: String,
    val description: String = "",
    val views: Int = 0,
    val canAdd: Boolean = true,
    val isPrivate: Boolean = false
)

data class PhotoAttachment(val photo: Photo): Attachment("Фото")

data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int,
    val text: String,
    val width: Int,
    val height: Int
)

data class FileAttachment(val file: File): Attachment("Файл")

data class File(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val ext: String,
    val url: String,
    val type: Int = 8
)

data class StickerAttachment(val sticker: Sticker): Attachment("Стикер")

data class Sticker(
    val productId: Int,
    val stickerId: Int,
    val imageUrl: String,
    val animationUrl: String,
    val isAllowed: Boolean = true
)