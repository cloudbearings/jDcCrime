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

import java.util.ArrayList;

public class CrimeFeedWrapperTest{
    private static CrimeFeedWrapper crimeFeedWrapper;
    private static CrimeList crimeList;

    public static void main( String args[] ) {
        crimeFeedWrapper = new CrimeFeedWrapper();
        crimeList = crimeFeedWrapper.getCrimeList();

        for ( int i=0; i< crimeList.getCrimes().size(); i++ ) {
            Crime crime = crimeList.getCrimes().get( i );
            System.out.println( "Offense: " + crime.getOffense() );
            System.out.println( "Method: " + crime.getMethod() );
            System.out.println( "Report Date/Time: " + crime.getReportDateTime() );
            System.out.println( "District: " + crime.getDistrict() );
            System.out.println();
        }
    }

}
