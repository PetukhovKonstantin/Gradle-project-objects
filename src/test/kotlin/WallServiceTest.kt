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
}