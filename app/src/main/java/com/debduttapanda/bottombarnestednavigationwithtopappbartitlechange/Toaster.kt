package com.debduttapanda.bottombarnestednavigationwithtopappbartitlechange

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController

data class Toaster(
    private val context: Context
){
    fun toast(message: String, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(context, message,duration).show()
    }

    fun stringResource(@StringRes id: Int): String{
        return context.getString(id)
    }
}

typealias UIScope = suspend (ScopeManager)->Unit

data class ScopeManager(
    val navHostController: NavHostController? = null,
    val owner: LifecycleOwner? = null,
    val toaster: Toaster? = null
)

fun MutableState<UIScope?>.scope(block: UIScope?){
    this.value = {scopeManager ->
        block?.invoke(scopeManager)
        this.value = null
    }
}

suspend fun MutableState<UIScope?>.forward(scopeManager: ScopeManager){
    this.value?.invoke(scopeManager)
}