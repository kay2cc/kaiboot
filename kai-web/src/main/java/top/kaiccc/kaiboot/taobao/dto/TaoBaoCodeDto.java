package top.kaiccc.kaiboot.taobao.dto;

import java.util.List;

/**
 * @author kaiccc
 * @date 2019-01-05 14:50
 */
public class TaoBaoCodeDto {

    private SeparateBean separate;
    private String cardType;
    private String title;
    private String desc;
    private String id;
    private String nodeId;
    private String accountId;
    private String favourCount;
    private String favourStatus;
    private String referId;
    private TaoBaoCommentDto isTop;
    private String isElite;
    private String namespace;
    private String gmtCreate;
    private Object sellershowId;
    private String commentCount;
    private UserBean user;
    private String targetUrl;
    private String feedType;
    private List<ActivitiesBean> activities;
    private List<?> privileges;
    private List<PicsBean> pics;
    private List<?> videos;

    public SeparateBean getSeparate() {
        return separate;
    }

    public void setSeparate(SeparateBean separate) {
        this.separate = separate;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFavourCount() {
        return favourCount;
    }

    public void setFavourCount(String favourCount) {
        this.favourCount = favourCount;
    }

    public String getFavourStatus() {
        return favourStatus;
    }

    public void setFavourStatus(String favourStatus) {
        this.favourStatus = favourStatus;
    }

    public String getReferId() {
        return referId;
    }

    public void setReferId(String referId) {
        this.referId = referId;
    }

    public TaoBaoCommentDto getIsTop() {
        return isTop;
    }

    public void setIsTop(TaoBaoCommentDto isTop) {
        this.isTop = isTop;
    }

    public String getIsElite() {
        return isElite;
    }

    public void setIsElite(String isElite) {
        this.isElite = isElite;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Object getSellershowId() {
        return sellershowId;
    }

    public void setSellershowId(Object sellershowId) {
        this.sellershowId = sellershowId;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public List<ActivitiesBean> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivitiesBean> activities) {
        this.activities = activities;
    }

    public List<?> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<?> privileges) {
        this.privileges = privileges;
    }

    public List<PicsBean> getPics() {
        return pics;
    }

    public void setPics(List<PicsBean> pics) {
        this.pics = pics;
    }

    public List<?> getVideos() {
        return videos;
    }

    public void setVideos(List<?> videos) {
        this.videos = videos;
    }

    public static class SeparateBean {

        private String height;

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }
    }

    public static class UserBean {

        private String userNick;
        private String userUrl;
        private String userLogo;

        public String getUserNick() {
            return userNick;
        }

        public void setUserNick(String userNick) {
            this.userNick = userNick;
        }

        public String getUserUrl() {
            return userUrl;
        }

        public void setUserUrl(String userUrl) {
            this.userUrl = userUrl;
        }

        public String getUserLogo() {
            return userLogo;
        }

        public void setUserLogo(String userLogo) {
            this.userLogo = userLogo;
        }
    }

    public static class ActivitiesBean {


        private String id;
        private String title;
        private String sellerId;
        private String targetUrl;
        private String actType;
        private String cover;
        private String joinCount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getTargetUrl() {
            return targetUrl;
        }

        public void setTargetUrl(String targetUrl) {
            this.targetUrl = targetUrl;
        }

        public String getActType() {
            return actType;
        }

        public void setActType(String actType) {
            this.actType = actType;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getJoinCount() {
            return joinCount;
        }

        public void setJoinCount(String joinCount) {
            this.joinCount = joinCount;
        }
    }

    public static class PicsBean {


        private String height;
        private String id;
        private String path;
        private String type;
        private String width;

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }
    }
}
