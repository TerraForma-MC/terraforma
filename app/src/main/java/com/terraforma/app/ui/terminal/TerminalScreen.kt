package com.terraforma.app.ui.terminal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.terraforma.app.ui.theme.*

data class TerminalLine(val text: String, val type: LineType)
enum class LineType { COMMAND, OUTPUT, ERROR, INFO }

@Composable
fun TerminalScreen(navController: NavController) {
    var input by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val listState = rememberLazyListState()

    val lines = remember {
        mutableStateListOf(
            TerminalLine("TerraForma Terminal v1.0", LineType.INFO),
            TerminalLine("Type 'help' for available commands.", LineType.INFO),
            TerminalLine("", LineType.OUTPUT),
        )
    }

    fun executeCommand(cmd: String) {
        lines.add(TerminalLine("terraforma@local:~$ $cmd", LineType.COMMAND))
        when (cmd.trim().lowercase()) {
            "help" -> {
                lines.add(TerminalLine("Available commands:", LineType.OUTPUT))
                lines.add(TerminalLine("  app install <package>    Install a package", LineType.OUTPUT))
                lines.add(TerminalLine("  app remove <package>     Remove a package", LineType.OUTPUT))
                lines.add(TerminalLine("  app list                 List installed packages", LineType.OUTPUT))
                lines.add(TerminalLine("  minecraft start          Start Minecraft client", LineType.OUTPUT))
                lines.add(TerminalLine("  server start             Start a server", LineType.OUTPUT))
                lines.add(TerminalLine("  server stop              Stop a server", LineType.OUTPUT))
                lines.add(TerminalLine("  server list              List all servers", LineType.OUTPUT))
                lines.add(TerminalLine("  git clone <url>          Clone a repository", LineType.OUTPUT))
                lines.add(TerminalLine("  clear                    Clear terminal", LineType.OUTPUT))
            }
            "clear" -> lines.clear()
            "server list" -> {
                lines.add(TerminalLine("NAME             SOFTWARE   VERSION   STATUS", LineType.OUTPUT))
                lines.add(TerminalLine("SurvivalSMP      Paper      26.2      RUNNING", LineType.OUTPUT))
                lines.add(TerminalLine("TestServer       Purpur     26.1      STOPPED", LineType.OUTPUT))
                lines.add(TerminalLine("ProxyNetwork     Velocity   3.4.0     RUNNING", LineType.OUTPUT))
            }
            "app list" -> {
                lines.add(TerminalLine("Installed packages:", LineType.OUTPUT))
                lines.add(TerminalLine("  paper        26.2", LineType.OUTPUT))
                lines.add(TerminalLine("  neovim       0.10.0", LineType.OUTPUT))
                lines.add(TerminalLine("  git          2.44.0", LineType.OUTPUT))
            }
            else -> {
                if (cmd.startsWith("app install ")) {
                    val pkg = cmd.removePrefix("app install ").trim()
                    lines.add(TerminalLine("Resolving $pkg...", LineType.OUTPUT))
                    lines.add(TerminalLine("Downloading $pkg...", LineType.OUTPUT))
                    lines.add(TerminalLine("Installed: $pkg", LineType.OUTPUT))
                } else if (cmd.isNotBlank()) {
                    lines.add(TerminalLine("command not found: $cmd", LineType.ERROR))
                }
            }
        }
    }

    LaunchedEffect(lines.size) {
        if (lines.isNotEmpty()) listState.animateScrollToItem(lines.size - 1)
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
    ) {
        ScreenHeader(title = "Terminal", onBack = { navController.popBackStack() })

        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            items(lines) { line ->
                Text(
                    text = line.text,
                    fontFamily = JetBrainsMono,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = when (line.type) {
                        LineType.COMMAND -> Accent
                        LineType.OUTPUT -> TextPrimary
                        LineType.ERROR -> StatusError
                        LineType.INFO -> TextSecondary
                    }
                )
            }
        }

        // Input row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceContainer)
                .border(width = 1.dp, color = Divider)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$ ",
                fontFamily = JetBrainsMono,
                fontSize = 13.sp,
                color = Accent,
                fontWeight = FontWeight.Bold
            )
            BasicTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester),
                textStyle = TextStyle(
                    fontFamily = JetBrainsMono,
                    fontSize = 13.sp,
                    color = TextPrimary
                ),
                cursorBrush = SolidColor(Accent),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(
                    onSend = {
                        if (input.isNotBlank()) {
                            executeCommand(input.trim())
                            input = ""
                        }
                    }
                )
            )
        }

        navigationBarsPadding()
    }
}