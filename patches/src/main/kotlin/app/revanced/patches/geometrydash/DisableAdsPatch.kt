package app.revanced.patches.geometrydash

import app.revanced.patcher.extensions.InstructionExtensions.replaceInstructions
import app.revanced.patcher.patch.bytecodePatch
import app.revanced.patches.geometrydash.fingerprints.*

@Suppress("unused")
val disableAdsPatch = bytecodePatch(
    name = "Disable ads",
    description = "Disables all ads in Geometry Dash Lite including banner ads, interstitial ads, and rewarded video ads. Dummy text",
) {
    compatibleWith("com.robtopx.geometryjumplite")

    execute {
        // Disable interstitial ad display
        showInterstitialFingerprint.method.replaceInstructions(
            0,
            """
                # Interstitial ads disabled - do nothing
                return-void
            """,
        )

        // Disable rewarded video ad display
        showRewardedVideoFingerprint.method.replaceInstructions(
            0,
            """
                # Rewarded video ads disabled - do nothing
                return-void
            """,
        )

        // Disable banner ad enabling
        enableBannerFingerprint.method.replaceInstructions(
            0,
            """
                # Banner ads disabled - do nothing
                return-void
            """,
        )

        // Make cached interstitial check always return false
        hasCachedInterstitialFingerprint.method.replaceInstructions(
            0,
            """
                # Always return false - no cached interstitial ads
                const/4 v0, 0x0
                return v0
            """,
        )

        // Make cached rewarded video check always return false
        hasCachedRewardedVideoFingerprint.method.replaceInstructions(
            0,
            """
                # Always return false - no cached rewarded videos
                const/4 v0, 0x0
                return v0
            """,
        )

        // Disable Unity banner loading
        loadBannerFingerprint.method.replaceInstructions(
            0,
            """
                # Unity Banner loading disabled - do nothing
                return-void
            """,
        )
    }
}
