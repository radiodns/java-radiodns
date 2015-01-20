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

import org.xbill.DNS.Name;
import org.xbill.DNS.SRVRecord;

/**
 * Delegator of SRVRecord
 * 
 * @author Byrion Smith <byrion.smith@thisisglobal.com>
 * @version 1.0.3
 * @see org.xbill.DNS.SRVRecord
 */
public class Record {

	private SRVRecord mRecord;
	
	public Record(SRVRecord record) {
		mRecord = record;
	}

	/**
	 * @param arg0
	 * @return
	 * @see org.xbill.DNS.Record#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0) {
		return mRecord.compareTo(arg0);
	}

	/**
	 * @param arg
	 * @return
	 * @see org.xbill.DNS.Record#equals(java.lang.Object)
	 */
	public boolean equals(Object arg) {
		return mRecord.equals(arg);
	}

	/**
	 * @return
	 * @see org.xbill.DNS.SRVRecord#getAdditionalName()
	 */
	public Name getAdditionalName() {
		return mRecord.getAdditionalName();
	}

	/**
	 * @return
	 * @see org.xbill.DNS.Record#getDClass()
	 */
	public int getDClass() {
		return mRecord.getDClass();
	}

	/**
	 * @return
	 * @see org.xbill.DNS.Record#getName()
	 */
	public Name getName() {
		return mRecord.getName();
	}

	/**
	 * @return
	 * @see org.xbill.DNS.SRVRecord#getPort()
	 */
	public int getPort() {
		return mRecord.getPort();
	}

	/**
	 * @return
	 * @see org.xbill.DNS.SRVRecord#getPriority()
	 */
	public int getPriority() {
		return mRecord.getPriority();
	}

	/**
	 * @return
	 * @see org.xbill.DNS.Record#getRRsetType()
	 */
	public int getRRsetType() {
		return mRecord.getRRsetType();
	}

	/**
	 * @return
	 * @see org.xbill.DNS.Record#getTTL()
	 */
	public long getTTL() {
		return mRecord.getTTL();
	}

	/**
	 * @return
	 * @see org.xbill.DNS.SRVRecord#getTarget()
	 */
	public Name getTarget() {
		return mRecord.getTarget();
	}

	/**
	 * @return
	 * @see org.xbill.DNS.Record#getType()
	 */
	public int getType() {
		return mRecord.getType();
	}

	/**
	 * @return
	 * @see org.xbill.DNS.SRVRecord#getWeight()
	 */
	public int getWeight() {
		return mRecord.getWeight();
	}

	/**
	 * @return
	 * @see org.xbill.DNS.Record#hashCode()
	 */
	public int hashCode() {
		return mRecord.hashCode();
	}

	/**
	 * @return
	 * @see org.xbill.DNS.Record#rdataToString()
	 */
	public String rdataToString() {
		return mRecord.rdataToString();
	}

	/**
	 * @return
	 * @see org.xbill.DNS.Record#rdataToWireCanonical()
	 */
	public byte[] rdataToWireCanonical() {
		return mRecord.rdataToWireCanonical();
	}

	/**
	 * @param rec
	 * @return
	 * @see org.xbill.DNS.Record#sameRRset(org.xbill.DNS.Record)
	 */
	public boolean sameRRset(org.xbill.DNS.Record rec) {
		return mRecord.sameRRset(rec);
	}

	/**
	 * @return
	 * @see org.xbill.DNS.Record#toString()
	 */
	public String toString() {
		return mRecord.toString();
	}

	/**
	 * @param section
	 * @return
	 * @see org.xbill.DNS.Record#toWire(int)
	 */
	public byte[] toWire(int section) {
		return mRecord.toWire(section);
	}

	/**
	 * @return
	 * @see org.xbill.DNS.Record#toWireCanonical()
	 */
	public byte[] toWireCanonical() {
		return mRecord.toWireCanonical();
	}

	/**
	 * @param name
	 * @return
	 * @see org.xbill.DNS.Record#withName(org.xbill.DNS.Name)
	 */
	public org.xbill.DNS.Record withName(Name name) {
		return mRecord.withName(name);
	}
	
}
