package app.revanced.patches.geometrydash.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint

internal object HasCachedRewardedVideoFingerprint : MethodFingerprint(
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass == "Lcom/literobtop/LiteRobTopActivity;" &&
        methodDef.name == "_hasCachedRewardedVideo" &&
        methodDef.returnType == "Z"
    }
)
