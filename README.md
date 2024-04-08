java-radiodns
=============

### Introduction

A Java library that facilitates the resolution of an authoritative Fully Qualified Domain Name (FQDN) from the broadcast parameters of an audio service.

From this FQDN it is then possible to discover IP-based applications provided in relation to the queried audio service.

For more information about RadioDNS, please see the official documentation: [http://radiodns.org/docs](http://radiodns.org/docs)


### Installation

Download [radiodns-1.0.4.jar](https://github.com/radiodns/java-radiodns/releases) from the releases page and add it to your project.

The library depends on the [dnsjava](http://www.dnsjava.org/) library.


### Getting Started

Use the methods on RadioDNS to return a Service object for a given set of broadcast parameters. Use the Service object to resolve RadioDNS applications.

    RadioDNS rdns = new RadioDNS();
    
    Service service = rdns.lookupFMService("ce1", "c479", 95800);

    Application application = service.getApplication(RadioDNS.RADIOVIS);

	if (application != null) {
	 
	   Record record = application.getRecords().get(0);

	   System.out.println(String.format("ApplicationId: %s Host: %s Port: %d Priority: %d Weight: %d",
	        application.getApplicationId(), record.getTarget(), record.getPort(), record.getPriority(), record.getWeight()));

	} else {
		System.out.println("No Results");
	}

### DNS Server

You can override the default DNS server to query by specifying the hostname in the RadioDNS constructor.

    RadioDNS rdns = new RadioDNS("8.8.8.8");
    
### License

Licensed under the Apache License, Version 2.0 (the "License").
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.    
See the License for the specific language governing permissions and 
limitations under the License.

Copyright (c) 2012 Global Radio UK Limited
