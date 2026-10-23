package id.ikuradachi.patches.bstation.player

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.fieldAccess
import app.morphe.patcher.literal
import app.morphe.patcher.methodCall
import app.morphe.patcher.newInstance
import app.morphe.patcher.opcode
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

// ============================================================================
// Target 4: Video Player Outro / Unlock Video Guide Bottom-Right Card
// ============================================================================

// Target 4A: Suppress Unlock Video Guide Progress Dispatcher
object UnlockVideoGuideProgressFingerprint : Fingerprint(
    definingClass = "Lcom/biliintl/playdetail/page/ad/unlock/UnlockVideoGuideService;",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "V",
    parameters = listOf("I", "I"),
    filters = listOf(
        fieldAccess(smali = "Lcom/biliintl/playdetail/fundation/identifier/IdType;->SeasonId:Lcom/biliintl/playdetail/fundation/identifier/IdType;"),
        methodCall(definingClass = "Lcom/biliintl/playdetail/page/identifier/OgvIdentifier;", returnType = "J"),
        methodCall(returnType = "Ltv/danmaku/biliplayer/ScreenModeType;"),
    ),
)

// Target 4B: Suppress Landscape Bottom-Right Outro Popup
object UnlockVideoGuideLandscapeFingerprint : Fingerprint(
    definingClass = "Lcom/biliintl/playdetail/page/ad/unlock/UnlockVideoGuideService;",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "V",
    parameters = listOf("I", "I"),
    filters = listOf(
        literal(0x3fe8000000000000L),
        string("bstar-main.video-detail.unlock-advance-pop.all.show"),
    ),
)

// Target 4C: Suppress Unlock Video Guide Widget View Attachment
object UnlockVideoGuideWidgetClassFingerprint : Fingerprint(
    filters = listOf(
        string("UnlockVideoGuideWidget"),
    ),
)

object UnlockVideoGuideWidgetFingerprint : Fingerprint(
    classFingerprint = UnlockVideoGuideWidgetClassFingerprint,
    accessFlags = listOf(AccessFlags.PUBLIC),
    returnType = "V",
    parameters = listOf("L"),
    filters = listOf(
        newInstance("Landroid/widget/FrameLayout\$LayoutParams;"),
        methodCall(definingClass = "Landroid/view/ViewGroup;", name = "addView"),
    ),
)

// ============================================================================
// Target 5: Video Player In-Stream Bottom-Right Overlay Ad Banner
// ============================================================================

object OverlayAdGateFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "Z",
    parameters = listOf(),
    filters = listOf(
        string("overlay_ad"),
        methodCall(
            definingClass = "Lcom/biliintl/bstarcomm/ads/admanager/NativeAdManager;",
            returnType = "Z",
        ),
    ),
)

// ============================================================================
// Target 6: Fullscreen Bottom-Right Player Recommendation Widget
// ============================================================================

object PlayerRecommendWidgetActivateFingerprint : Fingerprint(
    definingClass = "Lcom/biliintl/playdetail/page/player/panel/widget/control/PlayerRecommendWidget;",
    accessFlags = listOf(AccessFlags.PUBLIC),
    returnType = "V",
    parameters = listOf(),
    filters = listOf(
        string("mPlayerContainer"),
        opcode(Opcode.CHECK_CAST),
        literal(8),
    ),
)

// ============================================================================
// Community Guideline Toast (separate from player outro unlock card)
// ============================================================================

object FavoriteGuidelineFingerprint : Fingerprint(
    definingClass = "Lcom/biliintl/playdetail/page/list/community/guideline/FavoriteGuidelineService;",
    accessFlags = listOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
    returnType = "V",
    parameters = listOf("Lcom/biliintl/play/model/view/ViewCommunityCardMeta\$FavToast;"),
    filters = listOf(
        newInstance("Lcom/biliintl/playdetail/page/list/community/guideline/FavoriteGuidelineComponent;"),
    ),
)
