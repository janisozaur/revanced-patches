package app.revanced.patches.geometrydash.fingerprints

import app.revanced.patcher.fingerprint

internal val showInterstitialFingerprint = fingerprint {
    custom { methodDef, _ ->
        methodDef.definingClass == "Lcom/literobtop/LiteRobTopActivity;" &&
        methodDef.name == "_showInterstitial" &&
        methodDef.returnType == "V"
    }
}
