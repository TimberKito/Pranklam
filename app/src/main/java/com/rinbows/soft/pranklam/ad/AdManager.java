package com.rinbows.soft.pranklam.ad;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdManager {

    private static final String one_AD = "7b4fcd8edbef6030";
    private static final String two_Ad = "c4f894825d1ec2bb";
    private static final String three_ad = "a7f794672ec9c221";

    public static MaxInterstitialAd onCache(List<MaxInterstitialAd> list) {
        Collections.shuffle(list);
        for (MaxInterstitialAd ad : list) {
            if (ad.isReady()) {
                return ad;
            }
        }
        return null;
    }
    public static List<MaxInterstitialAd> AdLoad(Activity activity) {
        MaxInterstitialAd AdT = new MaxInterstitialAd(two_Ad, activity);
        MaxInterstitialAd AdOne = new MaxInterstitialAd(one_AD, activity);
        MaxInterstitialAd AdThree = new MaxInterstitialAd(three_ad, activity);
        AdOne.loadAd();
        AdT.loadAd();
        AdThree.loadAd();
        ArrayList<MaxInterstitialAd> maxInterstitialAds = new ArrayList<>();
        maxInterstitialAds.add(AdOne);
        maxInterstitialAds.add(AdT);
        maxInterstitialAds.add(AdThree);
        return maxInterstitialAds;
    }
    public static void setAdListener(MaxInterstitialAd ad, AdListener adListener) {
        ad.setListener(new MaxAdListener() {
            @Override
            public void onAdLoaded(@NonNull MaxAd maxAd) {

            }

            @Override
            public void onAdDisplayed(@NonNull MaxAd maxAd) {
                adListener.onSuccess();
            }

            @Override
            public void onAdHidden(@NonNull MaxAd maxAd) {
                adListener.onHidden();
            }

            @Override
            public void onAdClicked(@NonNull MaxAd maxAd) {

            }

            @Override
            public void onAdLoadFailed(@NonNull String s, @NonNull MaxError maxError) {

            }

            @Override
            public void onAdDisplayFailed(@NonNull MaxAd maxAd, @NonNull MaxError maxError) {
                adListener.onFail(maxAd);
            }
        });

    }



}
