package com.illia.finalproject.ui.activity

import android.content.Context

abstract class ApplicationContextHolder {
    companion object {

        private lateinit var applicationContext: Context

        fun setContext(context: Context) {
            applicationContext=context
        }
        fun getContext() : Context{
            println("CONTEXT REQUESTED")
            return applicationContext
        }
    }
}