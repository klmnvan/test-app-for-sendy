package com.example.sendyapp.presentation.components.textfileds

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.sendyapp.presentation.ui.theme.SendyAppTheme
import kotlin.math.absoluteValue

@Composable
fun PhoneTextFiled(value: String, placeholder: String, onValueChange: (String) -> Unit) {
    val mask = MaskVisualTransformation("+7 ХХХ ХХХ ХХ ХХ")

    TextField(
        value = value,
        onValueChange = { it ->
            if(it.toCharArray().size <= 10)
            onValueChange(it.filter { it.isDigit() })
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = SendyAppTheme.colors.textTittle,
            unfocusedContainerColor = SendyAppTheme.colors.container.copy(alpha = 0.2f),
            focusedContainerColor = SendyAppTheme.colors.container.copy(alpha = 0.2f),
            focusedTextColor = SendyAppTheme.colors.textTittle,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            disabledContainerColor = SendyAppTheme.colors.container
        ),
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        placeholder = {
            Text(
                placeholder,
                style = SendyAppTheme.typography.textInField
            )
        },
        visualTransformation = mask,
        textStyle = SendyAppTheme.typography.textInField
    )
}

class MaskVisualTransformation(private val mask: String): VisualTransformation {
    private val specialSymbolsIndices = mask.indices.filter { mask[it] != 'Х' }

    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        var maskIndex = 0
        text.forEach { char ->
            while (specialSymbolsIndices.contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }
        return TransformedText(AnnotatedString(out), offsetTranslator())
    }

    private fun offsetTranslator() = object: OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) return 0
            var numberOfHashtags = 0
            val masked = mask.takeWhile {
                if (it == 'Х') numberOfHashtags++
                numberOfHashtags < offsetValue
            }
            return masked.length + 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            return mask.take(offset.absoluteValue).count { it == 'Х' }
        }
    }
}