package app.revanced.patches.geometrydash.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint

internal object ShowInterstitialFingerprint : MethodFingerprint(
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass == "Lcom/literobtop/LiteRobTopActivity;" &&
        methodDef.name == "_showInterstitial" &&
        methodDef.returnType == "V"
    }
)
