package eu.tutorials.whopays.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import eu.tutorials.whopays.R

@Composable
fun AdMobBanner(modifier: Modifier = Modifier) {
    val adUnitId = stringResource(R.string.admob_banner_id)

    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                this.adUnitId = adUnitId
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AdMobBannerPreview() {
    AdMobBanner()
}