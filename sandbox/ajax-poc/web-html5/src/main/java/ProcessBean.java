import com.parleys.server.frontend.web.html5.util.JSFUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

/**
 * $Author$
 * $Revision$
 */
@ManagedBean
@ViewScoped
public class ProcessBean {
    private String currentPage = "home";

    private Long id;

    public void init() {
        System.out.println("currentPage = " + currentPage);
        System.out.println("id = " + id);
    }

    public String gotoPage(String page) {
        this.currentPage = page;
        return null;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
