package com.bladyzamosc.pluginek

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

/**
 * User: Z6EKI
 * Date: 21.11.2022
 */
class SimpleBladyAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        var editor = e.getData(CommonDataKeys.EDITOR)
        val text = editor?.selectionModel?.selectedText?.trim()
        if (!text.isNullOrEmpty()) {
            handleText(text);
        }
    }

    private fun handleText(text: String?) {
        if (tryLongToDate(text)) {
            return
        }
        if (tryDateStringToLong(text)) {
            return
        }
    }

    private fun tryDateStringToLong(dateString: String?): Boolean {
        return try {
            println(LocalDateTime.parse(dateString).toInstant(ZoneOffset.UTC).toEpochMilli())
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun tryLongToDate(text: String?): Boolean {
        return try {
            val timestamp = text?.toLong()
            val localDateTime =
                LocalDateTime.ofInstant(timestamp?.let { Instant.ofEpochMilli(it) }, ZoneId.systemDefault())
            println(localDateTime)
            true
        } catch (ne: NumberFormatException) {
            false
        }

    }
}