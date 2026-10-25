package com.example.catmemeshop.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun PantallaMapa() {

    // Ubicación de ejemplo: centro de Querétaro
    val ubicacionTienda = LatLng(
        20.5931,
        -100.3920
    )

    val camara = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            ubicacionTienda,
            14f
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {
        Text(
            text = "Ubicación de CatMemeShop",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = "Encuentra nuestra tienda de gatos exclusivos",
            fontSize = 14.sp,
            color = Color.LightGray,
            modifier = Modifier.padding(
                start = 16.dp,
                bottom = 16.dp
            )
        )

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            cameraPositionState = camara
        ) {
            Marker(
                state = MarkerState(
                    position = ubicacionTienda
                ),
                title = "CatMemeShop",
                snippet = "Tienda de gatos exclusivos"
            )
        }
    }
}
