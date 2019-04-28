package com.waytogalaxy.display.utils


class MultipleKeyMap<T>:HashMap<String,T>(){

    fun multiKeyPut(value:T,vararg keys:Any){
        val resultKey = fromKeys(keys)
        put(resultKey,value)
    }

    fun multiKeyGet(vararg keys: Any) = get(fromKeys(keys))


    fun multiKeyRemove(vararg keys: Any) = remove(fromKeys(keys))


    private fun fromKeys(keys: Array<out Any>): String {
        val sb = StringBuffer()
        for (item in keys){
            if(sb.isNotEmpty()){
                sb.append(":")
            }
            sb.append(item)
        }
        return sb.toString()
}

    fun multiKeysGet(keys: List<String>): List<T?>? {
      return keys.map { get(it) }
    }


}