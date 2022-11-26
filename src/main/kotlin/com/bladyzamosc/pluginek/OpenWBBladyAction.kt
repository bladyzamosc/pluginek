package com.bladyzamosc.pluginek

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys

/**
 * User: Z6EKI
 * Date: 26.11.2022
 */
class OpenWBBladyAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val text = getSelectedText(e)
        if (!text.isNullOrBlank()) {
            openBrowserWith(text)
        }
    }

    private fun openBrowserWith(text: String?) {
        Runtime.getRuntime().exec(arrayOf("cmd", "/c", "start chrome https://www.google.com/search?q=" + text))
    }

    private fun getSelectedText(e: AnActionEvent): String? {
        var editor = e.getData(CommonDataKeys.EDITOR)
        val text = editor?.selectionModel?.selectedText?.trim()
        return text
    }
}