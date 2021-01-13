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

import routing.util.RoutingInfo;

import util.Tuple;

import core.Connection;
import core.DTNHost;
import core.Message;
import core.Settings;
import core.SimClock;

/**
 * Implementation of PRoPHET router as described in
 * <I>Probabilistic routing in intermittently connected networks</I> by
 * Anders Lindgren et al.
 * with the Efficient eviction policy from
 * <I>Efficient evict policy for PRoPHET</I> by
 * Sati and Ahmad
 */
public class EfficientEvictProphetRouter extends ProphetRouter {
	/** Prophet router's setting namespace ({@value})*/
	public static final String PROPHET_NS = "EfficientEvictProphetRouter";
    protected HashMap<Message, Integer> replicaCounts = null;

	/**
	 * Constructor. Creates a new message router based on the settings in
	 * the given Settings object.
	 * @param s The settings object
	 */
	public EfficientEvictProphetRouter(Settings s) {
		super(s);
        this.replicaCounts = new HashMap<Message, Integer>();
	}

	/**
	 * Copyconstructor.
	 * @param r The router prototype where setting values are copied from
	 */
	protected EfficientEvictProphetRouter(ProphetRouter r) {
		super(r);
        this.replicaCounts = new HashMap<Message, Integer>();
	}

	/**
	 * Returns the message in the message buffer that maximizes the 
     * utility function.
	 * (that is not being sent if excludeMsgBeingSent is true).
	 * @param excludeMsgBeingSent If true, excludes message(s) that are
	 * being sent from the oldest message check (i.e. if oldest message is
	 * being sent, the second oldest message is returned)
	 * @return The oldest message or null if no message could be returned
	 * (no messages in buffer or all messages in buffer are being sent and
	 * exludeMsgBeingSent is true)
	 */
	protected Message getNextMessageToRemove(boolean excludeMsgBeingSent) {
		Collection<Message> messages = this.getMessageCollection();
		Message highest = null;
        double utilityValue = 0;
		for (Message m : messages) {

			if (excludeMsgBeingSent && isSending(m.getId())) {
				continue; // skip the message(s) that router is sending
			}
            double curUtility = this.computeUtilityValue(m);

			if (highest == null) {
				highest = m;
                utilityValue = curUtility;
			} else if (curUtility > utilityValue) {
				highest = m;
                utilityValue = curUtility;
			}
		}

		return highest;
	}

    protected double computeUtilityValue(Message m) {
        int TTL = m.getInitTtl();
        int TTLr = m.getTtl();
        int TTLe = TTL - TTLr;
        int Hc = m.getHopCount();
        double Tbuf = SimClock.getTime() - m.getReceiveTime();
        int Rc = replicaCounts.get(m);

        return TTLe/TTL * (Hc * Tbuf / TTLr + Rc * Tbuf);
    }

	/**
	 * Tries to start a transfer of message using a connection. Is starting
	 * succeeds, the connection is added to the watch list of active connections
	 * @param m The message to transfer
	 * @param con The connection to use
	 * @return the value returned by
	 * {@link Connection#startTransfer(DTNHost, Message)}
	 */
	protected int startTransfer(Message m, Connection con) {
        if (this.replicaCounts.containsKey(m)) {
            this.replicaCounts.put(m, this.replicaCounts.get(m) + 1);
        } else {
            this.replicaCounts.put(m, 1);
        }

        int retVal = super.startTransfer(m, con);

        if (!this.getMessageCollection().contains(m)) {
            this.replicaCounts.remove(m);
        }

		return retVal;
	}



}
