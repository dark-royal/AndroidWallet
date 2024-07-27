package com.mobile.mobilewallet.helperclass;

import android.view.View;
import com.mobile.mobilewallet.*;

public class ContextDeterminer {
    public Class<?> nextScreenDeterminator(View view) {
        if (view.getId() == R.id.homeNavigation) {
            return (DashboardActivity.class);
        } else if (view.getId() == R.id.transHistory) {
            return (TransactionHistory.class);
        } else if (view.getId() == R.id.morePay) {
            return (MorePayment.class);
        } else if (view.getId() == R.id.myAccount) {
            return MyAccount.class;
        }
    return null;
    }
}
