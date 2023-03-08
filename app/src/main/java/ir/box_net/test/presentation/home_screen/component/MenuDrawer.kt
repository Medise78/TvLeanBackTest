package ir.box_net.test.presentation.home_screen.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ir.box_net.test.common.Tab

@Composable
fun MenuDrawer(
    modifier: Modifier = Modifier,
    selectedTab: Tab,
    tabs: List<Tab>,
    onTabSelected: (Tab) -> Unit
) {
    var hasFocus by remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxHeight()
            .onFocusChanged {
                hasFocus = it.hasFocus
            },
    ) {
        tabs.forEach { tab ->
            CustomTab(
                expand = hasFocus,
                tab = tab,
                selected = selectedTab == tab,
                onTabSelected = {
                    if (tab.routes.isNotEmpty()) {
                        onTabSelected(tab)
                        hasFocus = false
                    }
                }
            )
        }
    }
}


@Composable
fun CustomTab(
    modifier: Modifier = Modifier,
    tab: Tab,
    selected: Boolean = false,
    expand: Boolean = false,
    onTabSelected: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isItemFocused by interactionSource.collectIsFocusedAsState()
    val animatedBackground by animateColorAsState(
        animationSpec = tween(500),
        targetValue = when {
            selected && expand -> Color.White.copy(0.2f)
            isItemFocused -> Color.White.copy(alpha = 0.1f)
            else -> Color.Transparent
        }
    )

    val animatedTextColor by animateColorAsState(
        animationSpec = tween(500),
        targetValue = if (selected) {
            Color.White
        } else {
            MaterialTheme.colors.onSurface
        }
    )

    val animateDpAsState by animateDpAsState(
        targetValue = if (expand) 180.dp else 64.dp, animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMediumLow
        )
    )

    val iconColor = when {
        selected && expand -> Color.Black
        selected && !expand -> Color.White
        else -> Color.White.copy(alpha = 0.5f)
    }

    Row(
        modifier = modifier
            .background(animatedBackground)
            .width(animateDpAsState)
            .height(48.dp)
            .padding(vertical = 8.dp)
            .selectable(
                selected = selected,
                onClick = onTabSelected,
                enabled = true,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = null
            ), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = tab.icon,
            contentDescription = tab.name,
            colorFilter = ColorFilter.tint(iconColor),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        if (expand) {
            Text(
                text = tab.name,
                color = animatedTextColor,
                maxLines = 1
            )
        }
    }
}