package com.parleys.server.frontend.web.html5.beans;

import com.parleys.server.domain.News;
import com.parleys.server.domain.types.NewsType;
import com.parleys.server.frontend.web.html5.util.JSFUtil;
import com.parleys.server.security.AuthorizationException;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean for the homepage.
 *
 * @author Jan-Kees van Andel
 * @author Stephan Janssen
 */
@ManagedBean @RequestScoped
public class NewsBean extends AbstractParleysBean {

    private static final Logger LOGGER = Logger.getLogger(NewsBean.class);

    private long newsId;

    private News activeNewsItem;

    private List<News> newsItems;

    public void init() {
        if (JSFUtil.theCurrentEventIsNoPageAction()) {
            return;
        }

        try {
            newsItems = getParleysServiceDelegate().getNews(NewsType.GENERAL, 0, 0, 10).getOverviews();
        } catch (AuthorizationException e) {
            LOGGER.error(e);
        } catch (ClientStatusException e) {
            LOGGER.error(e);
        }

        if (newsId > 0) {
            for (News newsItem : newsItems) {
                if (newsItem.getId() == newsId) {
                    activeNewsItem = newsItem;
                    break;
                }
            }
        }

        initializeHomepage();
    }

    public void setNewsId(final long newsId) {
        this.newsId = newsId;
    }

    public long getNewsId() {
        return newsId;
    }

    public News getActiveNewsItem() {
        return activeNewsItem;
    }

    public void setActiveNewsItem(final News activeNewsItem) {
        this.activeNewsItem = activeNewsItem;
    }

    public List<News> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(final List<News> newsItems) {
        this.newsItems = newsItems;
    }
}
