package com.rinbows.soft.pranklam.ad;

import com.applovin.mediation.MaxAd;

public interface AdListener {
    void onFail(MaxAd ad);
    void onSuccess();
    void onHidden( );
}
