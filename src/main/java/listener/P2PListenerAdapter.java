package listener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class P2PListenerAdapter implements P2PListener {


    List<P2PListener> listeners = new CopyOnWriteArrayList<>();


    public void addListener(final P2PListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(final P2PListener listener) {
        this.listeners.remove(listener);
    }


    /*
     * (non-Javadoc)
     * 
     * @see listener.P2PListener#onPeerConnected()
     */
    public void onPeerConnected() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see listener.P2PListener#onPeerDisonnected()
     */
    public void onPeerDisonnected() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see listener.P2PListener#onMessageReceived()
     */
    public void onMessageReceived() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see listener.P2PListener#onMessageSent()
     */
    public void onMessageSent() {
        // TODO Auto-generated method stub

    }

}
