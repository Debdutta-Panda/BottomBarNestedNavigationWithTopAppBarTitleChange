package com.debduttapanda.bottombarnestednavigationwithtopappbartitlechange

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class SplashViewModel: ViewModel() {
    val uiScope = mutableStateOf<UIScope?>(null)
    init {
        uiScope.scope {
            delay(2000)
            it.navHostController?.navigate("home")
        }
    }
}