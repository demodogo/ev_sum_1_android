package com.example.sm_1.ui.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.sm_1.ui.theme.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onBackToLoginClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var acceptedTerms by remember { mutableStateOf(false) }

    val options = listOf("Usuario estándar", "Administrador", "Invitado")
    var selectedOption by remember { mutableStateOf(options[0]) }
    var expanded by remember { mutableStateOf(false) }

    var message by remember { mutableStateOf<String?>(null) }

    val users = remember { mutableStateListOf<Pair<String, String>>() }

    val maxReached = users.size >= 5

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Icon(
                imageVector = Icons.Outlined.PersonAdd,
                contentDescription = "Register Icon",
                modifier = Modifier.size(100.dp),
                tint = Primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Crear Cuenta",
                style = MaterialTheme.typography.headlineLarge,
                color = Primary,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Completa tus datos para registrarte",
                style = MaterialTheme.typography.bodyLarge,
                color = Primary
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {  email = it },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                singleLine = true,
                maxLines = 1,
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
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = if (passwordVisible) "Ocultar" else "Mostrar")
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
                singleLine = true,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
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

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedOption,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipo de usuario") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Primary,
                        unfocusedBorderColor = Primary.copy(alpha = 0.3f),
                        focusedTextColor = Primary,
                        unfocusedTextColor = Primary
                    )
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedOption = option
                                expanded = false
                            }
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = acceptedTerms,
                    onCheckedChange = { acceptedTerms = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Primary,
                        uncheckedColor = Primary,
                        checkmarkColor = Surface
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Acepto los términos y condiciones", color = Primary)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val cleanEmail = email.trim()
                    message = when {
                        cleanEmail.isBlank() || password.isBlank() -> "Completa tu email y contraseña"
                        !acceptedTerms -> "Debes aceptar los terminos y condiciones"
                        maxReached -> "No se pueden registrar más usuarios"
                        users.any { it.first == cleanEmail } -> "El email ya está registrado"
                        else -> {
                            users.add(Pair(cleanEmail, selectedOption))
                            email = ""
                            password = ""
                            "Usuario registrado exitosamente"
                        }
                    }
                },
                enabled =  acceptedTerms && !maxReached,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = Surface,
                    disabledContainerColor = Primary.copy(alpha = 0.3f)
                )
            ) {
                Text("Registrar", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }

            if (message != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (message!!.contains("exitosamente")) Success.copy(alpha = 0.1f) else Error.copy(alpha = 0.1f)
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            imageVector = if (message!!.contains("exitosamente")) Icons.Default.CheckCircle else Icons.Default.Info,
                            contentDescription = null,
                            tint = if (message!!.contains("exitosamente")) Success else Error
                        )
                        Text(
                            text = message!!,
                            modifier = Modifier.weight(1f),
                            color = Primary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        IconButton(onClick = { message = null }) {
                            Icon(Icons.Default.Close, "Cerrar", tint = Primary.copy(alpha = 0.6f))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text= "Usuarios registrados (${users.size}/5)",
                style = MaterialTheme.typography.titleMedium,
                color = Primary,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            if(users.isNotEmpty()){
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(users) { u ->
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Primary.copy(alpha = 0.06f)
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(text = u.first, color = Primary, style = MaterialTheme.typography.bodyMedium)
                                Text(text = u.second, color = Secondary, style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom=20.dp)
            ) {
                 Text(
                    text = "¿Ya tienes cuenta? ",
                    color = Primary,
                     style = MaterialTheme.typography.bodyLarge
                )
                 Text(
                    text = "Inicia Sesión",
                    color = Secondary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.clickable { onBackToLoginClick() }
                )
            }
        }
    }
}