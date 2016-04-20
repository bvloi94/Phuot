import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by vanloibui on 4/12/16.
 */
public class TestReceiver extends ResultReceiver {
    private Receiver receiver;

    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    public TestReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public interface Receiver {
        public void onReceiveResult(int resultCode, Bundle resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (receiver!=null) {
            receiver.onReceiveResult(resultCode, resultData);
        }
        super.onReceiveResult(resultCode, resultData);
    }
}
