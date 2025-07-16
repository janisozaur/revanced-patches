package app.revanced.patches.geometrydash.fingerprints

import app.revanced.patcher.fingerprint

internal val loadBannerFingerprint = fingerprint {
    custom { methodDef, _ ->
        methodDef.definingClass == "Lcom/unity3d/services/banners/UnityBanners;" &&
        methodDef.name == "_loadBanner" &&
        methodDef.returnType == "V"
    }
}
