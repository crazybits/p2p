package listener;


/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public interface P2PListener {

    public void onPeerConnected();

    public void onPeerDisconnected();

    public void onMessageReceived();

    public void onMessageSent();


}
