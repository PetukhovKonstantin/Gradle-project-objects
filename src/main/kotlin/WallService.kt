object WallService {
    private var posts = emptyArray<Post>()
    private var lastId = 0;

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((i, p) in posts.withIndex()) {
            if (post.id == p.id) {
                posts[i] = post.copy();
                return true;
            }
        }
        return false;
    }

    fun clear() {
        posts = emptyArray()
        lastId = 0;
    }
}