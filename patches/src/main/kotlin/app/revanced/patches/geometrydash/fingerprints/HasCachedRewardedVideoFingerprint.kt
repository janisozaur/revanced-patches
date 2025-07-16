package app.revanced.patches.geometrydash.fingerprints

import app.revanced.patcher.fingerprint

internal val hasCachedRewardedVideoFingerprint = fingerprint {
    custom { methodDef, _ ->
        methodDef.definingClass == "Lcom/literobtop/LiteRobTopActivity;" &&
        methodDef.name == "_hasCachedRewardedVideo" &&
        methodDef.returnType == "Z"
    }
}
