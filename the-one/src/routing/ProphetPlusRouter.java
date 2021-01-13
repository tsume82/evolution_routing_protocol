/*
 * Copyright 2010 Aalto University, ComNet
 * Released under GPLv3. See LICENSE.txt for details.
 */
package routing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import routing.util.EnergyModel;
import routing.util.RoutingInfo;

import util.Tuple;

import core.Connection;
import core.DTNHost;
import core.Message;
import core.Settings;
import core.SimClock;
import core.NetworkInterface;

/**
 * Implementation of PRoPHET+ router as described in
 * <I>PRoPHET+: An Adaptive PRoPHET-Based Routing
 * Protocol for Opportunistic Network
 * </I> by
 * Huang et al.
 */
public class ProphetPlusRouter extends ProphetRouter {
    private final double Wb = 0.25;
    private final double Wp = 0.25;
    private final double Wa = 0.1;
    private final double Wo = 0.1;
    private final double Wr = 0.3;

	/** Prophet router's setting namespace ({@value})*/
	public static final String PROPHET_NS = "ProphetPlusRouter";

	protected double transmissionEnergy;
    protected double totalEnergy;
    protected int updatePeriod;
    protected int sent;
    protected int minMsgSize;
    protected int dataGenerated;
    protected int meanSelfGeneratedDataSize;
    private final String EVENTS = "Events1";
    private final String MSG_SIZE = "size";
    
	/**
	 * Constructor. Creates a new message router based on the settings in
	 * the given Settings object.
	 * @param s The settings object
	 */
	public ProphetPlusRouter(Settings s) {
		super(s);
        this.transmissionEnergy = s.getDouble(EnergyModel.TRANSMIT_ENERGY_S);
        this.totalEnergy = s.getDouble(EnergyModel.INIT_ENERGY_S);
        this.updatePeriod = new Settings(ProphetRouter.PROPHET_NS).getInt(SECONDS_IN_UNIT_S);
        this.minMsgSize = (int) new Settings(EVENTS).getCsvInts(MSG_SIZE)[0];
        this.reset();
	}

	/**
	 * Copyconstructor.
	 * @param r The router prototype where setting values are copied from
	 */
	protected ProphetPlusRouter(ProphetPlusRouter r) {
		super(r);
        this.transmissionEnergy = r.transmissionEnergy;
        this.totalEnergy = r.totalEnergy;
        this.updatePeriod = r.updatePeriod;
        this.minMsgSize = r.minMsgSize;
        this.reset();
	}

	/**
	 * Returns the deliverability value for the given receiver.
     * @param m The message to send
     * @param to The host being considered
	 * @return the Vd value
	 */
	public double getDeliverability(Message m, ProphetPlusRouter to) {
        long btotal = to.getBufferSize();
        long bself = to.getSelfGeneratedDataSize();
        long bthresh = btotal - bself;
        double vb = (btotal - m.getSize()) / bthresh;
        if (vb < 0) {
            vb = 0;
        }

        EnergyModel energy = to.getEnergy();  
        double premain = energy.getEnergy();
        double preceive = to.transmissionEnergy;
        double psend = preceive;
        double pthreshold = 0;
        double ptotal = to.totalEnergy;
        double vp = (premain - preceive - psend - pthreshold) / ptotal;

        NetworkInterface iface = to.getHost().getInterface(1);
        int transmissionRate = iface.getTransmitSpeed(null);
        int senderTransmissionRate = this.getHost().getInterface(1).getTransmitSpeed(null); 
        double va = transmissionRate/senderTransmissionRate;
        if (va > 1) {
            va = 1;
        }

        int nTransmissions = to.getNTransmissions();
        int maxTransmissions = to.getMaxTransmissions();
        double vo = nTransmissions/maxTransmissions;

		super.ageDeliveryPreds(); // make sure preds are updated before getting
        double vr = to.getPredFor(m.getTo());
		if (preds.containsKey(m.getTo())) {
			vr = preds.get(m.getTo());
		}

        Double vd = this.Wb * vb + this.Wp * vp + this.Wa * va + this.Wo * vo + this.Wr * vr;
        return vd;
	}

	/**
	 * Tries to send all other messages to all connected hosts ordered by
	 * their delivery probability
	 * @return The return value of {@link #tryMessagesForConnected(List)}
	 */
	private Tuple<Message, Connection> tryOtherMessages() {
		List<Tuple<Message, Connection>> messages =
			new ArrayList<Tuple<Message, Connection>>();

		Collection<Message> msgCollection = getMessageCollection();

		/* for all connected hosts collect all messages that have a higher
		   probability of delivery by the other host */
		for (Connection con : getConnections()) {
			DTNHost other = con.getOtherNode(getHost());
			ProphetPlusRouter othRouter = (ProphetPlusRouter)other.getRouter();

			if (othRouter.isTransferring()) {
				continue; // skip hosts that are transferring
			}

			for (Message m : msgCollection) {
				if (othRouter.hasMessage(m.getId())) {
					continue; // skip messages that the other one has
				}
				if (getDeliverability(m, othRouter) > getDeliverability(m, this)) {
					// the other node has higher probability of delivery
					messages.add(new Tuple<Message, Connection>(m,con));
				}
			}
		}

		if (messages.size() == 0) {
			return null;
		}

		// sort the message-connection tuples
		Collections.sort(messages, new TupleComparator());
		return tryMessagesForConnected(messages);	// try to send messages
	}

	/**
	 * Tries to start a transfer of message using a connection. Is starting
	 * succeeds, the connection is added to the watch list of active connections
	 * @param m The message to transfer
	 * @param con The connection to use
	 * @return the value returned by
	 * {@link Connection#startTransfer(DTNHost, Message)}
	 */
    @Override
	protected int startTransfer(Message m, Connection con) {
        this.sent++; 
        if (m.getFrom() == this.getHost()) {
            this.dataGenerated += m.getSize();            
        }
         return super.startTransfer(m, con);
    }

    protected int getNTransmissions() {
        return this.sent;
    }

    protected int getMaxTransmissions() {
        return (int) this.getHost().getInterface(1).getTransmitSpeed(null) * this.updatePeriod / this.minMsgSize;
    }

    protected int getSelfGeneratedDataSize() {
        return this.meanSelfGeneratedDataSize;
    }

    public void update() {
        if (SimClock.getTime() % this.updatePeriod == 0) {
            this.reset();
        }
        super.update();
    }

    public void reset() {
        sent = 0;
        dataGenerated = 0;
        this.meanSelfGeneratedDataSize = this.dataGenerated/this.updatePeriod;
    }
}
