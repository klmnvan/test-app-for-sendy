package com.example.sendyapp.presentation.components.pullrefresh

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sendyapp.presentation.ui.theme.SendyAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPullToRefreshContainer(pullToRefreshState: PullToRefreshState) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (pullToRefreshState.progress > 0 || pullToRefreshState.isRefreshing) {
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter).padding(top = 120.dp),
                state = pullToRefreshState,
                contentColor = SendyAppTheme.colors.primary,
                containerColor = SendyAppTheme.colors.primary.copy(alpha = 0.1f)
            )
        }
    }
}