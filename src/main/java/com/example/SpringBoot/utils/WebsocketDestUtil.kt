package com.waytogalaxy.display.utils

import com.waytogalaxy.display.common.cons.Cons

object WebsocketDestUtil {

    fun msgDest(websocketName: String) = if (websocketName.endsWith(Cons.TERMINAL_PC.toString())) {
        Cons.WEBSOCKET_NOTIFICATIONS
    } else {
        Cons.APP_WEBSOCKET_NOTIFICATIONS
    }

}