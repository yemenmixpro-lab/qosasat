package com.yemenmixpro.qosasat.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.Button
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yemenmixpro.qosasat.viewmodel.StoryViewModel

@Composable
fun QosasatApp() {
    // Force RTL layout direction for Arabic
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        MaterialTheme {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: StoryViewModel = viewModel()) {
    val stories by viewModel.stories.collectAsState(initial = emptyList())
    var editing by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "قصصات") }) },
        content = {
            Column(modifier = Modifier.padding(8.dp)) {
                Button(onClick = { editing = true }) {
                    Text("إنشاء قصاصة جديدة")
                }

                if (editing) {
                    EditorScreen(onDone = { text ->
                        viewModel.insert(text)
                        editing = false
                    }, onCancel = { editing = false })
                }

                LazyColumn {
                    items(stories) { s ->
                        Column(modifier = Modifier
                            .clickable { /* TODO: open detail */ }
                            .padding(8.dp)) {
                            Text(text = s.text)
                        }
                    }
                }
            }
        }
    )
}
