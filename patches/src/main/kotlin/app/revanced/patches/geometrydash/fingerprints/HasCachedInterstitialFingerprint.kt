package app.revanced.patches.geometrydash.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint

internal object HasCachedInterstitialFingerprint : MethodFingerprint(
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass == "Lcom/literobtop/LiteRobTopActivity;" &&
        methodDef.name == "_hasCachedInterstitial" &&
        methodDef.returnType == "Z"
    }
)
