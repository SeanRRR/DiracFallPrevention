package no.nordicsemi.android.nrftoolbox.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import no.nordicsemi.android.nrftoolbox.R
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleAppBar() {
    val romelioSans = FontFamily(Font(R.font.romelio_sans)) // Ensure font is in res/font
    val previewFont = if (LocalInspectionMode.current) FontFamily.SansSerif else romelioSans
    SmallTopAppBar(
        navigationIcon = {
            Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp)
            ) {
                Spacer(modifier = Modifier.width(0.dp))
                // Left-aligned Logo
                Image(
                    painter = painterResource(id = R.drawable.logo_icon), // Replace with your actual logo
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .width(50.dp)  // Reduce the width
                        .height(100.dp)
                        .align(Alignment.CenterVertically) // Ensures vertical alignment
                )

                Spacer(modifier = Modifier.width(0.dp)) // Space between logo and text

                // Column for text with tighter spacing
                Column(
                    modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = 15.dp)
                ) {
                    Text(
                        text = "DiracSens",
                        fontSize = 30.sp,
                        fontFamily = previewFont,
                        color = Color.Black,
                        maxLines = 1,
                        style = TextStyle(lineHeight = 12.sp)
                    )

                    Spacer(modifier = Modifier.height(0.dp)) // Reduces gap between text

                    Text(
                        text = " Fall Prevention",
                        fontSize = 12.sp,
                        fontFamily = previewFont,
                        color = Color.Black,
                        maxLines = 1,
                        style = TextStyle(lineHeight = 12.sp)
                    )
                }
            }
            }
        },
        title = {

        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(id = R.color.light_background),
            titleContentColor = Color.Black
        ),
        modifier = Modifier.height(72.dp) // Adjusted height to fit content better
    )
}
