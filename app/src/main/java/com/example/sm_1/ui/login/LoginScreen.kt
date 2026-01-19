package com.example.sm_1.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sm_1.ui.theme.*
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onRecoverClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var messageText by remember { mutableStateOf<String?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.5f))

            Icon(
                imageVector = Icons.Filled.VerifiedUser,
                contentDescription = "Login Icon",
                modifier = Modifier.size(100.dp),
                tint = Primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Bienvenido/a",
                style = MaterialTheme.typography.headlineLarge,
                color = Primary,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Inicia sesión para continuar",
                style = MaterialTheme.typography.bodyLarge,
                color = Primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { if (!it.contains(' ')) email = it },
                label = { Text("Email") },
                singleLine = true,
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Primary,
                    unfocusedBorderColor = Primary.copy(alpha = 0.3f),
                    focusedLeadingIconColor = Primary,
                    unfocusedLeadingIconColor = Primary.copy(alpha = 0.3f),
                    focusedTextColor = Primary,
                    unfocusedTextColor = Primary,
                    cursorColor = Secondary
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { if (!it.contains(' ')) password = it },
                label = { Text("Contraseña") },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = null)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Primary,
                    unfocusedBorderColor = Primary.copy(alpha = 0.3f),
                    focusedLeadingIconColor = Primary,
                    unfocusedLeadingIconColor = Primary.copy(alpha = 0.3f),
                    focusedTrailingIconColor = Primary,
                    unfocusedTrailingIconColor = Primary.copy(alpha = 0.3f),
                    focusedTextColor = Primary,
                    unfocusedTextColor = Primary,
                    cursorColor = Secondary
                )
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = Secondary,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .clickable { onRecoverClick() }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    messageText = when {
                        email.isBlank() || password.isBlank() -> "Completa tu email y contraseña"
                        else -> "¡Bienvenido!"
                    }
                    onLoginClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = Surface
                )
            ) {
                Text("Iniciar sesión", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }

            if (messageText != null) {
                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Primary.copy(alpha = 0.08f)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = Primary
                        )

                        Text(
                            text = messageText!!,
                            modifier = Modifier.weight(1f),
                            color = Primary,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        IconButton(onClick = { messageText = null }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Cerrar",
                                tint = Primary.copy(alpha = 0.6f)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                 Text(
                    text = "¿No tienes cuenta? ",
                    color = Primary,
                     style = MaterialTheme.typography.bodyLarge
                )
                 Text(
                    text = "Regístrate",
                    color = Secondary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.clickable { onRegisterClick() }
                )
            }
        }
    }
}