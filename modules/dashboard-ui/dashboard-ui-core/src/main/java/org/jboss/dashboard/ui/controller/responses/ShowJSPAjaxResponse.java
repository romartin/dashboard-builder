/**
 * Copyright (C) 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.dashboard.ui.controller.responses;

import org.jboss.dashboard.ui.HTTPSettings;
import org.jboss.dashboard.ui.controller.CommandRequest;
import org.jboss.dashboard.workspace.LayoutRegion;
import org.jboss.dashboard.workspace.Parameters;

/**
 * Response that embeds a jsp view into the output stream.
 */
public class ShowJSPAjaxResponse extends PanelAjaxResponse {

    private static transient org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ShowJSPAjaxResponse.class.getName());

    private LayoutRegion region;
    private String jsp;

    public ShowJSPAjaxResponse(String jsp) {
        this.jsp = jsp;
    }

    public ShowJSPAjaxResponse(String jsp, LayoutRegion region) {
        this.jsp = jsp;
        this.region = region;
    }

    public boolean execute(CommandRequest cmdReq) throws Exception {
        if (log.isDebugEnabled()) log.debug("ShowJSPAjaxResponse: " + jsp);
        cmdReq.getResponseObject().setHeader("Content-Encoding", HTTPSettings.lookup().getEncoding());
        cmdReq.getResponseObject().setContentType("text/html;charset=" + HTTPSettings.lookup().getEncoding());
        if (region != null) {
            cmdReq.getRequestObject().setAttribute(Parameters.RENDER_IDREGION, region.getId());
        }
        cmdReq.getRequestObject().getRequestDispatcher(jsp).include(cmdReq.getRequestObject(), cmdReq.getResponseObject());
        return true;
    }
}
