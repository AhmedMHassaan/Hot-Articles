package com.ahmedmhassaan.data

import java.io.InputStream
import java.io.InputStreamReader


//class MockResponseFileReader(path: String) {
class MockResponseFileReader(inpuStream: InputStream) {
    val content: String

    init {
//        "android.resource://com.cpt.sample/raw/filename"
//        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(path))
        val reader = InputStreamReader(inpuStream)

        content = reader.readText()
        reader.close()
    }
}