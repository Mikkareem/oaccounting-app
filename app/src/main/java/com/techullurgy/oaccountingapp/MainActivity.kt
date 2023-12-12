package com.techullurgy.oaccountingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techullurgy.oaccountingapp.presentation.OAccountingViewModel
import com.techullurgy.oaccountingapp.presentation.components.AccountingInfoItem
import com.techullurgy.oaccountingapp.presentation.components.AccountingInfoSummaryCard
import com.techullurgy.oaccountingapp.presentation.components.AppText
import com.techullurgy.oaccountingapp.presentation.screens.MainScreen
import com.techullurgy.oaccountingapp.ui.theme.OAccountingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OAccountingAppTheme {
                @Suppress("UNCHECKED_CAST")
                val viewModel: OAccountingViewModel by viewModels(
                    factoryProducer = {
                        object: ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return OAccountingViewModel(this@MainActivity) as T
                            }
                        }
                    }
                )

                val textStyle = LocalTextStyle.current // .copy(letterSpacing = ..., fontFamily = Poppins)

                ProvideTextStyle(value = textStyle) {
                    MainScreen(viewModel = viewModel)
                }
            }
        }
    }
}