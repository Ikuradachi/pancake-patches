package id.ikuradachi.patches.bstation.ui

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags

object MainNavigationInfoFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC),
    returnType = "Ljava/util/List;",
    parameters = listOf(),
    filters = listOf(
        string("bstar://pegasus/channel"),
        string("bstar://main/explore"),
        methodCall(
            definingClass = "Ltv/danmaku/bili/ui/main2/resource/MainResourceManager;",
            name = "u",
            returnType = "Ltv/danmaku/bili/ui/main2/resource/MainResourceManager;",
        ),
    ),
    custom = { _, classDef ->
        classDef.interfaces.contains("Ltv/danmaku/bili/ui/main2/basic/BaseMainFrameFragment\$b;")
    },
)
