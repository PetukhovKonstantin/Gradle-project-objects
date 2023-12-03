import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun testAdd() {
        val post = WallService.add(Post(999, 1,1, "test"))
        assertEquals(1, post.id)
    }

    @Test
    fun testUpdateTrue() {
        val post = WallService.add(Post(999, 1,1, "test"))
        val post2 = WallService.add(Post(2, 2,2, "test2"))

        val result = WallService.update(Post(1, 1, 1, "Test1"))
        assertEquals(true, result)
    }

    @Test
    fun testUpdateFalse() {
        val post = WallService.add(Post(999, 1,1, "test"))
        val post2 = WallService.add(Post(2, 2,2, "test2"))

        val result = WallService.update(Post(3, 1, 1, "Test1"))
        assertEquals(false, result)
    }

    @Test
    fun testAddWithAttachments() {
        var attachments = emptyArray<Attachment>()
        val attachment1 = StickerAttachment(Sticker(1, 1, "https://sticks.com/404", "https://anim.sticks.com/404"))
        attachments += attachment1
        val attachment2 = PhotoAttachment(Photo(1, 1, 1, 1, "Photo 1", 720, 480))
        attachments += attachment2
        val post = WallService.add(Post(999, 1,1, "test", attachments = attachments))
        assertEquals("Фото", post.attachments?.get(1)?.type)
    }
}