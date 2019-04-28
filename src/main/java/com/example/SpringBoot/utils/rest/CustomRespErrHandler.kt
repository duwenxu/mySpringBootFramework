package com.waytogalaxy.display.utils.rest

import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.DefaultResponseErrorHandler
import org.springframework.web.client.ResponseErrorHandler
import org.springframework.web.client.RestClientException
import java.io.IOException
import jdk.nashorn.internal.runtime.ScriptingFunctions.readLine
import java.io.InputStreamReader
import java.io.BufferedReader
import java.io.InputStream


class CustomRespErrHandler : ResponseErrorHandler {

    val errorHandler = DefaultResponseErrorHandler()


    override fun hasError(response: ClientHttpResponse) = errorHandler.hasError(response)

    /**
     * 自定义处理异常
     * @param response 返回信息
     */
    override fun handleError(response: ClientHttpResponse) {
        val body = convertStreamToString(response.body)
                try {
                    errorHandler.handleError(response)
                } catch (ex: RestClientException) {
                    throw CustomerException(ex, body, ex.message?:"")
                }
    }


    /**
     * inputStream 装换为 string
     * @param inputStream 输入流
     */
    private fun convertStreamToString(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuilder()

        try {
            var line: String? = reader.readLine()
            while (line != null) {
                sb.append(line)
                line = reader.readLine()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                inputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        return sb.toString()
    }

}