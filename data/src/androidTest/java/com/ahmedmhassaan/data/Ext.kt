package com.ahmedmhassaan.data

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.io.InputStream
import java.nio.charset.StandardCharsets

internal fun MockWebServer.enqueueResponse(inputStream: InputStream, code: Int) {

    val source = inputStream?.let { inputStream.source().buffer() }
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(code)
                .setBody(source.readString(StandardCharsets.UTF_8))
        )
    }
}