package app.revanced.patches.geometrydash.fingerprints

import app.revanced.patcher.fingerprint

internal val showRewardedVideoFingerprint = fingerprint {
    custom { methodDef, _ ->
        methodDef.definingClass == "Lcom/literobtop/LiteRobTopActivity;" &&
        methodDef.name == "_showRewardedVideo" &&
        methodDef.returnType == "V"
    }
}
