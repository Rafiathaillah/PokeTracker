package com.visualprogramming.poketracker.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.visualprogramming.poketracker.ui.theme.PokeGreen
import com.visualprogramming.poketracker.ui.theme.PokeGreenBg
import com.visualprogramming.poketracker.ui.theme.PokeRedPrimary
import com.visualprogramming.poketracker.ui.theme.Slate400

@Composable
fun TopHeaderBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Logo Pokéball
        PokeballIcon(modifier = Modifier.size(36.dp))

        Spacer(modifier = Modifier.width(12.dp))

        // Judul Aplikasi
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "PokéTracker",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Real-time TCG Card Valuation",
                color = Slate400,
                fontSize = 11.sp
            )
        }

        // Pill LIVE API
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(PokeGreenBg)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(PokeGreen)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "LIVE API",
                    color = PokeGreen,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun PokeballIcon(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val radius = size.minDimension / 2f
        val center = Offset(size.width / 2f, size.height / 2f)

        // Bagian atas merah
        drawArc(
            color = PokeRedPrimary,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true,
            size = Size(radius * 2, radius * 2),
            topLeft = Offset(center.x - radius, center.y - radius)
        )

        // Bagian bawah putih
        drawArc(
            color = Color.White,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            size = Size(radius * 2, radius * 2),
            topLeft = Offset(center.x - radius, center.y - radius)
        )

        // Garis tengah hitam
        drawLine(
            color = Color(0xFF1E2638),
            start = Offset(center.x - radius, center.y),
            end = Offset(center.x + radius, center.y),
            strokeWidth = 3f
        )

        // Lingkaran luar hitam
        drawCircle(
            color = Color(0xFF1E2638),
            radius = radius,
            center = center,
            style = Stroke(width = 3f)
        )

        // Lingkaran tengah luar hitam
        drawCircle(
            color = Color(0xFF1E2638),
            radius = radius * 0.32f,
            center = center
        )

        // Tombol tengah putih
        drawCircle(
            color = Color.White,
            radius = radius * 0.22f,
            center = center
        )
    }
}
