package app.revanced.patches.geometrydash.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint

internal object EnableBannerFingerprint : MethodFingerprint(
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass == "Lcom/literobtop/LiteRobTopActivity;" &&
        methodDef.name == "_enableBanner" &&
        methodDef.returnType == "V"
    }
)
