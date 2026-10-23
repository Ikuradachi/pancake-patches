package id.ikuradachi.patches.bstation.player

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import id.ikuradachi.patches.bstation.shared.Constants.COMPATIBILITY_BSTATION

@Suppress("unused")
val playerOutroPatch = bytecodePatch(
    name = "Disable player outro unlock card",
    description = "Disables video player outro unlock video guide bottom-right card.",
) {
    category("Player")
    compatibleWith(COMPATIBILITY_BSTATION)

    execute {
        UnlockVideoGuideProgressFingerprint.method.addInstructions(
            0,
            "return-void",
        )
        UnlockVideoGuideLandscapeFingerprint.method.addInstructions(
            0,
            "return-void",
        )
        UnlockVideoGuideWidgetFingerprint.method.addInstructions(
            0,
            "return-void",
        )
    }
}
