package no.nordicsemi.android.nrftoolbox.view


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import no.nordicsemi.android.common.analytics.view.AnalyticsPermissionButton
import no.nordicsemi.android.nrftoolbox.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleAppBar(text: String) {
    SmallTopAppBar(
        title = { Text(text, maxLines = 2, color = Color.Black) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
//            containerColor = Color(0xFF204085),
            containerColor = colorResource(id = R.color.light_background),
            titleContentColor = Color.Black,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )
}
