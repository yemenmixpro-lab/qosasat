package com.yemenmixpro.qosasat.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditorScreen(onDone: (String) -> Unit, onCancel: () -> Unit) {
    val textState = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(value = textState.value, onValueChange = { textState.value = it }, modifier = Modifier.fillMaxWidth(), label = { Text("اكتب نص القصاصة") })
        Button(onClick = { onDone(textState.value) }, modifier = Modifier.padding(top = 8.dp)) {
            Text("حفظ")
        }
        Button(onClick = onCancel, modifier = Modifier.padding(top = 8.dp)) {
            Text("إلغاء")
        }
    }
}
