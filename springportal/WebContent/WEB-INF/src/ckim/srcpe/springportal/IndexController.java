package ckim.srcpe.springportal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.juniper.smgt.ssp.model.PortalBehavior;
import net.juniper.smgt.ssp.model.UserInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

public class IndexController extends SspAction{

	protected final Log logger = LogFactory.getLog(getClass());

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        PortalBehavior pb = getBehavior(request);
        UserInfo ui = pb.getUserInfo();

        boolean haveMenu = setMenuItems(request, pb);
        request.setAttribute("userInfo", ui);

        if (haveMenu)
            return new ModelAndView(".index.page");
        else
            return new ModelAndView(".index.page");
    }


}
