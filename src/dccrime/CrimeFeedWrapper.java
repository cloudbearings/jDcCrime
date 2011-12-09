package dccrime;

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

import com.pythonbyte.net.RssFeedWrapper;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class CrimeFeedWrapper extends RssFeedWrapper {
    private Document parsedDocument;

    public CrimeFeedWrapper( String url ) {
        super( url );
    }

    public NodeList getCrimeNodeList() {
        parsedDocument = super.parse();
        return parsedDocument.getElementsByTagName( "dcst:ReportedCrime" );
    }

    public Crime getCrimeFromElement(Element crimeElement) {
        Crime c = new Crime();

        loadOffense( c, crimeElement.getElementsByTagName( "dcst:offense" ) );
        loadMethod( c, crimeElement.getElementsByTagName( "dcst:method" ) );
        loadDistrict( c, crimeElement.getElementsByTagName( "dcst:district" ) );
        loadReportDateTime( c, crimeElement.getElementsByTagName( "dcst:reportdatetime" ) );

        return c;
    }

    private void loadReportDateTime( Crime crime, NodeList nodeList ) {
        for ( int i=0; i<nodeList.getLength(); i++ ){
            crime.setReportDateTime( getCharacterDataFromElement((Element) nodeList.item( i ) ) );
        }
    }

    private void loadMethod(Crime crime, NodeList nodeList) {
        for ( int i=0; i<nodeList.getLength(); i++ ){
            crime.setMethod(getCharacterDataFromElement((Element) nodeList.item ( i ) ) );
        }
    }

    private void loadDistrict(Crime crime, NodeList nodeList) {
            for ( int i=0; i<nodeList.getLength(); i++ ){
                crime.setDistrict( getCharacterDataFromElement( (Element) nodeList.item( i ) ) );
            }
        }

    public void loadOffense( Crime crime, NodeList nodeList ) {
        for (int i=0; i<nodeList.getLength(); i++){
            crime.setOffense( getCharacterDataFromElement( (Element) nodeList.item( i ) ) );
        }
    }

    public CrimeList getCrimeList(){
        CrimeList crimeList = new CrimeList();
        NodeList nodes = getCrimeNodeList();
        for(int i=0;i<nodes.getLength();i++)
        {
            crimeList.add( getCrimeFromElement( (Element)nodes.item( i ) ) );
        }
        return crimeList;
    }

}
