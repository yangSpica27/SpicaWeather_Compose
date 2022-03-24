package com.spica.weatherc.ui.cities

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spica.weatherc.R
import com.spica.weatherc.model.city.CityBean
import com.spica.weatherc.ui.theme.LightBackgroundColor
import com.spica.weatherc.ui.theme.LightTextColor
import com.spica.weatherc.viewmodel.CityViewModel

/**
 * 城市列表
 */
@Composable
fun CitiesScreen(navController: NavController) {

    val model: CityViewModel = hiltViewModel()

    Surface {
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            SearchBar(onSearch = {})
            LazyColumn(
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                items(model.getAllCities()) { item: CityBean ->
                    CityItem(cityBean = item)
                }
            }
        }
    }

}

/**
 * 城市单元
 */
@Composable
private fun CityItem(cityBean: CityBean) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = cityBean.cityName,
                fontStyle = MaterialTheme.typography.h6.fontStyle,
                fontSize = MaterialTheme.typography.h6.fontSize,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                fontWeight = FontWeight.W500
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "东经：${cityBean.lon} 北纬：${cityBean.lat}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Divider(Modifier.height(1.dp))
        }
    }
}


@Composable
private fun SearchBar(onSearch: (keyword: String) -> Unit) {
    val value = remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .background(LightBackgroundColor)
            .height(100.dp)
            .padding(horizontal = 14.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .height(60.dp)
                .background(Color.White)
                .weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            CustomTextField(
                showCleanIcon = true,
                hint = "城市名称"
            )
        }
    }
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    hint: String? = null,
    showCleanIcon: Boolean = false,
    onTextChange: String.() -> Unit = {},
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: String.() -> Unit = {},
    textFieldStyle: TextStyle = MaterialTheme.typography.body2,
    hintTextStyle: TextStyle = MaterialTheme.typography.body1,
) {
    val text = remember { mutableStateOf("") }
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingIcon?.invoke()
        BasicTextField(
            value = text.value,
            onValueChange = {
                text.value = it
                onTextChange.invoke(it)
            },
            cursorBrush = SolidColor(colorResource(id = R.color.textColorPrimary)),
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .padding(10.dp),
            textStyle = textFieldStyle,
            decorationBox = { innerTextField ->
                if (text.value.isBlank() && !hint.isNullOrEmpty())
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(),
                        contentAlignment = Alignment.CenterStart
                    ) {

                        Text(
                            text = hint,
                            color = colorResource(id = R.color.textColorPrimaryHintLight),
                            style = hintTextStyle
                        )
                        innerTextField()
                    } else innerTextField()

            },
            keyboardActions = KeyboardActions {
                keyboardActions(text.value)
            },
            keyboardOptions = keyboardOptions
        )
        trailingIcon?.invoke()
        if (showCleanIcon)
            IconButton(
                onClick = {
                    text.value = ""
                },
                content = {
                    Icon(
                        imageVector = Icons.Filled.Clear, contentDescription = ""
                    )
                }
            )
    }
}


