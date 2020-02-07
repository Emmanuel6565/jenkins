package com.jcx.JXC.Dto;


public class ArticleDto {
    private String title;
    private String tag;
    private String content;

    public String getTitle(){
        return title;
    }
    public String getTag(){
        return tag;
    }
    public String getContent(){
        return content;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Article {\n");
        sb.append(" ").append(toIndentedString(super.toString())).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n  ");
    }
}

