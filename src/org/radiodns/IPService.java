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
 * @version 1.0.2
 */
public class IPService extends Service {

	private String mAuthoritativeFqdn;
	
	/**
	 * Class constructor
	 * 
	 * @param authoritativeFqdn		Authoritative FQDN
	 */
	public IPService(String authoritativeFqdn) {
		mAuthoritativeFqdn = authoritativeFqdn;
	}

	/*
	 * @see org.radiodns.Service#getRadioDNSFqdn()
	 */
	@Override
	public String getRadioDNSFqdn() {
		return null;
	}
	
	/*
	 * @see org.radiodns.Service#getAuthoritativeFqdn()
	 */
	@Override
	public String getAuthoritativeFqdn() throws LookupException {
		return mAuthoritativeFqdn;
	}
}
