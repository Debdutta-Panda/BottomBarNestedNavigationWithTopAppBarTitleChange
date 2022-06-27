package com.debduttapanda.bottombarnestednavigationwithtopappbartitlechange

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class ProfileViewModel: ViewModel() {
    val uiScope = mutableStateOf<UIScope?>(null)
    fun gotoEdit() {
        uiScope.scope {
            it.navHostController?.navigate("edit")
        }
    }

    fun onValueChange(it: String) {
        name.value = it
    }

    fun onDone() {
        uiScope.scope {
            it.navHostController?.navigateUp()
        }
    }

    val name = mutableStateOf("Debdutta")
}