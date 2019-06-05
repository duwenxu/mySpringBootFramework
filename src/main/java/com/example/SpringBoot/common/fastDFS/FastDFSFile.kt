package com.example.SpringBoot.common.fastDFS

/**
 * fastDFS的封装类对象
 */
data class FastDFSFile(
         var name: String,
         var content: ByteArray,
         var ext:String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FastDFSFile

        if (name != other.name) return false
        if (!content.contentEquals(other.content)) return false
        if (ext != other.ext) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + content.contentHashCode()
        result = 31 * result + ext.hashCode()
        return result
    }
}
