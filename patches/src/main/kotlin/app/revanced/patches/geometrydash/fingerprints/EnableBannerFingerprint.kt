package app.revanced.patches.geometrydash.fingerprints

import app.revanced.patcher.fingerprint

internal val enableBannerFingerprint = fingerprint {
    custom { methodDef, _ ->
        methodDef.definingClass == "Lcom/literobtop/LiteRobTopActivity;" &&
        methodDef.name == "_enableBanner" &&
        methodDef.returnType == "V"
    }
}
