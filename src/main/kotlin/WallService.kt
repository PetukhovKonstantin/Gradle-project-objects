object WallService {
    private var posts = emptyArray<Post>()
    private var lastId = 0
    private var comments = emptyArray<Comment>()
    private var lastIdComment = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((i, p) in posts.withIndex()) {
            if (post.id == p.id) {
                posts[i] = post.copy()
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        lastId = 0
    }

    fun clearComments() {
        comments = emptyArray()
        lastIdComment = 0
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        val post = posts.find { it.id == postId } ?: throw PostNotFoundException("Post with id $postId not found")
        update(post.copy(comments = if (post.comments == null) Comments(1) else (post.comments.copy(count =+ 1))))
        comments += comment.copy(id = ++lastIdComment, postId = postId)
        return comment
    }
}