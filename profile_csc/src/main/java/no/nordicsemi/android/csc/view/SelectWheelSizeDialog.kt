/*
 * Copyright (c) 2022, Nordic Semiconductor
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list
 * of conditions and the following disclaimer in the documentation and/or other materials
 * provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be
 * used to endorse or promote products derived from this software without specific prior
 * written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
 * OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package no.nordicsemi.android.csc.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import no.nordicsemi.android.common.theme.NordicTheme
import no.nordicsemi.android.csc.R
import no.nordicsemi.android.ui.view.dialog.StringListDialog
import no.nordicsemi.android.ui.view.dialog.StringListDialogConfig
import no.nordicsemi.android.ui.view.dialog.StringListDialogResult
import no.nordicsemi.android.ui.view.dialog.toAnnotatedString

@Composable
internal fun SelectWheelSizeDialog(onEvent: (StringListDialogResult) -> Unit) {
    val wheelEntries = stringArrayResource(R.array.wheel_entries)
    val wheelValues = stringArrayResource(R.array.wheel_values)

    StringListDialog(createConfig(wheelEntries) {
        onEvent(it)
    })
}

@Composable
private fun createConfig(entries: Array<String>, onResult: (StringListDialogResult) -> Unit): StringListDialogConfig {
    return StringListDialogConfig(
        title = stringResource(id = R.string.csc_dialog_title).toAnnotatedString(),
        items = entries.toList(),
        onResult = onResult
    )
}

@Preview
@Composable
internal fun DefaultPreview() {
    NordicTheme {
        val wheelEntries = stringArrayResource(R.array.wheel_entries)
        StringListDialog(createConfig(wheelEntries) {})
    }
}
