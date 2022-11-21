package com.bladyzamosc.pluginek

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * User: Z6EKI
 * Date: 21.11.2022
 */
class SimpleBladyAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        var editor = e.getData(CommonDataKeys.EDITOR)
        val text = editor?.selectionModel?.selectedText
        if (!text.isNullOrEmpty()) {
            handleText(text);
        }
    }

    private fun handleText(text: String?) {
        tryLongToDate(text)
    }

    private fun tryLongToDate(text: String?) {
        try {
            val timestamp = text?.toLong()
            val localDateTime =
                LocalDateTime.ofInstant(timestamp?.let { Instant.ofEpochMilli(it) }, ZoneId.systemDefault())
            println(localDateTime)
        } catch (ne: NumberFormatException) {
            // do nothing
        }
    }
}