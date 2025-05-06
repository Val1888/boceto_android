package com.example.clon_fulanito.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPerfil(modifier: Modifier = Modifier) {
    // Paleta de colores rosas
    val pinkPrimary = Color(0xFFF06292)  // Rosa medio
    val pinkLight = Color(0xFFF8BBD0)    // Rosa claro
    val pinkDark = Color(0xFFEC407A)     // Rosa oscuro
    val pinkSurface = Color(0xFFFFEBEE)   // Rosa muy claro para fondos

    // Gradiente rosado para el fondo
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(pinkLight, Color.White)
    )

    Surface(
        modifier = modifier.fillMaxSize(),
        color = pinkSurface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBackground)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            ProfileHeader(pinkPrimary)
            PersonalInfoCard(pinkPrimary, pinkSurface)
            ContactInfoCard(pinkPrimary, pinkSurface)
        }
    }
}

@Composable
private fun ProfileHeader(primaryColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.White)  // Fondo blanco para el círculo
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Foto de perfil",
                modifier = Modifier.fillMaxSize(),
                tint = primaryColor  // Color rosita para el ícono
            )
        }

        Text(
            text = "Usuario",
            style = MaterialTheme.typography.headlineMedium.copy(
                color = primaryColor,
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = "Miembro desde 2023",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = primaryColor.copy(alpha = 0.7f)
            )
        )
    }
}

@Composable
private fun PersonalInfoCard(primaryColor: Color, surfaceColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White  // Fondo blanco para mejor contraste
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Información Personal",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = primaryColor,
                    fontWeight = FontWeight.Bold
                )
            )

            Divider(
                color = primaryColor.copy(alpha = 0.2f),
                thickness = 1.dp
            )

            ProfileInfoItem(
                label = "Nombre completo",
                value = "Valeria Pérez",
                primaryColor = primaryColor
            )

            ProfileInfoItem(
                label = "Edad",
                value = "25 años",
                primaryColor = primaryColor
            )

            ProfileInfoItem(
                label = "Ocupación",
                value = "Desarrolladora Android",
                primaryColor = primaryColor
            )
        }
    }
}

@Composable
private fun ContactInfoCard(primaryColor: Color, surfaceColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White  // Fondo blanco para mejor contraste
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Información de Contacto",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = primaryColor,
                    fontWeight = FontWeight.Bold
                )
            )

            Divider(
                color = primaryColor.copy(alpha = 0.2f),
                thickness = 1.dp
            )

            ContactInfoRow(
                icon = Icons.Filled.Email,
                text = "perfil123@gmail.com",
                iconColor = primaryColor
            )

            ContactInfoRow(
                icon = Icons.Filled.Phone,
                text = "+52 656 123 4567",
                iconColor = primaryColor
            )

            ContactInfoRow(
                icon = Icons.Filled.LocationOn,
                text = "Ciudad Juárez, Chihuahua",
                iconColor = primaryColor
            )
        }
    }
}

@Composable
private fun ProfileInfoItem(label: String, value: String, primaryColor: Color) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(
                color = primaryColor.copy(alpha = 0.6f)
            )
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.DarkGray  // Texto oscuro para mejor legibilidad
            )
        )
    }
}

@Composable
private fun ContactInfoRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    iconColor: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = iconColor  // Iconos rositas
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.DarkGray  // Texto oscuro para mejor legibilidad
            )
        )
    }
}