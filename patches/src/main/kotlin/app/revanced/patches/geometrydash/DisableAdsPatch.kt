package app.revanced.patches.geometrydash

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.extensions.InstructionExtensions.getInstruction
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.patches.geometrydash.fingerprints.*
import com.android.tools.smali.dexlib2.iface.instruction.OneRegisterInstruction

@Patch(
    name = "Disable ads",
    description = "Disables all ads in Geometry Dash Lite including banner ads, interstitial ads, and rewarded video ads.",
    compatiblePackages = [
        CompatiblePackage(
            "com.robtopx.geometryjumplite",
            [
                "2.2.144"
            ]
        )
    ]
)
@Suppress("unused")
object DisableAdsPatch : BytecodePatch(
    setOf(
        ShowInterstitialFingerprint,
        ShowRewardedVideoFingerprint,
        EnableBannerFingerprint,
        HasCachedInterstitialFingerprint,
        HasCachedRewardedVideoFingerprint,
        LoadBannerFingerprint
    )
) {
    override fun execute(context: BytecodeContext) {
        // Disable interstitial ad display
        ShowInterstitialFingerprint.result?.let { result ->
            result.mutableMethod.apply {
                addInstructions(
                    0,
                    """
                        # Interstitial ads disabled - do nothing
                        return-void
                    """
                )
            }
        } ?: throw ShowInterstitialFingerprint.exception

        // Disable rewarded video ad display
        ShowRewardedVideoFingerprint.result?.let { result ->
            result.mutableMethod.apply {
                addInstructions(
                    0,
                    """
                        # Rewarded video ads disabled - do nothing
                        return-void
                    """
                )
            }
        } ?: throw ShowRewardedVideoFingerprint.exception

        // Disable banner ad enabling
        EnableBannerFingerprint.result?.let { result ->
            result.mutableMethod.apply {
                addInstructions(
                    0,
                    """
                        # Banner ads disabled - do nothing
                        return-void
                    """
                )
            }
        } ?: throw EnableBannerFingerprint.exception

        // Make cached interstitial check always return false
        HasCachedInterstitialFingerprint.result?.let { result ->
            result.mutableMethod.apply {
                val returnInstruction = getInstruction<OneRegisterInstruction>(implementation!!.instructions.size - 1)
                val returnRegister = returnInstruction.registerA

                addInstructions(
                    0,
                    """
                        # Always return false - no cached interstitial ads
                        const/4 v$returnRegister, 0x0
                        return v$returnRegister
                    """
                )
            }
        } ?: throw HasCachedInterstitialFingerprint.exception

        // Make cached rewarded video check always return false
        HasCachedRewardedVideoFingerprint.result?.let { result ->
            result.mutableMethod.apply {
                val returnInstruction = getInstruction<OneRegisterInstruction>(implementation!!.instructions.size - 1)
                val returnRegister = returnInstruction.registerA

                addInstructions(
                    0,
                    """
                        # Always return false - no cached rewarded videos
                        const/4 v$returnRegister, 0x0
                        return v$returnRegister
                    """
                )
            }
        } ?: throw HasCachedRewardedVideoFingerprint.exception

        // Disable Unity banner loading
        LoadBannerFingerprint.result?.let { result ->
            result.mutableMethod.apply {
                addInstructions(
                    0,
                    """
                        # Unity Banner loading disabled - do nothing
                        return-void
                    """
                )
            }
        } ?: throw LoadBannerFingerprint.exception
    }
}
