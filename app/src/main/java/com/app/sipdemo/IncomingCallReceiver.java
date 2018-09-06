package com.app.sipdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.sip.*;


public class IncomingCallReceiver  extends BroadcastReceiver {
    /**
     * Processes the incoming call, answers it, and hands it over to the
     * WalkieTalkieActivity.
     * @param context The context under which the receiver is running.
     * @param intent The intent being received.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        SipAudioCall incomingCall = null;
        try {

            SipAudioCall.Listener listener = new SipAudioCall.Listener() {
                @Override
                public void onRinging(SipAudioCall call, SipProfile caller) {
                    try {
                        call.answerCall(30);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onRingingBack(SipAudioCall call) {
                    super.onRingingBack(call);
                }

                @Override
                public void onReadyToCall(SipAudioCall call) {
                    super.onReadyToCall(call);
                }

                @Override
                public void onError(SipAudioCall call, int errorCode, String errorMessage) {
                    super.onError(call, errorCode, errorMessage);
                }

                @Override
                public void onCalling(SipAudioCall call) {
                    super.onCalling(call);
                }

                @Override
                public void onChanged(SipAudioCall call) {
                    super.onChanged(call);
                }

                @Override
                public void onCallHeld(SipAudioCall call) {
                    super.onCallHeld(call);
                }

                @Override
                public void onCallBusy(SipAudioCall call) {
                    super.onCallBusy(call);
                }

                @Override
                public void onCallEnded(SipAudioCall call) {
                    super.onCallEnded(call);
                }

                @Override
                public void onCallEstablished(SipAudioCall call) {
                    super.onCallEstablished(call);
                }
            };

            WalkieTalkieActivity wtActivity = (WalkieTalkieActivity) context;

            incomingCall = wtActivity.manager.takeAudioCall(intent, listener);
            incomingCall.answerCall(30);
            incomingCall.startAudio();
            incomingCall.setSpeakerMode(true);
            if(incomingCall.isMuted()) {
                incomingCall.toggleMute();
            }

            wtActivity.call = incomingCall;

            wtActivity.updateStatus(incomingCall);

        } catch (Exception e) {
            if (incomingCall != null) {
                incomingCall.close();
            }
        }
    }

}