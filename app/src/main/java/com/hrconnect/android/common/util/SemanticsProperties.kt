package com.hrconnect.android.common.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

val SupportingTextColor = SemanticsPropertyKey<Color>("SupportingTextColor")
var SemanticsPropertyReceiver.supportingTextColor by SupportingTextColor