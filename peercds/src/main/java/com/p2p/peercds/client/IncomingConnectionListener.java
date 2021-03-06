/**
 * Copyright (C) 2011-2012 Turn, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.p2p.peercds.client;

import com.p2p.peercds.client.peer.SharingPeer;

import java.nio.channels.SocketChannel;
import java.util.EventListener;

/**
 * EventListener interface for objects that want to handle incoming peer
 * connections.
 *
 * @author mpetazzoni
 */
public interface IncomingConnectionListener extends EventListener {

	public void handleNewPeerConnection(SocketChannel channel, byte[] peerId);

	public void handleFailedConnection(SharingPeer peer, Throwable cause);
}
