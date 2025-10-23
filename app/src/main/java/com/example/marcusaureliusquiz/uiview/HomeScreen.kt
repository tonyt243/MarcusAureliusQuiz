package com.example.marcusaureliusquiz.uiview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.marcusaureliusquiz.R
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Marcus Aurelius Quiz") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF8B0000))
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF4E1))
                .padding(padding)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Test your knowledge of Marcus Aurelius!",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.marcus_aurelius),
                    contentDescription = "Marcus Aurelius",
                    modifier = Modifier
                        .size(400.dp)
                        .padding(bottom = 15.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { navController.navigate("quiz") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD2691E)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Start Quiz", color = Color.White)
                }
            }
        }
    }
}
