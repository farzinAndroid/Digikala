package com.farzin.newdigikala.ui.screens.product_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.farzin.newdigikala.R
import com.farzin.newdigikala.data.model.product_detail.Price
import com.farzin.newdigikala.ui.theme.darkText
import com.farzin.newdigikala.ui.theme.digikalaRed
import com.farzin.newdigikala.ui.theme.spacing
import com.farzin.newdigikala.ui.theme.veryExtraSmall
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.line.lineSpec
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollSpec
import com.patrykandpatrick.vico.compose.component.lineComponent
import com.patrykandpatrick.vico.compose.component.shape.shader.fromComponent
import com.patrykandpatrick.vico.compose.component.shape.shader.verticalGradient
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.core.axis.horizontal.createHorizontalAxis
import com.patrykandpatrick.vico.core.axis.vertical.VerticalAxis
import com.patrykandpatrick.vico.core.axis.vertical.createVerticalAxis
import com.patrykandpatrick.vico.core.chart.composed.plus
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.component.shape.DashedShape
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.composed.plus
import com.patrykandpatrick.vico.core.entry.entriesOf
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.scroll.AutoScrollCondition
import com.patrykandpatrick.vico.core.scroll.InitialScroll

@Composable
fun ProductChartPriceScreen(
    jsonString: String,
    navController: NavController,
) {

    var priceList by remember { mutableStateOf<List<Price>>(emptyList()) }

    val priceListType = object : TypeToken<List<Price>>() {}.type
    priceList = Gson().fromJson(jsonString, priceListType)

    val chartModel = getPriceList(priceList)

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.small),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                )
            }

            Text(
                text = stringResource(R.string.price_chart),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText,
                modifier = Modifier.fillMaxWidth()
            )

        }

        Spacer(modifier = Modifier.height(3.dp))

        PriceLineChart(model = chartModel,priceList=priceList )
    }


}


 fun getPriceList(priceList: List<Price>): ChartEntryModel {
    val entryPrices = priceList.take(6).map { it.price }
    return entryModelOf(*entryPrices.toTypedArray().reversedArray())
}

fun createPersianMonthArray(priceList: List<Price>):Array<String>{
    return priceList.take(6).mapNotNull {
        val numberMonth = it.persianDate.split("/").getOrNull(1)?.toIntOrNull()
        numberMonth?.let {index->
            mapOf(
                1 to "فروردین",
                2 to "اردیبهست",
                3 to "خرداد",
                4 to "تیر",
                5 to "مرداد",
                6 to "شهریور",
                7 to "مهر",
                8 to "آبان",
                9 to "آذر",
                10 to "دی",
                11 to "بهمن",
                12 to "اسفند",
            )[index] ?: "invalid month"
        }
    }.toTypedArray().reversedArray()
}

@Composable
fun PriceLineChart(
    model: ChartEntryModel,
    priceList: List<Price>,
    scrollable: Boolean = true,
    initialScroll: InitialScroll = InitialScroll.Start,
) {
    ChartCard(
        title = stringResource(R.string.product_price_chart),
        subTitle = stringResource(R.string.product_price_chart_time)
    ) {
        Chart(
            chart = lineChart(
                lines = listOf(
                    lineSpec(
                        lineColor = MaterialTheme.colors.digikalaRed,
                        lineBackgroundShader = verticalGradient(
                            arrayOf(
                                MaterialTheme.colors.digikalaRed.copy(0.5f),
                                MaterialTheme.colors.digikalaRed.copy(0f)
                            )
                        )
                    )
                )
            ),
            model = model,
            startAxis = rememberStartAxis(),
            bottomAxis = createHorizontalAxis {
                guideline = LineComponent(
                    color = Color.Red.toArgb(),
                    thicknessDp = 1.dp.value,
                    shape = DashedShape(
                        shape = Shapes.rectShape,
                        dashLengthDp = 2.dp.value,
                        gapLengthDp = 4.dp.value
                    )
                )
            },
            chartScrollSpec = rememberChartScrollSpec(
                isScrollEnabled = scrollable,
                initialScroll = initialScroll
            )
        )

        Row {
            Spacer(modifier = Modifier.weight(142f))
            createPersianMonthArray(priceList).forEach {
                Text(
                    text = it,
                    color = MaterialTheme.colors.darkText,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.veryExtraSmall,
                    modifier = Modifier.weight(142f)
                )
            }


        }

    }
}


@Composable
fun ColumnChartWithNegativeValues() {
    ChartCard(
        title = "نمودار ستونی منفی",
        subTitle = "مثالی برای استفاده از نمودار ستونی با مقادیر منفی"
    ) {
        Chart(
            chart = columnChart(
                dataLabel = textComponent(),
            ),
            model = entryModelOf(2f, -1f, 4f, -2f, 1f, 5f, -3f),
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(),
        )
    }
}



@Composable
fun DefaultLineChart(
    model: ChartEntryModel = entryModelOf(2f, 1f, 4f, 8f, 1f, 5f, 7f),
    scrollable: Boolean = true,
    initialScroll: InitialScroll = InitialScroll.Start,
) {
    ChartCard(
        title = "نمودار خطی",
        subTitle = "مثالی برای استفاده از نمودار خطی"
    ) {
        Chart(
            chart = lineChart(),
            model = model,
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(),
            chartScrollSpec = rememberChartScrollSpec(
                isScrollEnabled = scrollable,
                initialScroll = initialScroll
            )
        )
    }
}


