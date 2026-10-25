package com.example.catmemeshop.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaInicio(
    onEntrarTienda: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "CatMemeShop",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(Modifier.height(20.dp))

        Text(
            text = "Bienvenido a nuestra tienda de gatos",
            fontSize = 18.sp,
            color = Color.LightGray,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(14.dp))

        Text(
            text = "Explora nuestro catálogo de gatos y accesorios.",
            fontSize = 15.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(35.dp))

        Button(
            onClick = onEntrarTienda,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .border(
                    1.dp,
                    Color.White,
                    RoundedCornerShape(12.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF252525)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "ENTRAR A LA TIENDA",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
