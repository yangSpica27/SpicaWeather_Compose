package com.spica.weatherc.ui.widget.card

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.spica.weatherc.model.weather.LifeIndexBean
import com.spica.weatherc.model.weather.Weather

/**
 * 指数信息卡片
 */

@Composable
fun TipsCard(weather: Weather) {

    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
        ) {

            weather.lifeIndexes.mapIndexed { index, item ->
                if (index != weather.lifeIndexes.size - 1) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        TipItem(lifeIndexBean = item)
                        Divider(modifier = Modifier.height(1.dp))
                    }
                } else {
                    TipItem(lifeIndexBean = item)
                }

            }

        }
    }

}


@Composable
private fun TipItem(lifeIndexBean: LifeIndexBean) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = 12.dp, vertical = 8.dp)
            ,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = lifeIndexBean.iconRes),
            contentDescription = null,
            modifier = Modifier.height(24.dp)
        )

        Spacer(modifier = Modifier.width(20.dp))

        Column(
            Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = lifeIndexBean.name, style = MaterialTheme.typography.subtitle2)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = lifeIndexBean.category, style = MaterialTheme.typography.body2)
        }

    }
}



