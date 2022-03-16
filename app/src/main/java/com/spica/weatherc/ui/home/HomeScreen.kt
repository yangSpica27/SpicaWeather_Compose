package com.spica.weatherc.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.spica.weatherc.R
import com.spica.weatherc.ui.widget.card.NowCard


@Composable
fun HomeScreen(
    navController: NavController,
) {
    val scrollState = rememberScrollState()
    var scrolledY = 0f
    var previousOffset = 0
    Scaffold(
        topBar = {
            TopBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                backgroundColor = Color.Black
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        },
        isFloatingActionButtonDocked = true,
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            NowCard(weather = null)
            Spacer(modifier = Modifier.height(20.dp))
            NowCard(weather = null)
            Spacer(modifier = Modifier.height(20.dp))
            NowCard(weather = null)
            Spacer(modifier = Modifier.height(20.dp))
            NowCard(weather = null)
            Spacer(modifier = Modifier.height(20.dp))
            NowCard(weather = null)
        }

    }
}


@Composable
fun TopBar() {
    Row(Modifier.padding(horizontal = 12.dp, vertical = 12.dp)) {
        Icon(painter = painterResource(id = R.drawable.ic_menu), contentDescription = null)
        Text(
            text = "中国，南京",
            modifier = Modifier.weight(1f),
            fontStyle = MaterialTheme.typography.h2.fontStyle,
            textAlign = TextAlign.Center
        )
        Icon(painter = painterResource(id = R.drawable.ic_tip), contentDescription = null)
    }
}