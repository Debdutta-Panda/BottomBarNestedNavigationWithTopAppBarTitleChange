package com.debduttapanda.bottombarnestednavigationwithtopappbartitlechange

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class HomeViewModel: ViewModel() {
    val uiScope = mutableStateOf<UIScope?>(null)
}