/*
 * Copyright (c) 2012 Global Radio UK Limited
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.    
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package org.radiodns;

import org.minidns.dnsname.DnsName;
import org.minidns.record.Record.TYPE;
import org.minidns.record.SRV;

/**
 * Delegator of SRV
 * 
 * @author Jake Chandrasakera
 * @version 2.0.0
 * @see org.minidns.record.SRV
 */
public class Record {

	private SRV mRecord;

	public Record(SRV record) {
		mRecord = record;
	}

	/**
	 * @param arg0
	 * @return
	 */
	public int compareTo(SRV arg0) {
		return mRecord.compareTo(arg0);
	}

	/**
	 * @param arg
	 * @return
	 */
	public boolean equals(SRV arg) {
		return mRecord.equals(arg);
	}

	/**
	 * The target server.
	 *
	 * @deprecated use {@link #getTarget()} instead.
	 */
	@Deprecated
	public DnsName getName() {
		return mRecord.name;
	}

	/**
	 * @return
	 */
	public int getPort() {
		return mRecord.port;
	}

	/**
	 * @return
	 */
	public int getPriority() {
		return mRecord.priority;
	}

	/**
	 * @return
	 */
	public DnsName getTarget() {
		return mRecord.target;
	}

	/**
	 * @return
	 */
	public TYPE getType() {
		return mRecord.getType();
	}

	/**
	 * @return
	 */
	public int getWeight() {
		return mRecord.weight;
	}

	/**
	 * @return
	 */
	public int hashCode() {
		return mRecord.hashCode();
	}

	/**
	 * @return
	 */
	public String toString() {
		return mRecord.toString();
	}
}
