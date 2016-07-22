/*
 * Copyright 1999,2005 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.apache.xmlrpc;

import java.util.Hashtable;
import java.io.InputStream;

import org.xml.sax.AttributeList;
import org.xml.sax.SAXException;

/**
 * Process an XML-RPC server response from a byte array or an
 * InputStream into an Object. Optionally throw the result object
 * if it is an exception.
 *
 * @author <a href="mailto:hannes@apache.org">Hannes Wallnoefer</a>
 * @author <a href="mailto:andrew@kungfoocoder.org">Andrew Evers</a>
 * @since 2.0
 */
public final class XmlRpcClientResponseProcessor extends XmlRpc {

    /**
     * Creates a new instance.
     */
    public XmlRpcClientResponseProcessor()
     {
    }

    protected void objectParsed(Object what)
     {
        result = what;
    }

    /**
     * Overrides method in XmlRpc to handle fault repsonses.
     */
    public void startElement(String name, AttributeList atts)
            throws SAXException
     {
        if ("fault".equals(name))
         {
            fault = true;
        }
         else
         {
            super.startElement(name, atts);
        }
    }

    /**
     * Called by the worker management framework to see if this worker can be
     * re-used. Must attempt to clean up any state, and return true if it can
     * be re-used.
     *
     * @return boolean true if this worker has been cleaned up and may be re-used.
     */
    protected boolean canReUse()
     {
        result = null;
        fault = false;
        return true;
    }
}