@Composable
fun DefaultColumnChart(
    model: ChartEntryModel = entryModelOf(2f, 1f, 4f, 8f, 1f, 5f, 7f),
    oldModel: ChartEntryModel? = null,
    scrollable: Boolean = true,
    initialScroll: InitialScroll = InitialScroll.Start,
    autoScrollCondition: AutoScrollCondition<ChartEntryModel> = AutoScrollCondition.Never,
) {
    ChartCard(
        title = "نمودار ستونی",
        subTitle = "مثالی برای استفاده از نمودار ستونی"
    ) {
        Chart(
            chart = columnChart(),
            model = model,
            oldModel = oldModel,
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(),
            chartScrollSpec = rememberChartScrollSpec(
                isScrollEnabled = scrollable,
                initialScroll = initialScroll,
                autoScrollCondition = autoScrollCondition,
            ),
        )
    }


}

@Composable
fun LineChartCard() {
    val colors = MaterialTheme.colors

    ChartCard(
        title = "نمودار خطی زیبا",
        subTitle = "مثالی برای استفاده از نمودار خطی خاص"
    ) {
        Chart(
            modifier = Modifier.height(100.dp),
            chart = lineChart(
                lines = listOf(
                    lineSpec(
                        point = null,
                        lineColor = colors.primary,
                        lineBackgroundShader = DynamicShaders.fromComponent(
                            componentSize = 4.dp,
                            component = shapeComponent(
                                shape = Shapes.pillShape,
                                color = colors.primary
                            ).apply {
                                setMargins(0.5.dp.value)
                            },
                        ),
                    ),
                ),
                axisValuesOverrider = AxisValuesOverrider.fixed(
                    minX = 0f,
                    maxY = 3f,
                ),
            ),
            model = entryModelOf(-1 to 0, 0 to 0, 1 to 1, 2 to 2, 3 to 0, 4 to 2, 5 to 1),
            startAxis = createVerticalAxis {
                label = textComponent(
                    color = colors.onSurface,
                    textSize = 10.sp,
                    background = shapeComponent(shape = Shapes.rectShape, color = Color.LightGray),
                    padding = dimensionsOf(horizontal = 4.dp, vertical = 2.dp),
                )
                axis = null
                tick = null
                guideline = LineComponent(
                    color = Color.LightGray.toArgb(),
                    thicknessDp = 1.dp.value,
                    shape = DashedShape(
                        shape = Shapes.pillShape,
                        dashLengthDp = 2.dp.value,
                        gapLengthDp = 4.dp.value,
                    ),
                )
                horizontalLabelPosition = VerticalAxis.HorizontalLabelPosition.Inside
            },
            bottomAxis = createHorizontalAxis {
                label = null
                tick = null
                guideline = null
                axis = lineComponent(color = Color.LightGray, thickness = 1.dp)
            },
        )
    }
}

@Composable
private fun ChartCard(
    title: String,
    subTitle: String,
    chart: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            chart()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.h3,
            )
            Text(
                text = subTitle,
                style = MaterialTheme.typography.h5,
            )
        }
    }
}


private val model1 = entryModelOf(0, 2, 4, 0, 2)
private val model2 = entryModelOf(1, 3, 4, 1, 3)
private val model3 = entryModelOf(entriesOf(3, 2, 2, 3, 1), entriesOf(1, 3, 1, 2, 3))

@Composable
fun LineChartDark() {
    ChartCard(
        title = "نمودار خطی رنگی دوتایی",
        subTitle = "مثالی برای استفاده از نمودار خطی رنگی دوتایی بدون بک گراند"
    ) {
        val yellow = Color(0xFFFFAA4A)
        val pink = Color(0xFFFF4AAA)

        Chart(
            modifier = Modifier.padding(8.dp),
            chart = lineChart(
                lines = listOf(
                    lineSpec(
                        lineColor = yellow,
                        lineBackgroundShader = verticalGradient(
                            arrayOf(yellow.copy(0.5f), yellow.copy(alpha = 0f)),
                        ),
                    ),
                    lineSpec(
                        lineColor = pink,
                        lineBackgroundShader = verticalGradient(
                            arrayOf(pink.copy(0.5f), pink.copy(alpha = 0f)),
                        ),
                    ),
                ),
                axisValuesOverrider = AxisValuesOverrider.fixed(maxY = 4f),
            ),
            model = model3,
        )
    }
}

@Composable
fun ComposedLineChart() {
    ChartCard(
        title = "نمودار خطی رنگی دوتایی",
        subTitle = "مثالی برای استفاده از خطی رنگی دوتایی"
    ) {
        Chart(
            chart = lineChart() + lineChart(
                lines = listOf(
                    lineSpec(
                        lineColor = Color.Blue,
                        lineBackgroundShader = verticalGradient(
                            colors = arrayOf(
                                Color.Blue.copy(alpha = 0.4f),
                                Color.Blue.copy(alpha = 0f),
                            ),
                        ),
                    ),
                ),
            ),
            model = model1 + model2,
            startAxis = rememberStartAxis(),
        )
    }
}

