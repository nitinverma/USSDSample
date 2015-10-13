package edu.nitin.ussd.sample.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.android.internal.telephony.IExtendedNetworkService;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by nitin.verma on 9/29/15.
 */
@Slf4j
public class UssdService extends Service {
    private final IExtendedNetworkService.Stub binder =
            new IExtendedNetworkService.Stub() {

                @Override
                public void setMmiString(final String number) throws RemoteException {
                    log.info("setMmiString( {} )", number);
                }

                @Override
                public CharSequence getMmiRunningText() throws RemoteException {
                    log.info("getMmiRunningText");
                    return null;
                }

                @Override
                public CharSequence getUserMessage(final CharSequence text) throws RemoteException {
                    log.info("getUserMessage( {} )", text);
                    return null;
                }

                @Override
                public void clearMmiString() throws RemoteException {
                    log.info("clearMmiString");
                }
            };

    @Override
    public IBinder onBind(final Intent intent) {
        log.info("onBind {}", intent);
        return binder;
    }
}
