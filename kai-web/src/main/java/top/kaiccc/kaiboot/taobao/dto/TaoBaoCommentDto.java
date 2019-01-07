package top.kaiccc.kaiboot.taobao.dto;

import java.util.List;

/**
 * @author kaiccc
 * @date 2019-01-05 15:10
 */
public class TaoBaoCommentDto {


    private FeaturesBean features;
    private List<ListBean> list;

    public FeaturesBean getFeatures() {
        return features;
    }

    public void setFeatures(FeaturesBean features) {
        this.features = features;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class FeaturesBean {

        private String commentListIcon;
        private String configVersion;
        private String commentListTitle;

        public String getCommentListIcon() {
            return commentListIcon;
        }

        public void setCommentListIcon(String commentListIcon) {
            this.commentListIcon = commentListIcon;
        }

        public String getConfigVersion() {
            return configVersion;
        }

        public void setConfigVersion(String configVersion) {
            this.configVersion = configVersion;
        }

        public String getCommentListTitle() {
            return commentListTitle;
        }

        public void setCommentListTitle(String commentListTitle) {
            this.commentListTitle = commentListTitle;
        }
    }

    public static class ListBean {


        private String commentId;
        private String commenterLogo;
        private String commenterNick;
        private String commenterUrl;
        private String content;
        private FeaturesBeanX features;
        private InteractDatasBean interactDatas;
        private PaginationInfosBean paginationInfos;
        private String status;
        private String targetId;
        private String targetTitle;
        private String targetUrl;
        private String timestamp;
        private List<AdminsBean> admins;
        private List<?> commenterIcons;
        private List<?> elements;
        private List<?> enhances;

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }

        public String getCommenterLogo() {
            return commenterLogo;
        }

        public void setCommenterLogo(String commenterLogo) {
            this.commenterLogo = commenterLogo;
        }

        public String getCommenterNick() {
            return commenterNick;
        }

        public void setCommenterNick(String commenterNick) {
            this.commenterNick = commenterNick;
        }

        public String getCommenterUrl() {
            return commenterUrl;
        }

        public void setCommenterUrl(String commenterUrl) {
            this.commenterUrl = commenterUrl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public FeaturesBeanX getFeatures() {
            return features;
        }

        public void setFeatures(FeaturesBeanX features) {
            this.features = features;
        }

        public InteractDatasBean getInteractDatas() {
            return interactDatas;
        }

        public void setInteractDatas(InteractDatasBean interactDatas) {
            this.interactDatas = interactDatas;
        }

        public PaginationInfosBean getPaginationInfos() {
            return paginationInfos;
        }

        public void setPaginationInfos(PaginationInfosBean paginationInfos) {
            this.paginationInfos = paginationInfos;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getTargetTitle() {
            return targetTitle;
        }

        public void setTargetTitle(String targetTitle) {
            this.targetTitle = targetTitle;
        }

        public String getTargetUrl() {
            return targetUrl;
        }

        public void setTargetUrl(String targetUrl) {
            this.targetUrl = targetUrl;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public List<AdminsBean> getAdmins() {
            return admins;
        }

        public void setAdmins(List<AdminsBean> admins) {
            this.admins = admins;
        }

        public List<?> getCommenterIcons() {
            return commenterIcons;
        }

        public void setCommenterIcons(List<?> commenterIcons) {
            this.commenterIcons = commenterIcons;
        }

        public List<?> getElements() {
            return elements;
        }

        public void setElements(List<?> elements) {
            this.elements = elements;
        }

        public List<?> getEnhances() {
            return enhances;
        }

        public void setEnhances(List<?> enhances) {
            this.enhances = enhances;
        }

        public static class FeaturesBeanX {
            /**
             * nickType : taobao
             */

            private String nickType;

            public String getNickType() {
                return nickType;
            }

            public void setNickType(String nickType) {
                this.nickType = nickType;
            }
        }

        public static class InteractDatasBean {
            /**
             * likeCount : 0
             * likeStatus : false
             * replyCount : 0
             * showLikeButton : true
             * showReplyButton : true
             */

            private String likeCount;
            private String likeStatus;
            private String replyCount;
            private String showLikeButton;
            private String showReplyButton;

            public String getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(String likeCount) {
                this.likeCount = likeCount;
            }

            public String getLikeStatus() {
                return likeStatus;
            }

            public void setLikeStatus(String likeStatus) {
                this.likeStatus = likeStatus;
            }

            public String getReplyCount() {
                return replyCount;
            }

            public void setReplyCount(String replyCount) {
                this.replyCount = replyCount;
            }

            public String getShowLikeButton() {
                return showLikeButton;
            }

            public void setShowLikeButton(String showLikeButton) {
                this.showLikeButton = showLikeButton;
            }

            public String getShowReplyButton() {
                return showReplyButton;
            }

            public void setShowReplyButton(String showReplyButton) {
                this.showReplyButton = showReplyButton;
            }
        }

        public static class PaginationInfosBean {

            private String score;
            private String id;

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public static class AdminsBean {
            /**
             * adminName : 举报
             * adminCode : report
             */

            private String adminName;
            private String adminCode;

            public String getAdminName() {
                return adminName;
            }

            public void setAdminName(String adminName) {
                this.adminName = adminName;
            }

            public String getAdminCode() {
                return adminCode;
            }

            public void setAdminCode(String adminCode) {
                this.adminCode = adminCode;
            }
        }
    }
}
