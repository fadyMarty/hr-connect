package com.hrconnect.android.presentation.create_vacancy.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hrconnect.uikit.common.theme.HrTheme
import com.hrconnect.uikit.presentation.components.select.Select

@Composable
fun RequiredSelect(
    items: List<String>,
    selectedItem: String?,
    onItemClick: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = "$label ",
                style = HrTheme.typography.fieldLabel
            )
            Text(
                text = "*",
                style = HrTheme.typography.fieldLabel,
                color = HrTheme.colorScheme.error
            )
        }
        Select(
            items = items,
            onItemClick = onItemClick,
            selectedItem = selectedItem
        )
    }
}