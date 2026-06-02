package com.terraforma.app.ui.sessions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.terraforma.app.ui.theme.*
import com.terraforma.app.viewmodel.SessionViewModel

@Composable
fun SessionsScreen(
    navController: NavController,
    sessionViewModel: SessionViewModel = viewModel()
) {
    val sessions by sessionViewModel.sessions.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
            .verticalScroll(scrollState)
    ) {
        ScreenHeader(title = "Sessions", onBack = { navController.popBackStack() })

        Spacer(modifier = Modifier.height(24.dp))

        SectionLabel("ACTIVE SESSIONS  —  ${sessions.count { it.isActive }}")

        Spacer(modifier = Modifier.height(12.dp))

        sessions.forEach { session ->
            SessionRow(session = session, modifier = Modifier.padding(horizontal = 20.dp))
            Spacer(modifier = Modifier.height(1.dp))
        }

        Spacer(modifier = Modifier.height(28.dp))

        SectionLabel("HIBERNATED")

        Spacer(modifier = Modifier.height(12.dp))

        val hibernated = listOf(
            "build-test-01" to "2h ago",
            "mod-dev-fabric" to "1d ago"
        )

        hibernated.forEach { (name, time) ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .background(SurfaceContainer)
                    .border(1.dp, DividerSubtle)
                    .clickable { }
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = name,
                        fontFamily = JetBrainsMono,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                    Text(
                        text = "Hibernated $time",
                        fontFamily = IBMPlexSans,
                        fontSize = 11.sp,
                        color = TextDisabled
                    )
                }
                Text(
                    text = "RESTORE",
                    fontFamily = JetBrainsMono,
                    fontSize = 10.sp,
                    letterSpacing = 1.sp,
                    color = Accent
                )
            }
            Spacer(modifier = Modifier.height(1.dp))
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun SessionRow(session: com.terraforma.app.viewmodel.SessionState, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(SurfaceContainer)
            .border(1.dp, if (session.isActive) Accent.copy(alpha = 0.2f) else Divider)
            .clickable { }
            .padding(horizontal = 16.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(5.dp)
                .background(if (session.isActive) StatusRunning else StatusStopped)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = session.name,
                fontFamily = JetBrainsMono,
                fontSize = 13.sp,
                color = TextPrimary
            )
            Text(
                text = session.type,
                fontFamily = IBMPlexSans,
                fontSize = 11.sp,
                color = TextSecondary
            )
        }
        Text(
            text = session.uptime,
            fontFamily = JetBrainsMono,
            fontSize = 11.sp,
            color = TextDisabled
        )
    }
}