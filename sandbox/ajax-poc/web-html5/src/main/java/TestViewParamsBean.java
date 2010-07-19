import com.parleys.server.frontend.web.html5.util.JSFUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean @RequestScoped
public class TestViewParamsBean {
    private String param1, param2;

    public void init() {
        System.out.println("before check: param1 = " + param1);
        System.out.println("before check: param2 = " + param2);
        if (JSFUtil.validationErrors()) {
            JSFUtil.fc().renderResponse();
            return;
        }
        System.out.println("after check: param1 = " + param1);
        System.out.println("after check: param2 = " + param2);
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }
}
