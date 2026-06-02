package com.terraforma.app.ui.storage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.terraforma.app.ui.theme.*

@Composable
fun StorageScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
            .verticalScroll(scrollState)
    ) {
        ScreenHeader(title = "Storage", onBack = { navController.popBackStack() })

        Spacer(modifier = Modifier.height(24.dp))

        SectionLabel("RUNTIME EXPLORER")

        Spacer(modifier = Modifier.height(12.dp))

        val runtimeTree = listOf(
            Pair(0, ".minecraft/"),
            Pair(1, "versions/"),
            Pair(1, "libraries/"),
            Pair(1, "assets/"),
            Pair(1, "resourcepacks/"),
            Pair(1, "shaderpacks/"),
            Pair(1, "mods/"),
            Pair(1, "config/"),
            Pair(1, "logs/"),
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .background(SurfaceContainer)
                .border(1.dp, Divider)
                .padding(16.dp)
        ) {
            runtimeTree.forEach { (depth, name) ->
                Row(modifier = Modifier.padding(start = (depth * 16).dp, bottom = 4.dp)) {
                    if (depth > 0) {
                        Text(
                            text = "├── ",
                            fontFamily = JetBrainsMono,
                            fontSize = 12.sp,
                            color = TextDisabled
                        )
                    }
                    Text(
                        text = name,
                        fontFamily = JetBrainsMono,
                        fontSize = 12.sp,
                        color = if (depth == 0) Accent else TextPrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        SectionLabel("STORAGE CATEGORIES")

        Spacer(modifier = Modifier.height(12.dp))

        val categories = listOf(
            Triple("Worlds", "3 worlds  —  1.2 GB", "worlds/"),
            Triple("Snapshots", "12 snapshots  —  480 MB", "snapshots/"),
            Triple("Backups", "5 backups  —  3.6 GB", "backups/"),
            Triple("Downloads", "8 files  —  220 MB", "downloads/"),
            Triple("Exports", "2 exports  —  95 MB", "exports/"),
            Triple("Project Archive", "6 projects  —  340 MB", "archive/"),
        )

        categories.forEach { (label, meta, path) ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .background(SurfaceContainer)
                    .border(1.dp, Divider)
                    .clickable { }
                    .padding(horizontal = 16.dp, vertical = 13.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = label,
                        fontFamily = IBMPlexSans,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = TextPrimary
                    )
                    Text(
                        text = meta,
                        fontFamily = JetBrainsMono,
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                }
                Text(
                    text = path,
                    fontFamily = JetBrainsMono,
                    fontSize = 10.sp,
                    color = TextDisabled
                )
            }
            Spacer(modifier = Modifier.height(1.dp))
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}