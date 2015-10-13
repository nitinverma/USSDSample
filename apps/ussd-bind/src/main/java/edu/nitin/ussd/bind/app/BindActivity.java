package edu.nitin.ussd.bind.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.android.internal.telephony.IExtendedNetworkService;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by nitin.verma on 9/29/15.
 */
@Slf4j
public class BindActivity extends Activity {

    private IExtendedNetworkService extendedNetworkService = null;

    private ServiceConnection serviceConnection =
            new ServiceConnection() {
                @Override
                public void onServiceConnected(final ComponentName name, final IBinder service) {
                    log.info("onServiceConnected( {}, {} )", name, service);
                    extendedNetworkService = IExtendedNetworkService.Stub.asInterface(service);
                }

                @Override
                public void onServiceDisconnected(final ComponentName name) {
                    log.info("onServiceDisconnected ( {} )", name);
                    extendedNetworkService = null;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = new Intent("com.android.ussd.IExtendedNetworkService");
        try {
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
            extendedNetworkService.setMmiString("hi!");
        } catch (final Throwable e) {
            log.error("", e);
        }
    }
}
