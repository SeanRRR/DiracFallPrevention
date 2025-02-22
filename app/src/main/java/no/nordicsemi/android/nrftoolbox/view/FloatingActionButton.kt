package no.nordicsemi.android.nrftoolbox.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import no.nordicsemi.android.nrftoolbox.R

@Composable
fun CallFloatingButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd // Align to bottom-right
    ) {
        FloatingActionButton(
            onClick = onClick,
            shape = CircleShape,
            containerColor = colorResource(id = R.color.light_background),
            //containerColor = Color.DarkGray,
            modifier = Modifier
                .padding(0.dp)
                .size(70.dp), // Standard FAB size
            elevation = FloatingActionButtonDefaults.elevation(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.phone_icon), // Replace with your phone icon
                contentDescription = "Call",
                tint = Color(0xFF7852A9),
                modifier = Modifier.size(80.dp)
            )
        }
    }
}