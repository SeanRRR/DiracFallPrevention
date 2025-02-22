package no.nordicsemi.android.nrftoolbox.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import no.nordicsemi.android.nrftoolbox.*
import no.nordicsemi.android.nrftoolbox.viewmodel.HomeViewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import no.nordicsemi.android.nrftoolbox.R
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import no.nordicsemi.android.nrftoolbox.view.CallFloatingButton

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen() {
    val isPreview = LocalInspectionMode.current
    val viewModel: HomeViewModel? = if (isPreview) null else hiltViewModel()
    val state by viewModel?.state?.collectAsStateWithLifecycle() ?: mutableStateOf(HomeViewState())
    val context = LocalContext.current
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val lightBackground = colorResource(id = R.color.light_background)
    // Set the status bar color to match your app's primary top bar color
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
//            color = Color(0xFF204085), // Dark blue color (same as top bar)
            color = lightBackground,
            darkIcons = true // Set to true if using a light theme
        )
        systemUiController.setNavigationBarColor(
            color = lightBackground, // Change this to the color you want
            darkIcons = true // Set true for dark icons or false for light icons
        )
    }

    Scaffold(
        topBar = {
            Column {
                TitleAppBar(stringResource(id = R.string.app_name))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp) // Thin divider
                        .background(Color.LightGray) // Subtle separator color
                )
            }
        },
        bottomBar = {
            Column {
                // Small rectangle as a top border
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp) // Thin top border
                        .background(Color.LightGray) // Light gray color
                )

                // Actual Navigation Bar
                NavigationBar(
                    containerColor = lightBackground,
                    tonalElevation = 0.dp, // Removes shadow effect
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route ?: "home"

                    val navigationItems = listOf(
                        Triple("home", R.drawable.home_icon, "Home"),
                        Triple("education", R.drawable.education_icon, "Education"),
                        Triple("help", R.drawable.help_icon, "Help"),
                        Triple("settings", R.drawable.settings_icon, "Settings")
                    )

                    // Define a map to set different sizes for each icon
                    val iconSizes = mapOf(
                        "home" to 37.dp,
                        "education" to 47.dp,
                        "help" to 45.dp,
                        "settings" to 44.dp
                    )
                    val iconTextSpacing = mapOf(
                        "home" to 4.dp,
                        "education" to 0.dp,
                        "help" to 2.dp,
                        "settings" to 3.dp
                    )

                    navigationItems.forEach { (route, icon, label) ->
                        NavigationBarItem(
                            icon = {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(iconTextSpacing[route] ?: 4.dp) // Adjust spacing for better alignment
                                ) {
                                    // Small bar above the icon when selected
                                    if (currentRoute == route) {
                                        Box(
                                            modifier = Modifier
                                                .width(30.dp) // Width of the highlight
                                                .height(4.dp) // Thickness of the highlight bar
                                                .background(Color.Black) // Color of the highlight bar
                                                .clip(RoundedCornerShape(50)) // Makes the bar slightly rounded
                                        )
                                    }
                                    Icon(
                                        painter = painterResource(id = icon), // Use appropriate drawable
                                        contentDescription = label,
                                        modifier = Modifier.size(iconSizes[route] ?: 30.dp), // Uses specific size or default 30.dp
                                        tint = if (currentRoute == route) Color.Black else Color.DarkGray // Changes icon color when selected
                                    )
                                    Text(
                                        label,
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            fontWeight = if (currentRoute == route) FontWeight.Bold else FontWeight.Normal
                                        ),
                                        color = if (currentRoute == route) Color.Black else Color.DarkGray // Changes text color when selected
                                    )
                                }
                            },
                            selected = currentRoute == route,
                            onClick = { navController.navigate(route) },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent // Removes default selection background
                            )
                        )
                    }
                }
            }
        },
                floatingActionButton = {
            CallFloatingButton(onClick = { /* Handle call action */ })
        },
        floatingActionButtonPosition = FabPosition.End // Bottom-right placement
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Set drawable as background
            Image(
                painter = painterResource(id = R.drawable.home_background_2), // Replace with your actual drawable
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                // .background(Color(0xFFF0F4F8))
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(180.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    contentPadding = PaddingValues(5.dp),
                ) {
                    items(
                        listOf(
                            Triple(
                                R.drawable.body_balance_icon,
                                R.string.bb_module,
                                GLSDestinationId
                            ),
                            Triple(
                                R.drawable.blood_pressure_icon,
                                R.string.bps_module,
                                BPSDestinationId
                            ),
                            Triple(R.drawable.gait_icon, R.string.gait_module, CSCDestinationId),
                            Triple(
                                R.drawable.heart_rate_icon,
                                R.string.hrs_module,
                                HRSDestinationId
                            ),
                            Triple(
                                R.drawable.breathing_rate_icon,
                                R.string.br_module,
                                HTSDestinationId
                            ),
                            Triple(
                                R.drawable.survey_icon,
                                R.string.survey_module,
                                RSCSDestinationId
                            ),
                            Triple(
                                R.drawable.medication_icon,
                                R.string.med_module,
                                CGMSDestinationId
                            ),
                            Triple(
                                R.drawable.baseline_icon,
                                R.string.baseline_module,
                                PRXDestinationId
                            ),
                        )
                    ) { (icon, title, destinationId) ->
                        Card(
                            modifier = Modifier
                                .padding(6.dp)
                                .fillMaxWidth()
                                .height(180.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = lightBackground),
                            elevation = CardDefaults.cardElevation(6.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(70.dp),
                                    contentScale = ContentScale.Fit
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = stringResource(id = title),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }

                Text(
                    text = BuildConfig.VERSION_NAME,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
