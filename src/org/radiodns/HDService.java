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

/**
 * @author Byrion Smith <byrion.smith@thisisglobal.com>
 * @version 1.0.3
 */
public class HDService extends Service {

	/*
	 * Transmitter identifier value
	 */
	private String mTx;
	/*
	 * Country code value
	 */
	private String mCc;

	/**
	 * Class constructor
	 * 
	 * @param cc		Country code
	 * @param tx		Transmitter identifier value
	 * @throws LookupException
	 */
	public HDService(String cc, String tx) throws LookupException {
		/*
		 * check for required variables
		 */
		if (cc == null || tx == null) {
			throw new LookupException("Required values are missing.");
		}

		/*
		 * Validate cc value
		 */
		if (cc.matches("(?i)^[0-9A-F]{3}$")) {
			mCc = cc;
		} else {
			throw new LookupException(
					"Invalid Country Code (CC) value. Must be a valid 3-character hexadecimal Country Code.");
		}

		/*
		 * Validate tx value
		 */
		if (tx.matches("(?i)^[0-9A-F]{5}$")) {
			mTx = tx;
		} else {
			throw new LookupException(
					"Invalid Transmitter Identifier (TX) value. Must be a valid 5-character hexadecimal.");
		}
	}
	
	public String getTx() {
		return mTx;
	}
	
	public String getCc() {
		return mCc;
	}

	/*
	 * @see org.radiodns.Service#getRadioDNSFqdn()
	 */
	@Override
	public String getRadioDNSFqdn() {
		String fqdn = String.format("%s.%s.hd.radiodns.org", mTx, mCc);
		fqdn = fqdn.toLowerCase();

		return fqdn;
	}
}