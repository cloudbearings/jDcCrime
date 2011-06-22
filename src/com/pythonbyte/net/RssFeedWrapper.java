package com.pythonbyte.net;

/*

jDcCrime

INTRODUCTION

A library for retriving crime data from the Reported Crime List for Washington, DC. Implemented 
in Java

CrimeFeedWrapper returns a list of Crime objects that contain getters for some of the basic 
details about the crime report.

The data is gathered from:
	 http://data.octo.dc.gov/feeds/crime_incidents/crime_incidents_current.xml


AUTHOR

Mark J. Nenadov (2011)
* Essex, Ontario
* Email: <marknenadov@gmail.com> 

LICENSING

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version

This program is distributed in the hope that it will be useful
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>. 

*/

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.CharacterData;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class RssFeedWrapper {
    private URL url;

    public RssFeedWrapper( String urlName ){
        try {
            url = new URL(urlName);
        }
        catch ( MalformedURLException e ) {
            url = null;
        }
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

   protected String getCharacterDataFromElement( Element e ) {

	    Node child = e.getFirstChild();

		if(child instanceof CharacterData) {
    		CharacterData cd = (CharacterData) child;
        	return cd.getData();
        }

		return "";
    }

    public Document parse(){
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return builder.parse( url.openStream() );
        }
        catch ( SAXException e ) {
            return null;
        }
        catch ( ParserConfigurationException e ) {
            return null;
        }
        catch ( IOException e ) {
            return null;
        }

    }
}
