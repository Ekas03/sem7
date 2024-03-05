class Photo(val name: String)

class PhotoCollection(val photos: MutableList<Photo> = mutableListOf()) : Iterable<Photo> {
    override fun iterator() : Iterator<Photo> = PhotoIterator(photos)
}

class PhotoIterator(val photos: MutableList<Photo> = mutableListOf(), var current: Int = 0) : Iterator<Photo> {
    override fun hasNext(): Boolean = photos.size > current
    override fun next(): Photo {
        val photo = photos[current]
        current++
        return photo
    }
}

fun main() {
    val photos = PhotoCollection(mutableListOf(Photo("Фото 1"), Photo("Фото 2"), Photo("Фото 3"), Photo("Фото 4")))
    while (true) {
        photos.forEach{ println(it.name) }
    }
}